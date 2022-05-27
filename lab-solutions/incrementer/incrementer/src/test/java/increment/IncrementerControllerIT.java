package increment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncrementerControllerIT {

    @Autowired
    IncrementerController controller;

    @Autowired
    IncrementerService service;

    @BeforeEach
    void init() {
        service.setCounter(0);
    }

    @Test
    void testIncrement() {
        int expected = controller.increment();

        assertEquals(1, expected);
    }

//    @Test
//    void testIncrement2() {
//        int expected = controller.increment();
//
//        assertEquals(1, expected);
//    }
}