package usedcarsrestdemo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {

    private Long id;
    private String brand;
    private String type;
    private int age;
    private CarCondition carCondition;
    private List<KilometerState> kilometerStates = new ArrayList<>();

    public Car(Long id, String brand, String type, int age, CarCondition carCondition) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;
    }

    public void addKilometerState(KilometerState kilometerState){
        kilometerStates.add(kilometerState);
    }
}
