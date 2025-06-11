package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BeansApplication {

    public static void main(String[] args) {
      ApplicationContext context = SpringApplication.run(BeansApplication.class, args);
      List<String> li = Arrays.asList(context.getBeanDefinitionNames());
      System.out.println(li.toArray().length);
    }
} 