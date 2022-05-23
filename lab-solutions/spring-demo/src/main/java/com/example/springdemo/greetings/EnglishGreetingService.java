package com.example.springdemo.greetings;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
public class EnglishGreetingService implements GreetingService {

    @Override
    public String sayHello() {
        return "Hello World!";
    }
}
