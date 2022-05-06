package team;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamPlayerServiceTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    TeamPlayerService service;

    @Test
    void testNotEnoughBudget() {
        when(teamRepository.findTeamById(2L)).thenReturn(new Team("Arsenal", "x", TeamClass.I, 100));
        Player john = new Player("John Doe", 100_000);
        john.setTeam(new Team("Chelsae", "x", TeamClass.I, 900));
        when(playerRepository.findById(1L)).thenReturn(john);

        assertThrows(NotEnoughBudgetException.class,
                () -> service.transferPlayer(2L, 1L));
    }
}
