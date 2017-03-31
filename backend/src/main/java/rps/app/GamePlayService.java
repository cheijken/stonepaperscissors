package rps.app;

import rps.DefaultResponse;
import rps.app.game.Game;
import rps.app.game.GameSessionsCache;
import rps.app.gameplay.Move;
import rps.app.gameplay.PlayAction;
import rps.app.player.Player;
import rps.app.player.Player.State;

public class GamePlayService {

	private static final String PLAY_ROCK = "ROCK";
	private static final String PLAY_PAPER = "PAPER";
	private static final String PLAY_SCISSORS = "SCISSORS";

	public Response readyPlayer(String gamesessionid, long playerId) {
		if (playerAndGameValid(gamesessionid, playerId)) {
			Game game = getGame(gamesessionid);
			if (game.hasAReadyPlayer()) {
				return readyGameAndPlayer(game, playerId);
			} else {
				return readyPlayer(game, playerId);
			}
		}
		return new DefaultResponse("Game or Player Not Found", "INVALID");
	}

	public Response makeAMove(String gamesessionid, long playerId, String move) {
		return makeAMove(getGame(gamesessionid), playerId, move);
	}

	public Response makeAMove(Game game, long playerId, String move) {
		if (isPlayable(game)) {
			if (Game.State.READY.equals(game.getState())) {
				changeGameStatus(game, Game.State.INPROGRESS);
			}
			Player player = getPlayer(game, playerId);
			PlayAction playAction = new PlayAction(game.getSessionId(), playerId, getMove(move));
			game.updatePlayAction(playAction);
			Player winner = evaluateGame(game);
			if (winner == null) {
				new DefaultResponse("Its a Tie", "TIE");
			}
			changeGameStatus(game, Game.State.OVER);
			System.out.println("Winning Player is = " + winner);
			return winner;
		}
		return new DefaultResponse("The Other Player is Not Yet Ready", "INVALID");
	}

	private boolean isPlayable(Game game) {
		return game.getState().equals(Game.State.READY) || game.getState().equals(Game.State.INPROGRESS);
	}

	private Move getMove(String move) {
		if (PLAY_ROCK.equals(move)) {
			return Move.ROCK;
		} else if (PLAY_PAPER.equals(move)) {
			return Move.PAPER;
		} else if (PLAY_SCISSORS.equals(move)){
			return Move.SCISSORS;
		}
		return null;
	}

	private void changeGameStatus(Game game, Game.State state) {
		game.setState(state);
	}

	private Player evaluateGame(Game game) {
		if (game.hasOtherPlayerPlayed()) {
			getPlayer(game, game.evaluate());
		}
		return null;
	}

	private Response readyPlayer(Game game, long player) {
		Player playerX = getPlayer(game, player);
		playerX.setState(State.READY);
		return playerX;
	}

	private Response readyGameAndPlayer(Game game, long player) {
		Player playerX = getPlayer(game, player);
		playerX.setState(State.READY);
		game.setState(Game.State.READY);
		return game;
	}

	private Game getGame(String gamesessionid) {
		return GameSessionsCache.getInstance().fetch(gamesessionid);
	}

	private Player getPlayer(Game game, long playerId) {
		for (Player player : game.getPlayers()) {
			if (player.getPlayerId() == playerId) {
				return player;
			}
		}
		return null;
	}

	private boolean playerAndGameValid(String gamesessionid, long playerId) {
		if (GameSessionsCache.gameExists(gamesessionid)) {
			Game currentGame = GameSessionsCache.getInstance().fetch(gamesessionid);
			for (Player player : currentGame.getPlayers()) {
				if (player.getPlayerId() == playerId) {
					return true;
				}
			}
		}
		return false;
	}

}
