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
		Player firstPlayer = new Player("ThisIsAPlayerOne");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("ThisIsAPlayerTwoo");
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
	public void shouldReadyPlayerAndGameREADYWhenOnlyBothPlayersReady() throws Exception {
		Player firstPlayer = new Player("ThisIsAPlayerOne");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("ThisIsAPlayerTwoo");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;

		Response result1 = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		assertThat("ReadyPlayer Status after first player Incorrect", result1.getState(), is(Player.State.READY));
		assertThat("Player1 Status Incorrect", firstPlayer.getState(), is(Player.State.READY));
		assertThat("Player2 Status Incorrect when Player1 is ready", secondPlayer.getState(), is(Player.State.WAIT));
		assertThat("Game Status Incorrect", game.getState(), is(State.WAIT));

		Response result2 = underTest.readyPlayer(game.getSessionId(), secondPlayer.getPlayerId());
		assertThat("ReadyPlayer Status after second player Incorrect", result2.getState(), is(State.READY));

		assertThat("Player1 Status Incorrect when both are ready", firstPlayer.getState(), is(Player.State.READY));
		assertThat("Player2 Status Incorrect when both are ready", secondPlayer.getState(), is(Player.State.READY));
		assertThat("Game Status Incorrect", game.getState(), is(State.READY));
	}

}