package es.gfi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//https://www.agilegroup.co.jp/technote/springboot-fileupload-error-handling.html
//@SpringBootApplication
//public class SpringBootWebApplication {
//
//    private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringBootWebApplication.class, args);
//    }
//
//}

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootWebApplication extends SpringBootServletInitializer {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }
}