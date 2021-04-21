package com.du.dylan.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
/**
 * 集成swagger2  解决前后端分离 弊端：不能及时协商+尽早解决的问题
 *      使用swagger总结:
 *          通过swagger 给一些比基奥难理解的接口或属性，增加注释信息
 *          接口文档实时更新
 *          可以在线测试
 *      安全问题:
 *          正式上线的时候  记得关闭swagger
 */
@Configuration//加载到springboot配置里面
@EnableSwagger2WebMvc//开启swagger2
public class SwaggerConfig {

    /**
     * 配置swagger2
     * 注册一个bean属性
     * swagger2其实就是重新写一个构造器，因为他没有get set方法\
     * enable() 是否启用swagger false swagger不能再浏览器中访问
     * groupName()配置api文档的分组  那就注册多个Docket实例 相当于多个分组
     * @return
     */
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Du.Dylan RESTful APIs")
                        .description("#    Du.Dylan")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact("du.dylan@iac.com.tw")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.du.dylan.shiroweb.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
