package sps.app;

import org.springframework.stereotype.Service;
import sps.AppUtils;
import sps.registration.player.Player;
import sps.registration.player.PlayersStack;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpawnGameService {

	public Response spawnGame(PlayersStack availablePlayers) {
		List<Player> players = new ArrayList<Player>();
		Response response = null;
		if (isEnoughPlayersToSpawnGame()) {

			players.add(availablePlayers.pop());
			players.add(availablePlayers.pop());

			response = createGame(players);
		} else {
			players.addAll(availablePlayers.getPlayers());
			response = availablePlayers.getPlayers().peek();
		}
		return response;
	}

	private Game createGame(List<Player> pickedPlayers) {
		Game newGame = new Game(AppUtils.generateRandomID());
		newGame.setPlayers(pickedPlayers);
		newGame.setState(Game.State.READY);
		return newGame;
	}

	private boolean isEnoughPlayersToSpawnGame() {
		return PlayersStack.getInstance().size() > 1;
	}
}
