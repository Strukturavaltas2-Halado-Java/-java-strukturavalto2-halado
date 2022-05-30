package springbikedemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class BikeRental {

    private String bikeId;
    private String userId;
    private LocalDateTime lastRentFinish;
    private double km;


}
