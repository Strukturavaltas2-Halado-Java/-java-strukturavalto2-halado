package increment;

import org.springframework.stereotype.Service;

@Service
public class IncrementerService {

    private IncrementerRepository repository;

    public IncrementerService(IncrementerRepository repository) {
        this.repository = repository;
    }

    public int increment() {
        int number = repository.getCounter();
        number++;
        repository.setCounter(number);
        return number;
    }

    public void setCounter(int counter) {
        repository.setCounter(counter);
    }
}
