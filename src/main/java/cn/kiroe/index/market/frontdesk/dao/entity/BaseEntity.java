package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Kiro
 * @Date 2023/12/29 20:17
 **/
@Getter
@Setter
@Schema(name = "BaseEntity对象", description = "实体基类")
public class BaseEntity implements Serializable {
    @Schema (description ="编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @Schema (description ="创建时间")
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;

    @Schema (description ="更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema (description ="逻辑删除")
    @TableField("deleted")
    @TableLogic()
    private Boolean deleted;
}
