package rps.app.player;

import java.util.Random;

import rps.app.Response;

public class Player implements Response {

	private String nickName;
	private long   playerId;
	private State  state;

	public Player(String nickName) {
		this.state = State.WAIT;
		this.nickName = nickName;
		this.playerId = (new Random(System.currentTimeMillis())).nextLong() * nickName.length();
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

	public static enum State {
		WAIT(0), READY(1), PLAYING(2), WIN(3), LOSE(4);
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
			case 3:
				return "WIN";
			case 4:
				return "LOSE";
			}
			return "IDLE";
		}
	}

	@Override
	public String toString() {
		return this.getNickName();
	}
}
