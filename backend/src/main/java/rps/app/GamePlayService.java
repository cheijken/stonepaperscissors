package rps.app;

import rps.DefaultResponse;
import rps.app.game.Game;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.Player.State;
import rps.app.player.PlayersStack;

public class GamePlayService {

	public Response readyPlayer(String gamesessionid, long playerId) {
		if(playerAndGameValid(gamesessionid, playerId)) {
			// Are Both Players Ready, then Game is Also Ready
			// change Game & Player State to ready
			// is One Player Ready
			return changePlayerStateToReady(gamesessionid, playerId);
		}
		return new DefaultResponse("Game or Player Not Found", "INVALID");
	}

	private Response changePlayerStateToReady(String gamesessionid, long player) {
		Game game = getGame(gamesessionid);
		Player playerX = getPlayer(game, player);
		playerX.setState(State.READY);
		return playerX;
	}

	private boolean doesGameContainReadyPlayer(Game game) {
		return false;
	}

	private Response changeGameStateToReady(String gamesessionid, long player) {
		Game game = getGame(gamesessionid);
		Player playerX = getPlayer(game, player);
		playerX.setState(State.READY);
		game.setState(Game.State.READY);
		return playerX;
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
