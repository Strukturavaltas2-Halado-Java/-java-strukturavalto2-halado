package usedcarsrestdemo;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {

    private CarService carService;


    @GetMapping
    public List<CarDto> getCars(@RequestParam Optional<String> brand,@RequestParam Optional<Integer> age, @RequestParam Optional<String> sort){
        return  carService.getCars(brand,age,sort);
    }

    @GetMapping("/brands")
    public Set<String> getCarBrands(){
        return carService.getCarBrands();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@RequestBody CreateCarCommand createCarCommand){
        return carService.createCar(createCarCommand);
    }

    @PostMapping("/{id}/kilometer-states")
    public CarDto addKilometerStateById(@PathVariable("id") long id, @RequestBody CreateKilometerStateCommand createKilometerStateCommand){
        return carService.addKilometerStateById(id, createKilometerStateCommand);
    }

}
