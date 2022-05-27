package increment;

import org.springframework.stereotype.Service;

@Service
public class IncrementerService {

    private int counter;

    public int increment() {
        counter++;
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
