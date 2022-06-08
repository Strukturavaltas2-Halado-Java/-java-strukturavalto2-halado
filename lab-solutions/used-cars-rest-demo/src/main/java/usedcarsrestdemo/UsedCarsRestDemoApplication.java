package usedcarsrestdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsedCarsRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsedCarsRestDemoApplication.class, args);
    }

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

}
