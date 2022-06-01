package springbikedemo.bikerental.model;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BikeRental {

    private int id;
    private String bikeId;
    private String userId;
    private LocalDateTime lastRentFinish;
    private double km;
}
