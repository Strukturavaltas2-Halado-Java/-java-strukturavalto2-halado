package usedcarsrestdemo.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usedcarsrestdemo.usedcars.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {

    @Query("select  c from Car c where (:brand is null or c.brand = :brand) and (:age is null or c.age<=:age)")
    List<Car> findAllByBrandAndAgeIsLessThanEqual(@Param("brand") Optional<String> brand,@Param("age") Optional<Integer> age);


    @Query("select c.brand from Car c")
    Set<String> getAllBrands();

}
