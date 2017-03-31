package rps.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rps.AppUtils;
import rps.app.game.Game;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

@Service
public class SpawnGameService {

	public Response spawnGame(PlayersStack availablePlayers) {
		if (isEnoughPlayersToSpawnGame(availablePlayers)) {
			return spawnNewGame(availablePlayers);
		} else {
			return defaultResponse(availablePlayers);
		}
	}

	private Response defaultResponse(PlayersStack availablePlayers) {
		List<Player> pickedPlayers = new ArrayList<Player>();
		pickedPlayers.addAll(availablePlayers.getPlayers());
		return availablePlayers.getPlayers().peek();
	}

	private Game spawnNewGame(PlayersStack availablePlayers) {
		List<Player> pickedPlayers = new ArrayList<Player>();
		pickedPlayers.add(availablePlayers.pop());
		pickedPlayers.add(availablePlayers.pop());

		Game newGame = new Game(AppUtils.generateRandomID());
		newGame.setPlayers(pickedPlayers);
		newGame.setState(Game.State.READY);
		GameSessionsCache.getInstance().add(newGame);
		return newGame;
	}

	public boolean isEnoughPlayersToSpawnGame(PlayersStack playersStack) { return playersStack.size() > 1;}
}
