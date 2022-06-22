package sv2advfinalexam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudentCommand {
    @NotBlank(message = "Student name cannot be blank!")
    private String name;
    @Past (message = "Student date of birth must be in past!")
    private LocalDate dateOfBirth;
}
