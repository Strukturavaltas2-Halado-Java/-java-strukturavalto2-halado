package vote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    VoteRepository voteRepository;

    @Test
    void testClose() {
        TimeMachine timeMachine = new TimeMachine();
        VoteService voteService = new VoteService(voteRepository, timeMachine);
        LocalDateTime now = LocalDateTime.parse("2022-06-01T12:00:00");
        when(voteRepository.findById(anyLong()))
                .thenReturn(new Vote(now));
        timeMachine.setNow(LocalDateTime.parse("2022-06-05T12:00:00"));
        voteService.closeVote(1L);
    }

    @Test
    void testCloseTooEarly() {
        TimeMachine timeMachine = new TimeMachine();
        timeMachine.setNow(LocalDateTime.parse("2022-06-02T12:00:00"));
        VoteService voteService = new VoteService(voteRepository, timeMachine);
        LocalDateTime now = LocalDateTime.parse("2022-06-01T12:00:00");
        when(voteRepository.findById(anyLong()))
                .thenReturn(new Vote(now));
        assertThrows(IllegalArgumentException.class,
                () -> voteService.closeVote(1L));

    }
}
