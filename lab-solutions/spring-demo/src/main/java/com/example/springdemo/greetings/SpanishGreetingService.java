package com.example.springdemo.greetings;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SpanishGreetingService implements GreetingService{
    @Override
    public String sayHello() {
        return "ola";
    }


}
