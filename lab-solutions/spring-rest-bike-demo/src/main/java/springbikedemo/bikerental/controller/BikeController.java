package springbikedemo.bikerental.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springbikedemo.bikerental.dtos.BikeRentalDTO;
import springbikedemo.bikerental.service.BikeService;
import springbikedemo.bikerental.dtos.CreateBikeRentalCommand;
import springbikedemo.bikerental.model.BikeRental;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/admin/api/bikerental")
public class BikeController {

    private BikeService service;


    @GetMapping("/rentals")
    public List<BikeRentalDTO> getAllRentals(){
        return service.getAllRentals();
    }

    @GetMapping("/users")
    public Set<String> getUserIds(){
        return service.getUserIds();
    }

    @GetMapping("/rentals/{id}")
    public BikeRental getRentalById(@PathVariable("id") int id){
        return service.getRentalById(id);
    }

    @PostMapping("/rentals")
    public BikeRentalDTO createRental(CreateBikeRentalCommand bikeRentalCommand){
        return service.createBikeRental(bikeRentalCommand);
    }




}
