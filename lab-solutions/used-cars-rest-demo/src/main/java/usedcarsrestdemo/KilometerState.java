package usedcarsrestdemo;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class KilometerState {

    private int km;
    private LocalDate dateOfCheck;
}
