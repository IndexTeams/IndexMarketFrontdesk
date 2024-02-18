package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 常见问题表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_issue")
@Schema (name ="MarketIssue对象", description = "常见问题表")
@ToString(callSuper = true)
public class MarketIssue extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description= "问题标题")
    @TableField("question")
    private String question;

    @Schema (description ="问题答案")
    @TableField("answer")
    private String answer;
}
