package increment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementerController {

    private IncrementerService incrementerService;

    public IncrementerController(IncrementerService incrementerService) {
        this.incrementerService = incrementerService;
    }

    @GetMapping("/")
    public int increment() {
        return incrementerService.increment();
    }
}
