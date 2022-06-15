package usedcarsrestdemo.usedcars.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedcarsrestdemo.usedcars.dtos.*;
import usedcarsrestdemo.usedcars.exceptions.CarNotFoundException;
import usedcarsrestdemo.usedcars.exceptions.IllegalKilometerStateException;
import usedcarsrestdemo.usedcars.model.Car;
import usedcarsrestdemo.usedcars.model.CarSeller;
import usedcarsrestdemo.usedcars.model.KilometerState;
import usedcarsrestdemo.usedcars.repository.CarSellerRepository;
import usedcarsrestdemo.usedcars.repository.CarsRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarSellingService {

    private CarsRepository carsRepository;
    private CarSellerRepository carSellerRepository;

    private ModelMapper modelMapper;

    public CarSellingService(CarsRepository carsRepository, CarSellerRepository carSellerRepository, ModelMapper modelMapper) {
        this.carsRepository = carsRepository;
        this.carSellerRepository = carSellerRepository;
        this.modelMapper = modelMapper;
    }

    public CarDto createCar(CreateCarCommand createCarCommand) {
        Car car = new Car(createCarCommand.getBrand(), createCarCommand.getType(), createCarCommand.getAge(), createCarCommand.getCarCondition());
        car.addKilometerState(new KilometerState(createCarCommand.getKm(), LocalDate.now()));
        carsRepository.save(car);
        return modelMapper.map(car, CarDto.class);
    }

    public List<CarDto> getCars(Optional<String> brand, Optional<Integer> age, Optional<String> sort) {
        List<Car> result = carsRepository.findAllByBrandAndAgeIsLessThanEqual(brand, age);

        List<CarDto> resultDto = result.stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());

        if(sort.isPresent()){
            if(sort.get().equalsIgnoreCase("asc")){
                return sortCarsAscending(resultDto);
            }
            if(sort.get().equalsIgnoreCase("desc")){
                return sortCarsDescending(resultDto);
            }
            throw new IllegalArgumentException("Sorting is illegal!");
        }
        return resultDto;

    }

    private List<CarDto> sortCarsAscending(List<CarDto> result) {
        return result.stream()
                .sorted(Comparator.comparingInt(c -> c.getKilometerStates().get(c.getKilometerStates().size() - 1).getKm()))
                .collect(Collectors.toList());
    }

    private List<CarDto> sortCarsDescending(List<CarDto> result) {
        return result.stream()
                .sorted(Comparator.comparingInt(c -> ((CarDto) c).getKilometerStates().get(((CarDto) c).getKilometerStates().size() - 1).getKm()).reversed())
                .collect(Collectors.toList());
    }

    public Set<String> getCarBrands() {
        return carsRepository.getAllBrands();

    }

    private Car findCarById(long id) {
        Optional<Car> car = carsRepository.findById(id);
        if(car.isEmpty()){
            throw new CarNotFoundException(id);
        }
        return car.get();
    }

    public CarDto addKilometerStateById(long id, CreateKilometerStateCommand createKilometerStateCommand) {
        Car car = findCarById(id);
        KilometerState kilometerState = new KilometerState(createKilometerStateCommand.getKm(), LocalDate.now());
        if (kilometerState.getKm() < car.getKilometerStates().get(car.getKilometerStates().size() - 1).getKm()) {
            throw new IllegalKilometerStateException(kilometerState.getKm());
        }
        car.addKilometerState(kilometerState);
        return modelMapper.map(car, CarDto.class);
    }

    public CarSellerDto createCarSeller(CreateCarSellerCommand createCarSellerCommand) {
        CarSeller carSeller = new CarSeller(createCarSellerCommand.getSellerName());
        carSellerRepository.save(carSeller);
        return modelMapper.map(carSeller,CarSellerDto.class);
    }
}
