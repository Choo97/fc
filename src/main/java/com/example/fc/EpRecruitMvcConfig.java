package com.example.fc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class EpRecruitMvcConfig implements WebMvcConfigurer {
    @Value("${getEpRecruitPath}")
    String getEpRecruitPath;
    @Value("${getEpRecruitThumbnailPath}")
    String getEpRecruitThumbnailPath;
    @Value("${getEpRecruitMainThumbnailPath}")
    String getEpRecruitMainThumbnailPath;
    @Value("${uploadEpRecruit}")
    String uploadEpRecruit;
    @Value("${getEpRecruitContentPath}")
    String getEpRecruitContentPath;
    @Value("${uploadEpRecruitContent}")
    String uploadEpRecruitContent;

    @Override
//    프로젝트경로 외부에서 디렉토리 접근하는법
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadEpRecruit).addResourceLocations(getEpRecruitMainThumbnailPath);
        registry.addResourceHandler(uploadEpRecruitContent).addResourceLocations(getEpRecruitContentPath); //에디터사진업로드경로
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setDefaultEncoding("UTF-8");
//        commonsMultipartResolver.setMaxUploadSize(50 * 1024 * 1024);
//        return commonsMultipartResolver;
//    }
}



