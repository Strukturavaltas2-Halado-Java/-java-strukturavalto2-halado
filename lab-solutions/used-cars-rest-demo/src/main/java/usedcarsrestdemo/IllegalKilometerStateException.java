package usedcarsrestdemo;

import org.springframework.http.HttpStatus;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class IllegalKilometerStateException extends AbstractThrowableProblem {
    public IllegalKilometerStateException(int km) {
        super(URI.create("cars/illegal-kilometer-state"),"Illegal kilometer state", Status.NOT_ACCEPTABLE, String.format("Not ok to turn back th km!"));
    }
}
