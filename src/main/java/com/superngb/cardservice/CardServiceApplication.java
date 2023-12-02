package com.superngb.cardservice;

import com.superngb.cardservice.config.ComponentScanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.superngb.cardservice.entity")
@EnableJpaRepositories("com.superngb.cardservice.repository")
@Import(ComponentScanConfig.class)
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

//    @Bean
//    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
//        return beanFactory -> {
//            genericApplicationContext((BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
//        };
//    }
//
//    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
//        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
//        beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
//        beanDefinitionScanner.scan("com.superngb.cardservice");
//    }
//
//    static TypeFilter removeModelAndEntitiesFilter() {
//        return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
//                .getClassName()
//                .endsWith("Model");
//    }

}
