package com.example.springdemo.greetings;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.Map;


@Controller
public class GreetingsController {


    private Map<String,GreetingService> greetings;

    public GreetingsController(Map<String, GreetingService> greetings) {
        this.greetings = greetings;
    }

    //    public GreetingsController(GreetingService service) {
//        this.service = service;
//    }

    public String sayHello(String type){
      GreetingService service = greetings.get(type);
      return service.sayHello();
    }

//    public GreetingService getService() {
//        return service;
//    }


    public Map<String, GreetingService> getGreetings() {
        return greetings;
    }
}
