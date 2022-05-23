package com.example.springdemo.greetings;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class HungarianGreetingService implements GreetingService {


    @Override
    public String sayHello() {
        return "Hello Világ!";
    }
}
