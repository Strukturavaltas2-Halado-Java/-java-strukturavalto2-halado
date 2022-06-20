package vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;

    private TimeMachine timeMachine;

    public void closeVote(Long id) {
        Vote vote = voteRepository.findById(id);
        LocalDateTime now = timeMachine.getNow();
        System.out.println("VOTE START: " + vote.getStartTime());
        System.out.println("NOW: " + now);
        System.out.println(Duration.between(vote.getStartTime(), now).toDays());


        if (Duration.between(vote.getStartTime(), now).toDays() < 3) {
            throw new IllegalArgumentException("Túl korán akarod a szavazást lezárni!");
        }

        vote.setEndTime(now);
    }
}
