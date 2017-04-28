package rps.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rps.AppUtils;
import rps.app.game.Game;
import rps.app.game.Game.State;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

@Service public class GameService {

	private Map<Long, Player> players = new HashMap<>();
	private List<Game> games = new ArrayList<>();

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
		newGame.setState(State.WAIT);
		pickedPlayers.get(0).setGame(newGame);
		pickedPlayers.get(1).setGame(newGame);
		GameSessionsCache.getInstance().add(newGame);
		return newGame;
	}

	private Game startNewGame(Player playerOne, Player playerTwo) {
		List<Player> pickedPlayers = new ArrayList<>();
		pickedPlayers.add(playerOne);
		pickedPlayers.add(playerTwo);

		Game newGame = new Game(AppUtils.generateRandomID());
		games.add(newGame);
		newGame.setPlayers(pickedPlayers);
		newGame.setState(State.READY);
		playerOne.setGame(newGame);
		playerTwo.setGame(newGame);
		GameSessionsCache.getInstance().add(newGame);
		return newGame;
	}


	public boolean isEnoughPlayersToSpawnGame(PlayersStack playersStack) {
		return playersStack.size() > 1;
	}

	public Player findPlayerById(Long playerId) {
		return players.get(playerId);
	}

	public Player createPlayer(String nickname) {
		Player newPlayer = new Player(nickname);
		players.put(newPlayer.getPlayerId(), newPlayer);
		return newPlayer;
	}

	public Player ready(long playerid) {
		Player player = findPlayerById(playerid);
		player.ready();
		if (!player.hasGame()) {
			Optional<Player> oppnent = players.values().stream()
					.filter(player1 -> !player1.equals(player))
					.filter(o -> o.getState().equals(Player.State.READY))
					.findFirst();
			oppnent.ifPresent(o ->startNewGame(player, o));
		}
		return player;
	}

}
