package rps.app.player;

import java.util.Stack;

public class PlayersStack {

	private static PlayersStack instance = null;
	private Stack<Player> players;

	private PlayersStack() {
		this.players = new Stack<Player>();
	}

	public static PlayersStack getInstance() {
		if (instance == null) {
			instance = new PlayersStack();
		}
		return instance;
	}

	public void push(Player player) {
		getInstance().players.push(player);
	}

	public Player pop() {
		return getInstance().players.pop();
	}

	public Stack<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Stack<Player> players) {
		this.players = players;
	}

	public int size() {
		return instance == null ? 0 : instance.getPlayers().size();
	}

}
