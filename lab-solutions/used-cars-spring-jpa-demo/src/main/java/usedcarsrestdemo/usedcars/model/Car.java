package usedcarsrestdemo.usedcars.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String type;
    private int age;
    @Column(name = "car_condition")
    @Enumerated(EnumType.STRING)
    private CarCondition carCondition;
    @ElementCollection
    private List<KilometerState> kilometerStates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="car_seller_id")
    private CarSeller carSeller;

    public Car(Long id, String brand, String type, int age, CarCondition carCondition) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;
    }

    public Car(String brand, String type, int age, CarCondition carCondition) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;
    }

    public void addKilometerState(KilometerState kilometerState) {
        kilometerStates.add(kilometerState);
    }
}
