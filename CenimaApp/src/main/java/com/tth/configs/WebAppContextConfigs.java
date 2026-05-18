/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tth.controller",
    "com.tth.repository",
    "com.tth.service"
})
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public SpringResourceTemplateResolver templateResolver(
//            ApplicationContext applicationContext) {
//
//        SpringResourceTemplateResolver resolver
//                = new SpringResourceTemplateResolver();
//
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("templates/");
//        resolver.setSuffix(".html");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setTemplateMode("HTML");
//
//        return resolver;
//    }

//    @Bean
//    public SpringTemplateEngine templateEngine(
//            ApplicationContext applicationContext) {
//
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//
//        engine.setTemplateResolver(
//                templateResolver(applicationContext));
//
//        engine.setEnableSpringELCompiler(true);
//
//        return engine;
//    }

//    @Bean
//    public ViewResolver viewResolver(
//            ApplicationContext applicationContext) {
//
//        ThymeleafViewResolver resolver
//                = new ThymeleafViewResolver();
//
//        resolver.setTemplateEngine(
//                templateEngine(applicationContext));
//
//        resolver.setCharacterEncoding("UTF-8");
//
//        return resolver;
//    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

//    @Bean
//    public Cloudinary cloudinary() {
//        return new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dxxwcby8l",
//                "api_key", "792844686918347",
//                "api_secret", "T8ys_Z9zaKSqmKWa4K1RY6DXUJg",
//                "secure", true
//        ));
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/CSS/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("/resources/images/");
    }
}