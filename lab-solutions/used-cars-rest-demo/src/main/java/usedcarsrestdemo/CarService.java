package usedcarsrestdemo;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CarService {

    private AtomicLong idGenerator = new AtomicLong();
    private List<Car> cars = new ArrayList<>();

    private ModelMapper modelMapper;

    public CarService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarDto createCar(CreateCarCommand createCarCommand) {
        Car car = new Car(idGenerator.incrementAndGet(), createCarCommand.getBrand(), createCarCommand.getType(), createCarCommand.getAge(), createCarCommand.getCarCondition());
        car.addKilometerState(new KilometerState(createCarCommand.getKm(), LocalDate.now()));
        cars.add(car);
        return modelMapper.map(car, CarDto.class);
    }

    public List<CarDto> getCars(Optional<String> brand, Optional<Integer> age, Optional<String> sort) {
        System.out.println(brand.get());
        List<CarDto> result = cars.stream()
                .filter(c -> brand.isEmpty() || c.getBrand().equalsIgnoreCase(brand.get()))
                .filter(c -> age.isEmpty() || c.getAge() <= age.get())
                .map(c -> modelMapper.map(c, CarDto.class))
                .collect(Collectors.toList());

        if (sort.isPresent() && sort.get().equals("asc")) {
            return sortCarsAscending(result);
        }
        if (sort.isPresent() && sort.get().equals("desc")) {
            return sortCarsDescending(result);
        }

        return result;


    }

    private List<CarDto> sortCarsDescending(List<CarDto> result) {
        return result.stream()
                .sorted(Comparator.comparingInt(c -> c.getKilometerStates().get(c.getKilometerStates().size() - 1).getKm()))
                .collect(Collectors.toList());
    }

    private List<CarDto> sortCarsAscending(List<CarDto> result) {
        return result.stream()
                .sorted(Comparator.comparingInt(c -> ((CarDto) c).getKilometerStates().get(((CarDto) c).getKilometerStates().size() - 1).getKm()).reversed())
                .collect(Collectors.toList());
    }

    public Set<String> getCarBrands() {
        return cars.stream()
                .map(Car::getBrand)
                .collect(Collectors.toCollection(TreeSet::new));

    }

    private Car findById(long id){
        return cars.stream().filter(c->c.getId()==id)
                .findFirst().orElseThrow(()->new CarNotFoundException(id));
    }

    public CarDto addKilometerStateById(long id, CreateKilometerStateCommand createKilometerStateCommand) {
        Car car = findById(id);
        KilometerState kilometerState = new KilometerState(createKilometerStateCommand.getKm(), LocalDate.now());
        if(kilometerState.getKm()<car.getKilometerStates().get(car.getKilometerStates().size()-1).getKm()){
            throw new IllegalKilometerStateException(kilometerState.getKm());
        }
        car.addKilometerState(kilometerState);
        return modelMapper.map(car,CarDto.class);
    }
}
