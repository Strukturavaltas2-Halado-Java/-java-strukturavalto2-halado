package usedcarsrestdemo.usedcars.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CarNotFoundException extends AbstractThrowableProblem {
    public CarNotFoundException(long id) {
        super(URI.create("cars/not-found"), "Not found", Status.NOT_FOUND, String.format("Car not found: %d", id));
    }
}
