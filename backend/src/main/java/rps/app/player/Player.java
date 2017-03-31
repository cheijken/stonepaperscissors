package rps.app.player;

import rps.app.Response;

public class Player implements Response {

	private String nickName;
	private long   playerId;
	private State  state;

	public Player(String nickName) {
		this.state = State.WAIT;
		this.nickName = nickName;
		this.playerId = nickName.length() * System.currentTimeMillis();
	}

	public String getNickName() {
		return nickName;
	}

	public long getPlayerId() {
		return playerId;
	}

	@Override
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public enum State {
		WAIT(0), READY(1), PLAYING(2);
		private int value;

		private State(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			switch (this.value) {
			case 0:
				return "WAIT";
			case 1:
				return "READY";
			case 2:
				return "PLAYING";
			}
			return "IDLE";
		}
	}
}
