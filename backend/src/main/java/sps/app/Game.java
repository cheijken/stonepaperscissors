package sps.app;

import sps.registration.player.Player;

import java.util.List;

public class Game implements Response {

	private String       sessionId;
	private List<Player> players;
	private State        state;

	public Game(String sessionId) {
		this.sessionId = sessionId;
		this.state = State.WAIT;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public enum State {
		WAIT(3), READY(2), INPROGRESS(1), OVER(0);
		private int value;

		private State(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			switch (this.value) {
			case 3:
				return "WAIT";
			case 2:
				return "READY";
			case 1:
				return "INPROGRESS";
			case 0:
				return "OVER";
			}
			return "WAIT";
		}
	}

}
