package usedcarsrestdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateKilometerStateCommand {

    @Positive
    private int km;
}
