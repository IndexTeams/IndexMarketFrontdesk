package cn.kiroe.index.market.frontdesk.dao.generator;

/**
 * @Author Kiro
 * @Date 2023/12/30 20:22
 **/

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Collections;

public class MybatisGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://jfl.kiroe.cn:3306/index_market", "root", "123321")
                         .globalConfig(builder -> {
                             builder.author("kiro") // 设置作者
                                    .enableSwagger() // 开启 swagger 模式
                                    .commentDate("yyyy-MM-dd HH:mm:ss")
                                    .outputDir("/Users/kiro/Downloads/mybatis"); // 指定输出目录
                         })
                         .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                             int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                             if (typeCode == Types.SMALLINT) {
                                 // 自定义类型转换
                                 return DbColumnType.INTEGER;
                             }
                             return typeRegistry.getColumnType(metaInfo);

                         }))
                         .packageConfig(builder -> {
                             builder.parent("cn.kiroe.index.market") // 设置父包名
                                    .moduleName("frontdesk") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/kiro/Downloads/mybatis")); // 设置mapperXml生成路径
                         })
                         .strategyConfig(builder -> {
                             builder.entityBuilder()
                                    .enableLombok()
                                    .enableTableFieldAnnotation()
                                    .logicDeleteColumnName("deleted")
                                    .addSuperEntityColumns("id", "add_time", "update_time", "deleted")
                                    .addTableFills(new Column("add_time", FieldFill.INSERT))
                                    .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                                    .idType(IdType.AUTO)
                                    .superClass("BaseEntity")
                                    .build();
                         })
                         .strategyConfig(builder -> {
                             builder.controllerBuilder().enableRestStyle();
                         })
                         .execute();
    }
}
