package com.ghtk.onlinebiddingproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafTemplateConfiguration {
     @Bean
    public ITemplateResolver templateResolver()
     {
         ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
         templateResolver.setPrefix("templates/");
         templateResolver.setSuffix(".html");
         templateResolver.setTemplateMode("HTML");
         templateResolver.setCharacterEncoding("UTF-8");
         return templateResolver;
     }

     @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver templateResolver)
     {
         SpringTemplateEngine templateEngine = new SpringTemplateEngine();
         templateEngine.setTemplateResolver(templateResolver);
         return templateEngine;
     }


}
