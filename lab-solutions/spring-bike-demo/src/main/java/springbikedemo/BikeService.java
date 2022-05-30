package springbikedemo;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<BikeRental> bikeRentals;


    public List<BikeRental> getAllRentals() {
        if (bikeRentals == null) {
            createAndUploadList();
        }
        return bikeRentals;
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
        return new BikeRental(temp[0], temp[1], LocalDateTime.parse(temp[2]), Double.parseDouble(temp[3]));
    }


}
