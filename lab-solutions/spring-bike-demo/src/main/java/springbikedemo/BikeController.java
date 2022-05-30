package springbikedemo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class BikeController {

    private BikeService service;


    @GetMapping("/rentals")
    public List<BikeRental> getAllRentals(){
        return service.getAllRentals();
    }

    @GetMapping("/users")
    public Set<String> getUserIds(){
        return service.getUserIds();
    }


}
