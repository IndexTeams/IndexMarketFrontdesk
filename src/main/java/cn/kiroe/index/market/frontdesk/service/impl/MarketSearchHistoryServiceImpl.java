package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.json.ObjectMapper;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketKeyword;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketSearchHistory;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketKeywordMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketSearchHistoryMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.SearchIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketSearchHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 搜索历史表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MarketSearchHistoryServiceImpl extends ServiceImpl<MarketSearchHistoryMapper, MarketSearchHistory> implements IMarketSearchHistoryService {
       private final MarketSearchHistoryMapper searchHistoryMapper;
       private final MarketKeywordMapper keywordMapper;


    @Override
    public BaseRespVo<List<String>> selectListByKeyword(String keyword) {
        List<MarketSearchHistory> marketSearchHistories = searchHistoryMapper.selectList(new LambdaQueryWrapper<MarketSearchHistory>()
                .like(MarketSearchHistory::getKeyword, keyword));
        if(marketSearchHistories==null){
            return null;
        }
       List<String> keywordArrayList= new ArrayList<>();
        for (MarketSearchHistory marketSearchHistory : marketSearchHistories) {
           keywordArrayList.add(marketSearchHistory.getKeyword());
        }


        return BaseRespVo.ok(keywordArrayList);
    }

    @Override
    public BaseRespVo<SearchIndexVo> index(Integer id) {
        List<MarketKeyword> marketKeywords = keywordMapper.selectList(null);
        //为默认的关键词列表，根据默认列表的sortOrder来排序
        List<MarketKeyword> defaultKeywordList = marketKeywords.stream()
                .filter(MarketKeyword::getIsDefault)
                .sorted(Comparator.comparingInt(MarketKeyword::getSortOrder))
                .collect(Collectors.toList());

        //为热门的关键词列表,同上
        List<MarketKeyword> hotKeywordList = marketKeywords.stream()
                .filter(MarketKeyword::getIsHot)
                .sorted(Comparator.comparingInt(MarketKeyword::getSortOrder))
                .collect(Collectors.toList());

        //根据用户id查询

        QueryWrapper<MarketSearchHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<MarketSearchHistory> searchHistoryList = searchHistoryMapper.selectList(queryWrapper);
        List<String> collect = searchHistoryList.stream()
                .map(marketSearchHistory -> marketSearchHistory.getKeyword()).collect(Collectors.toList());


        List<Map<String,String>> keywordMapList=new ArrayList<>();
        for (String s : collect) {
            Map<String,String> keywordMap=new HashMap<>();
            keywordMap.put("keyword",s);
            keywordMapList.add(keywordMap);
        }


        SearchIndexVo searchIndexVo = new SearchIndexVo();
        searchIndexVo.setDefaultKeyword(defaultKeywordList.get(0));
        searchIndexVo.setHotKeywordList(hotKeywordList);
        searchIndexVo.setHistoryKeywordList(keywordMapList);
        return BaseRespVo.ok(searchIndexVo);
    }

    @Override
    public Boolean deleteById(Integer id) {

        int i = searchHistoryMapper.deleteById(id);
        return i>0;
    }
}
