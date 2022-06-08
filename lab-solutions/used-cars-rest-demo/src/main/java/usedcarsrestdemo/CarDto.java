package usedcarsrestdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDto {

    private Long id;
    private String brand;
    private String type;
    private int age;
    private CarCondition carCondition;
    private List<KilometerStateDto> kilometerStates;
}
