package rps.app;

import rps.DefaultResponse;
import rps.app.game.Game;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.Player.State;

public class GamePlayService {

	public Response readyPlayer(String gamesessionid, long playerId) {
		if (playerAndGameValid(gamesessionid, playerId)) {
			Game game = getGame(gamesessionid);
			if (doesGameHaveReadyPlayer(game)) {
				return readyGameAndPlayer(game, playerId);
			} else {
				return readyPlayer(game, playerId);
			}
		}
		return new DefaultResponse("Game or Player Not Found", "INVALID");
	}

	private boolean doesGameHaveReadyPlayer(Game game) {
		for (Player player : game.getPlayers()) {
			if (State.READY.equals(player.getState())) {
				return true;
			}
		}
		return false;
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
