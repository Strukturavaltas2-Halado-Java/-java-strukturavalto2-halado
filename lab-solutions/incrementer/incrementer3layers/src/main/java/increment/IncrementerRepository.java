package increment;

import org.springframework.stereotype.Repository;

@Repository
public class IncrementerRepository {

    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
