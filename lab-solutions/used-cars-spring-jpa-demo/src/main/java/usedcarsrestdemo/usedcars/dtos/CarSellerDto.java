package usedcarsrestdemo.usedcars.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarSellerDto {

    private Long id;
    private String sellerName;
    private List<CarDto> cars;
}
