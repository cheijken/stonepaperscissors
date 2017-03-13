package rps.app;

import org.springframework.stereotype.Service;
import rps.AppUtils;
import rps.app.game.Game;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpawnGameService {

	public Response spawnGame(PlayersStack availablePlayers) {
		List<Player> players = new ArrayList<Player>();
		Response response = null;
		if (isEnoughPlayersToSpawnGame(availablePlayers)) {
			return spawnNewGame(players, availablePlayers);
		} else {
			return defaultResponse(players, availablePlayers);
		}
	}

	public Response defaultResponse(List<Player> pickedPlayers, PlayersStack availablePlayers) {
		pickedPlayers.addAll(availablePlayers.getPlayers());
		return availablePlayers.getPlayers().peek();
	}

	private Game spawnNewGame(List<Player> pickedPlayers, PlayersStack availablePlayers) {
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
