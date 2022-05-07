package com.guang.yuantodo.utils;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class GeneratorCodeConfig {

    private static void fastAutoGeneratorCode() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/yuantodo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true", "root", "niepangg")
                .globalConfig(builder -> {
                    System.out.println(System.getProperty("user.dir"));
                    builder.author("王立广")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 设置父模块所在的位置

                })
                .packageConfig(builder -> {
                    // 设置父模块的名字
                    builder.parent("com.guang.yuantodo");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("todos").addTableSuffix("s");
                }).execute();
    }

    public static void main(String[] args) {
        fastAutoGeneratorCode();
    }
}
