package rps.app;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rps.DefaultResponse;
import rps.app.game.Game;
import rps.app.game.Game.State;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

public class GamePlayActionServiceTest {

	private GamePlayActionService underTest   = new GamePlayActionService();
	private SpawnGameService      gameSpawner = new SpawnGameService();

	private PlayersStack playersAvailable;

	@Before
	public void setup() {
		playersAvailable = PlayersStack.getInstance();
	}

	@Test
	public void shouldReadyPlayerAndGameWAITWhenOnlyOnePlayerReady() throws Exception {
		Player firstPlayer = new Player("ThisIsAPlayerOneXYZ");
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
		Player firstPlayer = new Player("ThisIsAPlayerOneXYZ");
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

	@Test
	public void gameStatusShouldBeReadyWhenBothPlayersAreReady() {
		Player firstPlayer = new Player("ThisIsAPlayerOneXYZ");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("ThisIsAPlayerTwoB");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;
		Response response1 = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		assertTrue("First Player Ready Response Not Correct", response1 instanceof Player);
		assertThat("First Player Ready Response State Not Correct", response1.getState(), is(Player.State.READY));

		Response response2 = underTest.readyPlayer(game.getSessionId(), secondPlayer.getPlayerId());
		assertTrue("Second Player Ready Response Not Correct", response2 instanceof Game);

		assertThat("First Player State Not Correct", firstPlayer.getState(), is(Player.State.READY));
		assertThat("Second Player  State Not Correct", secondPlayer.getState(), is(Player.State.READY));
		assertThat("Second Player Ready Response State Not Correct", response2.getState(), is(Game.State.READY));


	}

	@Test
	public void shouldReturnWinnerWhenAWinIsEvaluatedConditionIsMet() {
		Player firstPlayer = new Player("ThisIsAPlayerOneXYZ");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("ThisIsAPlayerTwoB");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;
		Response response1 = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		assertTrue("First Player Ready Response Not Correct", response1 instanceof Player);
		assertThat("First Player Ready Response State Not Correct", response1.getState(), is(Player.State.READY));

		Response response2 = underTest.readyPlayer(game.getSessionId(), secondPlayer.getPlayerId());
		assertTrue("Second Player Ready Response Not Correct", response2 instanceof Game);
		assertThat("Second Player Ready Response State Not Correct", response2.getState(), is(Game.State.READY));

		Response move1 = underTest.makeAMove(game, firstPlayer.getPlayerId(), "ROCK");
		Response move2 = underTest.makeAMove(game, secondPlayer.getPlayerId(), "SCISSORS");
		assertTrue("Second Move Didnt Work", move2 instanceof  Player);
		Player winner = (Player) move2;
		assertThat("Incorrect Winner for Rock against Scissors", winner.getNickName(), is("ThisIsAPlayerOneXYZ"));
		assertThat("Incorrect Loser for Rock against Scissors", secondPlayer.getState(), is(Player.State.LOSE));


	}

	@Test
	public void shouldReturnTIEWhenEvaluatedRulesForRPS() {
		Player firstPlayer = new Player("ThisIsAPlayerOneXYZ");
		playersAvailable.push(firstPlayer);

		Player secondPlayer = new Player("ThisIsAPlayerTwoB");
		playersAvailable.push(secondPlayer);

		Response newGame = gameSpawner.spawnGame(playersAvailable);
		assertTrue("Game not Spawned Properly", newGame instanceof Game);

		Game game = (Game) newGame;
		Response response1 = underTest.readyPlayer(game.getSessionId(), firstPlayer.getPlayerId());
		assertTrue("First Player Ready Response Not Correct", response1 instanceof Player);
		assertThat("First Player Ready Response State Not Correct", response1.getState(), is(Player.State.READY));

		Response response2 = underTest.readyPlayer(game.getSessionId(), secondPlayer.getPlayerId());
		assertTrue("Second Player Ready Response Not Correct", response2 instanceof Game);
		assertThat("Second Player Ready Response State Not Correct", response2.getState(), is(Game.State.READY));

		Response move1 = underTest.makeAMove(game, firstPlayer.getPlayerId(), "ROCK");
		Response move2 = underTest.makeAMove(game, secondPlayer.getPlayerId(), "ROCK");
		assertThat("", move2.toString(), is("TIE"));
	}

}