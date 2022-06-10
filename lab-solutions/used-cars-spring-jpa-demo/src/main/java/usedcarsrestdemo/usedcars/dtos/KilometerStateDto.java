package usedcarsrestdemo.usedcars.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KilometerStateDto {

    private int km;
    private LocalDate dateOfCheck;
}
