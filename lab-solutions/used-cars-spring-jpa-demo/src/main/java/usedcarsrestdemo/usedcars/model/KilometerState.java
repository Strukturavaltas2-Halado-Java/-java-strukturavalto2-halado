package usedcarsrestdemo.usedcars.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class KilometerState {

    private int km;
    private LocalDate dateOfCheck;
}
