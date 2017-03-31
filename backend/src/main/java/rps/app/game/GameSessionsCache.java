package rps.app.game;

import java.util.HashMap;
import java.util.Map;

public class GameSessionsCache {

	private static GameSessionsCache instance = null;
	private Map<String, Game> activeSessions;

	private GameSessionsCache() {
		this.activeSessions = new HashMap<String, Game>();
	}

	public static GameSessionsCache getInstance() {
		if (instance == null) {
			instance = new GameSessionsCache();
		}
		return instance;
	}

	public Game fetch(String gamesessionid) {
		return activeSessions.get(gamesessionid);
	}

	public void add(Game newGame) {
		activeSessions.put(newGame.getSessionId(), newGame);
	}

	public void delete(Game game) {
		activeSessions.remove(game.getSessionId());
	}
}
