package springbikedemo.bikerental.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class CreateBikeRentalCommand {

    private String bikeId;
    private String userId;
    private LocalDateTime lastRentFinish;
    private double km;
}
