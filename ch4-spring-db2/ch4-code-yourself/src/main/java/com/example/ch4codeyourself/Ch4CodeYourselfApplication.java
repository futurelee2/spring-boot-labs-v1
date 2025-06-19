package com.example.ch4codeyourself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.ch4codeyourself.v2.repository")
//@EntityScan(basePackages = "com.example.ch4codeyourself.v2")
//public class Ch4CodeYourselfApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Ch4CodeYourselfApplication.class, args);
//    }
//
//}
//


@SpringBootApplication(scanBasePackages = "com.example.ch4codeyourself.v3") // 패키지 있는 애들만 등록시키기 위해
@EnableJpaRepositories(basePackages = "com.example.ch4codeyourself.v3")
@EntityScan(basePackages = "com.example.ch4codeyourself.v3")
public class Ch4CodeYourselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch4CodeYourselfApplication.class, args);
    }

}