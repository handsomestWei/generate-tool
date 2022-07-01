package com.wjy.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据数据库表结构自动生成dao层代码
 *
 * @author weijiayu
 * @date 2022/6/25 10:45
 */
public class MyBatisPlusGenerator {


    public static void main(String[] args) {
        MyBatisPlusGeneratorConfig generatorConfig = newMyBatisPlusGeneratorConfig();
        // 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(newGlobalConfig(generatorConfig))
                .setCfg(newInjectionConfig())
                .setDataSource(newDataSourceConfig(generatorConfig))
                .setStrategy(newStrategyConfig(generatorConfig))
                .setPackageInfo(newPackageConfig(generatorConfig));
        // 执行操作
        ag.execute();
    }

    private static MyBatisPlusGeneratorConfig newMyBatisPlusGeneratorConfig() {
        return MyBatisPlusGeneratorConfig.builder()
                // 作者
                .author("wjy")
                // 生成文件输出绝对路径
                .outputDir("D:\\test")
                // 数据库连接
                .dbUrl("jdbc:postgresql://localhost:5432/test")
                //数据库用户
                .dbUser("postgres")
                // 数据库密码
                .dbPassword("postgres")
                // 要针对哪些表来自动生成
//                .tables(new String[]{"tb_config", "tb_device_data"})
                // 包名
                .packageName("com.wjy.test")
                .build();
    }

    // 数据源配置
    private static DataSourceConfig newDataSourceConfig(MyBatisPlusGeneratorConfig generatorConfig) {
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.POSTGRE_SQL)
                .setDriverName("org.postgresql.Driver")
                .setUrl(generatorConfig.getDbUrl())
                .setUsername(generatorConfig.getDbUser())
                .setPassword(generatorConfig.getDbPassword());
        return dsConfig;
    }

    // 策略配置
    private static StrategyConfig newStrategyConfig(MyBatisPlusGeneratorConfig generatorConfig) {
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setEntityBuilderModel(true)
                .setEntityColumnConstant(true)
                .setEntitySerialVersionUID(true)
                .setTablePrefix("tb_")
                .setInclude(generatorConfig.getTables());

        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(create_time);
        list.add(update_time);
        stConfig.setTableFillList(list);
        return stConfig;
    }

    // 包名策略配置
    private static PackageConfig newPackageConfig(MyBatisPlusGeneratorConfig generatorConfig) {
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(generatorConfig.getPackageName())
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");
        return pkConfig;
    }

    // 全局配置
    private static GlobalConfig newGlobalConfig(MyBatisPlusGeneratorConfig generatorConfig) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)
                .setAuthor(generatorConfig.getAuthor())
                .setOutputDir(generatorConfig.getOutputDir())
                .setFileOverride(true)
                .setIdType(IdType.UUID)
                .setDateType(DateType.ONLY_DATE)
                .setServiceName("%sService")
                .setEntityName("%sEntity")
                .setEnableCache(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true);
        return globalConfig;
    }

    private static InjectionConfig newInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        };
        return injectionConfig;
    }


    @Data
    @Builder
    private static class MyBatisPlusGeneratorConfig {

        /**
         * 生成文件输出文件夹.
         */
        private String outputDir;

        /**
         * 作者.
         */
        private String author;

        /**
         * 数据库连接.
         */
        private String dbUrl;

        /**
         * 数据库用户.
         */
        private String dbUser;

        /**
         * 数据库密码.
         */
        private String dbPassword;

        /**
         * 数据库表名称.
         */
        private String[] tables;

        /**
         * 包名.
         */
        private String packageName;
    }

}
