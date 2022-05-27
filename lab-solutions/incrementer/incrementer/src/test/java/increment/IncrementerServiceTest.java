package increment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementerServiceTest {

    @Test
    void testIncrement() {
        IncrementerService service = new IncrementerService();
        int expected = service.increment();

        assertEquals(1, expected);
    }
}