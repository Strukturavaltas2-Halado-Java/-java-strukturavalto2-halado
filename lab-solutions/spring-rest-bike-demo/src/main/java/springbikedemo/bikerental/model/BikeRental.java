package springbikedemo.bikerental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class BikeRental {

    private int id;
    private String bikeId;
    private String userId;
    private LocalDateTime lastRentFinish;
    private double km;


}
