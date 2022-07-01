package com.wjy.generate;

import com.sun.codemodel.JCodeModel;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.net.URL;
import java.nio.file.Files;

/**
 * 根据json文件自动生成pojo对象文件
 *
 * @author weijiayu
 * @date 2022/7/01 00:01
 */
public class JsonObjectGenerator {

    public static void main(String[] args) throws Exception {
        JCodeModel codeModel = new JCodeModel();
        URL source = Example.class.getResource("/schema/required.json");
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
        };
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, "ClassName", "com.example", source);
        // 自动生成.java文件
        codeModel.build(Files.createTempDirectory("required").toFile());
    }

    private class Example {
    }
}
