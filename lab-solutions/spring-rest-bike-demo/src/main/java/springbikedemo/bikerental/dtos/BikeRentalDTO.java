package springbikedemo.bikerental.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeRentalDTO {

    private int id;
    private String bikeId;
    private String userId;
    private LocalDateTime lastRentFinish;
    private double km;

}
