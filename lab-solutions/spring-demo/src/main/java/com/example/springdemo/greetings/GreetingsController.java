package com.example.springdemo.greetings;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingsController {


    private GreetingService service;

    public GreetingsController(@Qualifier("englishGreetingService") GreetingService service) {
        this.service = service;
    }

    public String sayHello(){
      return service.sayHello();
    }

    public GreetingService getService() {
        return service;
    }


}
