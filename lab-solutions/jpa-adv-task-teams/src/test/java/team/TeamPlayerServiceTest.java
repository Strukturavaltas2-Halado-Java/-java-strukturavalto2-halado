package team;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class TeamPlayerServiceTest {


    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    TeamRepository teamRepository = new TeamRepository(factory);
    PlayerRepository playerRepository = new PlayerRepository(factory);
    TeamPlayerService service = new TeamPlayerService(teamRepository,playerRepository);


    @Test
    void testTransfer(){
        Team team = teamRepository.saveTeam(new Team("Chelsae","England",TeamClass.I,10_000_000));
        Player player = playerRepository.savePlayer(new Player("John",120));

        service.transferPlayer(team.getId(),player.getId());

        Team team2 = teamRepository.saveTeam(new Team("Arsenal","England",TeamClass.I,10_000_000));

        service.transferPlayer(team2.getId(),player.getId());

        Team team3 =teamRepository.saveTeam(new Team("Vasas","Hungary",TeamClass.II,10_000));

        service.transferPlayer(team3.getId(), player.getId());
    }


}