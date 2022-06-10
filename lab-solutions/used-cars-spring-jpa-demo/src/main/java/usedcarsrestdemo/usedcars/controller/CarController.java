package usedcarsrestdemo.usedcars.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usedcarsrestdemo.usedcars.service.CarSellingService;
import usedcarsrestdemo.usedcars.dtos.CarDto;
import usedcarsrestdemo.usedcars.dtos.CreateCarCommand;
import usedcarsrestdemo.usedcars.dtos.CreateKilometerStateCommand;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

    private CarSellingService carSellingService;

    @GetMapping
    public List<CarDto> getCars(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> age, @RequestParam Optional<String> sort){
        return  carSellingService.getCars(brand,age,sort);
    }

    @GetMapping("/brands")
    public Set<String> getCarBrands(){
        return carSellingService.getCarBrands();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@Valid @RequestBody CreateCarCommand createCarCommand){
        return carSellingService.createCar(createCarCommand);
    }

    @PostMapping("/{id}/kilometer-states")
    public CarDto addKilometerStateById(@PathVariable("id") long id, @Valid @RequestBody CreateKilometerStateCommand createKilometerStateCommand){
        return carSellingService.addKilometerStateById(id, createKilometerStateCommand);
    }

}
