package cn.kiroe.index.market.frontdesk.dao.vo.common;

import lombok.Data;

import javax.validation.constraints.Positive;

/**
 * @Author Kiro
 * @Date 2024/01/03 20:27
 **/
@Data
public class BasePage {
    @Positive
    Integer page;
    @Positive
    Integer limit;
}
