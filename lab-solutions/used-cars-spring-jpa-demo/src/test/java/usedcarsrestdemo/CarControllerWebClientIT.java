package usedcarsrestdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import usedcarsrestdemo.usedcars.dtos.CarDto;
import usedcarsrestdemo.usedcars.dtos.CreateCarCommand;
import usedcarsrestdemo.usedcars.model.CarCondition;

import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerWebClientIT {

    @Autowired
    WebTestClient webTestClient;


    @Test
    void testCreateCar() {

        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CarDto.class)
                .value(carDto -> assertThat(carDto.getBrand()).isEqualTo("Suzuki"));

        EntityExchangeResult<CarDto> result = webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CarDto.class)
                .returnResult();

        assertThat(result.getResponseBody().getBrand()).isEqualTo("Suzuki");

    }


    @Test
    void testGetCarsWithBrand() {
        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange();

        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Toyota", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange();


        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("api/cars").queryParam("brand", "Toyota").build())
                .exchange()
                .expectBodyList(CarDto.class).hasSize(2).contains(
                new CarDto("Toyota", "Swift", 12, CarCondition.EXCELLENT));
    }

    @Test
    void testGetCarBrands() {

        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange();

        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Toyota", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange();

        webTestClient.get()
                .uri("api/cars/brands")
                .exchange()
                .expectBody(TreeSet.class)
                .value(t -> assertThat(t).contains("Toyota","Suzuki"));


    }
}