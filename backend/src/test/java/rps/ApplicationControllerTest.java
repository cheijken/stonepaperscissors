package rps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

import rps.app.GameService;
import rps.app.Response;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

public class ApplicationControllerTest {

	private ApplicationController underTest;

	private GameService gameService = new GameService();

	@Test
	public void shouldCreateAGameWhenBOTHPlayersAreReady() throws Exception {
		Player player1 = gameService.createPlayer("GAURAV");
		player1 = gameService.ready(player1.getPlayerId());

		Player player2 = gameService.createPlayer("VISHAL");
		player2 = gameService.ready(player2.getPlayerId());

		assertNotNull(player1.getGame());
		assertNotNull(player2.getGame());
	}

	@Test
	public void shouldNOTCreateGAMEWhenOnlyONEPlayerINTheSystem() throws Exception {
		Player newPlayer = gameService.createPlayer("GAURAV");
		newPlayer = gameService.ready(newPlayer.getPlayerId());
		assertNull(newPlayer.getGame());
	}

	@Test
	@Ignore(value = "WIP")
	public void thirdPlayerInShouldNotGetAGameEvenIfHeIsReady() throws Exception {
		Player player1 = gameService.createPlayer("GAURAV");
		player1 = gameService.ready(player1.getPlayerId());

		Player player2 = gameService.createPlayer("VISHAL");
		player2 = gameService.ready(player2.getPlayerId());

		Player player3 = gameService.createPlayer("CHRISTIAN");
		player3 = gameService.ready(player3.getPlayerId());

		assertNotNull(player1.getGame());
		assertNotNull(player2.getGame());
		assertNull(player3.getGame());

	}

	@Test
	public void checkGameShouldReturnGameWhenSessionExists() throws Exception {

/*
		underTest = new ApplicationController(gameService, gamePlay);

		Response response = spawnGameForTest("Player1", "Player2");

		assertThat(response.getState(), is(State.WAIT));
		assertTrue(response instanceof Game);
		Game newGame = (Game) response;
		assertThat(underTest.checkGame(newGame.getSessionId()), is(newGame));
*/

	}

	@Test
	public void checkGameShouldReturnInvalidResponseWhenSessionNotExists() throws Exception {
/*
		underTest = new ApplicationController(gameService, gamePlay);
		Response checkGameResponse = underTest.checkGame("SomeRandomSessionId");
		assertThat(checkGameResponse.getState(), is("INVALID"));
*/
	}

	private Response spawnGameForTest(String player1, String player2) {
		Player playerOne = new Player(player1);
		Player playerTwo = new Player(player2);
		PlayersStack.getInstance().push(playerOne);
		PlayersStack.getInstance().push(playerTwo);
		return gameService.spawnGame(PlayersStack.getInstance());
	}

/*
	private Response registerPlayer(String nickName) {
		ApplicationController.RegistrationDetails newPlayer = new ApplicationController.RegistrationDetails();
		newPlayer.setNickname(nickName);
		return underTest.registerPlayer(newPlayer);
	}
*/

}