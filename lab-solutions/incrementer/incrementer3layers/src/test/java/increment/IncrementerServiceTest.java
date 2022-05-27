package increment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncrementerServiceTest {

    @Mock
    IncrementerRepository repository;

    @InjectMocks
    IncrementerService service;

    @Test
    void testIncrement() {
        when(repository.getCounter()).thenReturn(2);
        int expected = service.increment();

        verify(repository).setCounter(3);
        assertEquals(3, expected);
    }
}