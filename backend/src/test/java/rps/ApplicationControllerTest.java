package rps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rps.app.PlayActionsService;
import rps.app.Response;
import rps.app.SpawnGameService;
import rps.app.game.Game;
import rps.app.game.Game.State;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

public class ApplicationControllerTest {

	private ApplicationController underTest;

	private SpawnGameService   spawnGameService = new SpawnGameService();
	private PlayActionsService gamePlay         = new PlayActionsService();

	private PlayersStack playersAvailable;

	@Before
	public void setUp() throws Exception {
		playersAvailable = PlayersStack.getInstance();
	}

	@Test
	public void checkGameShouldReturnGameWhenSessionExists() throws Exception {

		underTest = new ApplicationController(spawnGameService, gamePlay);

		Response response = spawnGameForTest("Player1", "Player2");

		assertThat(response.getState(), is(State.WAIT));
		assertTrue(response instanceof Game);
		Game newGame = (Game) response;
		assertThat(underTest.checkGame(newGame.getSessionId()), is(newGame));

	}

	@Test
	public void checkGameShouldReturnInvalidResponseWhenSessionNotExists() throws Exception {
		underTest = new ApplicationController(spawnGameService, gamePlay);
		Response checkGameResponse = underTest.checkGame("SomeRandomSessionId");
		assertThat(checkGameResponse.getState(), is("INVALID"));
	}

	private Response spawnGameForTest(String player1, String player2) {
		Player playerOne = new Player(player1);
		Player playerTwo = new Player(player2);
		PlayersStack.getInstance().push(playerOne);
		PlayersStack.getInstance().push(playerTwo);
		return spawnGameService.spawnGame(PlayersStack.getInstance());
	}

	private Response registerPlayer(String nickName) {
		ApplicationController.RegistrationDetails newPlayer = new ApplicationController.RegistrationDetails();
		newPlayer.setNickname(nickName);
		return underTest.registerPlayer(newPlayer);
	}

}