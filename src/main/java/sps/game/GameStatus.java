package sps.game;

import sps.registration.player.Player;

import java.util.List;

public class GameStatus {

	private boolean      ready;
	private List<Player> playerList;

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

}
