package com.example.springdemo;

import com.example.springdemo.movies.MovieService;
import com.example.springdemo.greetings.EnglishGreetingService;
import com.example.springdemo.greetings.GreetingsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringDemoApplication.class, args);

        System.out.println(ctx.getBeansOfType(EnglishGreetingService.class));

        System.out.println(((GreetingsController)ctx.getBean("greetingsController")).sayHello());

        System.out.println(ctx.getBeansOfType(MovieService.class));
    }

    @Bean
    String createName(){
        return "This is a Controller!";
    }
    @Bean
    String createAnOtherName(){
        return "Other";
    }




}
