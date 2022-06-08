package usedcarsrestdemo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarCommand {

    private String brand;
    private String type;
    private int age;
    private CarCondition carCondition;
    private int km;
}
