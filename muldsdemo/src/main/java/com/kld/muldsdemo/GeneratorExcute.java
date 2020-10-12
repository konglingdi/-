package com.kld.muldsdemo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName GeneratorExcute
 * @date: 2020.08.31 14:46
 * @Author: 孔令迪
 */
public class GeneratorExcute {
    public static void main(String[] args) {
        String path = GeneratorExcute.class.getClassLoader().getResource("generatorConfig.xml").getPath();
        //由于绝对路径中出现了%20代替空格，所以替换一下空格
        path = path.replaceAll("%20", " ");
        List<String> listStr = new ArrayList<String>();
        try {
            ConfigurationParser cp = new ConfigurationParser(listStr);
            Configuration config = cp.parseConfiguration(new File(path));
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, listStr);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
