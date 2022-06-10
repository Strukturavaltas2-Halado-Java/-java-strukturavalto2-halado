package usedcarsrestdemo.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usedcarsrestdemo.usedcars.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByBrand(String brand);
    List<Car> findAllByAgeIsLessThanEqual(int age);
    List<Car> findAllByBrandAndAgeIsLessThanEqual(String brand, int age);

}
