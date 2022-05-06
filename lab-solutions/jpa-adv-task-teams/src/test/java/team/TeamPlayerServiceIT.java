package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamPlayerServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    TeamRepository teamRepository = new TeamRepository(factory);

    PlayerRepository playerRepository = new PlayerRepository(factory);

    TeamPlayerService service = new TeamPlayerService(teamRepository,playerRepository);

    Team chelsea;

    Player john;

    Team arsenal;

    @BeforeEach
    void initData() {
        chelsea = teamRepository.saveTeam(new Team("Chelsae","England",TeamClass.I,10_000_000));
        john = playerRepository.savePlayer(new Player("John",100_000));
        service.transferPlayer(chelsea.getId(),john.getId());
        arsenal = teamRepository.saveTeam(new Team("Arsenal","England",TeamClass.I,10_000_000));
    }

    @Test
    void testTransfer() {
        // When
        service.transferPlayer(arsenal.getId(), john.getId());
        // Than
        john = playerRepository.findById(john.getId());
        assertEquals("Arsenal", john.getTeam().getName());
    }

    @Test
    void testBudgetTransfer() {
        // When
        service.transferPlayer(arsenal.getId(), john.getId());
        chelsea = teamRepository.findTeamById(chelsea.getId());
        // Than
        assertEquals(10_100_000, chelsea.getBudget());
        arsenal = teamRepository.findTeamById(arsenal.getId());
        assertEquals(9_900_000, arsenal.getBudget());
    }



}