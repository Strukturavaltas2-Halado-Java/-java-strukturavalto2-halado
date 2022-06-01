package springbikedemo.bikerental.controller;


import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import springbikedemo.bikerental.dtos.BikeRentalDTO;
import springbikedemo.bikerental.service.BikeService;
import springbikedemo.bikerental.dtos.CreateBikeRentalCommand;
import springbikedemo.bikerental.model.BikeRental;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/rentals")
public class BikeController {

    private BikeService service;

    @GetMapping
    public List<BikeRentalDTO> getAllRentals(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> startTime){
        return service.getAllRentals(startTime);
    }

    @GetMapping("/users")
    public Set<String> getUserIds(){
        return service.getUserIds();
    }

    @GetMapping("/{id}")
    public BikeRental getRentalById(@PathVariable("id") int id){
        return service.getRentalById(id);
    }

    @PostMapping
    public BikeRentalDTO createRental(@RequestBody CreateBikeRentalCommand bikeRentalCommand){
        return service.createBikeRental(bikeRentalCommand);
    }
}