package usedcarsrestdemo.usedcars.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="car_sellers")
public class CarSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="seller_name")
    private String sellerName;

    @OneToMany(mappedBy = "carSeller")
    private List<Car> cars = new ArrayList<>();

    public CarSeller(String sellerName) {
        this.sellerName = sellerName;
    }
}
