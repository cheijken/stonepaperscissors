package rps.app;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import rps.app.game.Game;
import rps.app.game.Game.State;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

public class SpawnGameServiceTest {

	private SpawnGameService underTest = new SpawnGameService();

	private PlayersStack playersAvailable;

	@Before
	public void setUp() throws Exception {
		playersAvailable = PlayersStack.getInstance();
	}

	@Test
	public void responseIsPlayerWithWaitStateWhenOnlyOneUser() throws Exception {
		// Given
		Player firstPlayer = new Player("Player1");
		playersAvailable.push(firstPlayer);

		// When
		Response spawnResponse = underTest.spawnGame(playersAvailable);

		//Then
		assertNotNull(spawnResponse);
		assertThat(spawnResponse.getState(), is(Player.State.WAIT));

		clearPlayersStack();

	}

	private void clearPlayersStack() {playersAvailable.pop();}

	@Test
	public void responseIsGameWithWAITStateWhenTwoPlayersWithWAIT() throws Exception {
		// Given
		Player firstPlayer = new Player("Player1");
		playersAvailable.push(firstPlayer);
		Player secondPlayer = new Player("Player2");
		playersAvailable.push(secondPlayer);

		// When
		Response spawnResponse = underTest.spawnGame(playersAvailable);

		//Then
		assertNotNull(spawnResponse);
		assertThat(spawnResponse.getState(), is(State.WAIT));
	}

	@Test
	public void firstGameShouldBeReadySecondWaitForTotalThreePlayersInTheSystem() throws Exception {
		// Given
		Player firstPlayer = new Player("Player1");
		playersAvailable.push(firstPlayer);
		Player secondPlayer = new Player("Player2");
		playersAvailable.push(secondPlayer);
		Player thirdPlayer = new Player("Player3");
		playersAvailable.push(thirdPlayer);

		// When
		Response spawnGame1Response = underTest.spawnGame(playersAvailable);
		Response spawnGame2Response = underTest.spawnGame(playersAvailable);

		//Then
		assertNotNull(spawnGame1Response);
		assertThat(spawnGame1Response.getState(), is(Game.State.WAIT));
		assertNotNull(spawnGame2Response);
		assertThat(spawnGame2Response.getState(), is(Player.State.WAIT));
	}

}