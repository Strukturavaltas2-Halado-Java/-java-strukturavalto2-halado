package usedcarsrestdemo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarCommand {

    @NotBlank
    private String brand;
    @NotBlank
    private String type;
    @PositiveOrZero
    private int age;
    private CarCondition carCondition;
    @PositiveOrZero
    private int km;
}
