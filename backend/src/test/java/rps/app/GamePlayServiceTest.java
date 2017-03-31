package rps.app;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import rps.DefaultResponse;
import rps.app.game.Game;
import rps.app.game.Game.State;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

public class GamePlayServiceTest {

	private GamePlayService  underTest   = new GamePlayService();
	private SpawnGameService gameSpawner = new SpawnGameService();

	private PlayersStack playersAvailable;

	@Before
	public void setup() {
		playersAvailable = PlayersStack.getInstance();
	}


	@Test
	public void shouldReadyPlayerAndGameWAITWhenOnlyOnePlayerReady() throws Exception {
		Player firstPlayer = new Player("Player1");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("Player2");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;

		Response result = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		assertFalse(result instanceof DefaultResponse);
		assertTrue(result instanceof Player);

		assertThat("Player Status Incorrect", result.getState(), is(Player.State.READY));
		assertThat("Game Status Incorrect", game.getState(), is(Game.State.WAIT));

	}

	@Test
	@Ignore(value="WIP Failing Tesst")
	public void shouldReadyPlayerAndGameREADYWhenOnlyBothPlayersReady() throws Exception {
		Player firstPlayer = new Player("Player1");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("Player2");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;

		Response result1 = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		Response result2 = underTest.readyPlayer(game.getSessionId(), secondPlayer.getPlayerId());

		assertThat("Player1 Status Incorrect", result1.getState(), is(Player.State.READY));
		assertThat("Player2 Status Incorrect", result2.getState(), is(Player.State.READY));
		assertThat("Game Status Incorrect", game.getState(), is(Game.State.READY));
	}

}