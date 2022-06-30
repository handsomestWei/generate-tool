package com.wjy.generate;

import java.util.ArrayList;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

/**
 * 根据数据库表结构自动生成设计文档
 *
 * @author weijiayu
 * @date 2022/6/25 10:45
 */
public class DbDocumentGenerator {

    public static void main(String[] args) throws Exception {
        new DocumentationExecute(newConfiguration()).execute();
    }

    private static Configuration newConfiguration() {
        return Configuration.builder()
                // 版本
                .version("1.0.0")
                // 描述
                .description("数据库设计文档生成")
                // 数据源
                .dataSource(newHikariDataSource())
                // 生成配置
                .engineConfig(newEngineConfig())
                // 生成配置
                // .produceConfig(newProcessConfig())
                .build();
    }

    private static EngineConfig newEngineConfig() {
        return EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("D:\\")
                // 打开目录
                .openOutputDir(true)
                // 文件类型
                .fileType(EngineFileType.WORD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                // 自定义文件名称
                .fileName("自定义文件名称").build();
    }

    private static DataSource newHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/test");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }

    private static ProcessConfig newProcessConfig() {
        // 忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        // 忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        // 忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        return ProcessConfig.builder()
                // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                // 根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                // 根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                // 根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                // 忽略表名
                .ignoreTableName(ignoreTableName)
                // 忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                // 忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }
}
