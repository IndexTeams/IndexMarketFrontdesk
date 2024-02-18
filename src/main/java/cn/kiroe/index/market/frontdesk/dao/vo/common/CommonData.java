package cn.kiroe.index.market.frontdesk.dao.vo.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author stone
 * @date 2022/04/06 10:13
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CommonData<T>{

    /*limit: 20
    list: [{id: 1152101, goodsSn: "1152101", name: "魔兽世界 部落 奥格瑞玛 拉杆箱 可登机", categoryId: 1012001, brandId: 0,…},…]
    page: 6
    pages: 13
    total: 241*/
    private Integer total;
    private Integer pages;
    private Integer limit;
    private Integer page;
    private List<T> list;


    public static CommonData data(PageInfo pageInfo) {
        CommonData data = new CommonData();
        data.setTotal((int) pageInfo.getTotal());// pageInfo特有类 对Page 结果进行包装
        data.setPages(pageInfo.getPages());
        data.setPage(pageInfo.getPageNum());
        data.setLimit(pageInfo.getPageSize());
        data.setList(pageInfo.getList());
        return data;
    }
    public static<T> CommonData data(PageInfo pageInfo, Page<T> page){
        CommonData data = new CommonData();
        data.setTotal((int) page.getTotal());// pageInfo特有类 对Page 结果进行包装
        data.setPages(page.getPages());
        data.setPage(page.getPageNum());
        data.setLimit(page.getPageSize());
        data.setList(pageInfo.getList());
        return data;
    }
    public static CommonData data(List list) {
        // 对查询数据进行封装
        CommonData data = new CommonData();
        data.setTotal(list.size());
        data.setPages(1);
        data.setPage(1);
        data.setLimit(list.size());
        data.setList(list);
        return data;
    }

    public void setData(Page<T> page){
        this.limit = page.getPageSize();
        this.pages = page.getPages();
        this.total= (int) page.getTotal();
        this.page = page.getPageNum();
        this.list = page.getResult();
    }


}
