package springbikedemo.bikerental.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springbikedemo.bikerental.dtos.BikeRentalDTO;
import springbikedemo.bikerental.dtos.CreateBikeRentalCommand;
import springbikedemo.bikerental.model.BikeRental;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BikeService {

    AtomicInteger idGenerator = new AtomicInteger();

    private List<BikeRental> bikeRentals;
    private ModelMapper modelMapper;

    public BikeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<BikeRentalDTO> getAllRentals() {
        if (bikeRentals == null) {
            createAndUploadList();
        }
        return bikeRentals.stream()
                .map(rental->modelMapper.map(rental,BikeRentalDTO.class))
                .collect(Collectors.toList());
    }

    public Set<String> getUserIds() {
        if (bikeRentals == null) {
            createAndUploadList();
        }
        return bikeRentals.stream().map(BikeRental::getUserId)
                .collect(Collectors.toSet());
    }

    private void createAndUploadList() {
        bikeRentals = new ArrayList<>();
        loadBikeRentals();
    }

    private void loadBikeRentals() {
        try {
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/bikes.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                bikeRentals.add(processLine(line));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find file!");
        }
    }

    private BikeRental processLine(String line) {
        String[] temp = line.split(";");
        return new BikeRental(idGenerator.incrementAndGet(), temp[0], temp[1], LocalDateTime.parse(temp[2]), Double.parseDouble(temp[3]));
    }


    public BikeRental getRentalById(int id) {

        return bikeRentals.stream().filter(r->r.getId()==id).findFirst().orElseThrow(()->new IllegalArgumentException("Cannot find bike!"));
    }

    public BikeRentalDTO createBikeRental(CreateBikeRentalCommand bikeRentalCommand) {
        if (bikeRentals == null) {
            createAndUploadList();
        }
        BikeRental actual = modelMapper.map(bikeRentalCommand,BikeRental.class);
        actual.setId(idGenerator.incrementAndGet());
        bikeRentals.add(actual);
        return modelMapper.map(actual,BikeRentalDTO.class);
    }
}
