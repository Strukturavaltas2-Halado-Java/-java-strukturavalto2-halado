package usedcarsrestdemo;

import lombok.*;

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

    public CarDto(String brand, String type, int age, CarCondition carCondition) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDto carDto = (CarDto) o;

        if (age != carDto.age) return false;
        if (brand != null ? !brand.equals(carDto.brand) : carDto.brand != null) return false;
        if (type != null ? !type.equals(carDto.type) : carDto.type != null) return false;
        return carCondition == carDto.carCondition;
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (carCondition != null ? carCondition.hashCode() : 0);
        return result;
    }
}
