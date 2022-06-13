package usedcarsrestdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import usedcarsrestdemo.usedcars.dtos.CarDto;
import usedcarsrestdemo.usedcars.dtos.CreateCarCommand;
import usedcarsrestdemo.usedcars.dtos.CreateKilometerStateCommand;
import usedcarsrestdemo.usedcars.dtos.KilometerStateDto;
import usedcarsrestdemo.usedcars.model.CarCondition;

import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from car_kilometer_states","delete from cars","delete from car_sellers"})
class CarControllerWebClientIT {

    @Autowired
    WebTestClient webTestClient;

    CarDto carDto;

    @BeforeEach
    void init(){
       carDto = webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange()
                .expectBody(CarDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    void testCreateCar() {

        webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, 123_000))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CarDto.class)
                .value(carDto -> assertThat(carDto.getBrand()).isEqualTo("Suzuki"));

        assertThat(carDto.getBrand()).isEqualTo("Suzuki");

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


    @Test
    void testCarNotFound(){
        Problem p = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("api/cars/{id}/kilometer-states").build(2))
                .bodyValue(new CreateKilometerStateCommand(60))
                .exchange()
                .expectBody(Problem.class).returnResult().getResponseBody();

        assertEquals("Car not found: 2",p.getDetail());
    }

    @Test
    void testAddKilometerStateToCar(){
            CarDto carWithNewKm=webTestClient.post()
                    .uri(uriBuilder -> uriBuilder.path("api/cars/{id}/kilometer-states").build(carDto.getId()))
                    .bodyValue(new CreateKilometerStateCommand(125_000))
                    .exchange()
                    .expectBody(CarDto.class)
                    .returnResult().getResponseBody();

            assertThat(carWithNewKm.getKilometerStates()).hasSize(2).extracting(KilometerStateDto::getKm).contains(123_000,125_000);

    }

    @Test
    void createCarWithWrongKm(){
        ConstraintViolationProblem cvp = webTestClient.post()
                .uri("api/cars")
                .bodyValue(new CreateCarCommand("Suzuki", "Swift", 12, CarCondition.EXCELLENT, -100))
                .exchange()
                .expectBody(ConstraintViolationProblem.class).returnResult().getResponseBody();
        assertEquals("must be positive",cvp.getViolations().get(0).getMessage());
    }
}