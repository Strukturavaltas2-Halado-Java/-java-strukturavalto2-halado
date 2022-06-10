package usedcarsrestdemo.usedcars.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import usedcarsrestdemo.usedcars.dtos.CarSellerDto;
import usedcarsrestdemo.usedcars.dtos.CreateCarCommand;
import usedcarsrestdemo.usedcars.dtos.CreateCarSellerCommand;
import usedcarsrestdemo.usedcars.model.CarSeller;
import usedcarsrestdemo.usedcars.service.CarSellingService;

@RestController
@RequestMapping("api/car-sellers")
@AllArgsConstructor
public class CarSellerController {

    private CarSellingService carSellingService;

    @PostMapping
    public CarSellerDto createCarSeller(@RequestBody CreateCarSellerCommand createCarSellerCommand){
        return carSellingService.createCarSeller(createCarSellerCommand);
    }

    @PostMapping("/{id}/cars")
    private CarSellerDto addCarToCarSeller(@PathVariable("id") long id, @RequestBody CreateCarCommand createCarCommand){
        return null;
    }

}
