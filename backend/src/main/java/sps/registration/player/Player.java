package sps.registration.player;

public class Player {

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public long getSessionId() {
		return sessionId;
	}

	private long sessionId;

	public Player(String nickName) {
		this.nickName = nickName;
		this.sessionId = nickName.length() * System.currentTimeMillis();
	}

	public enum PlayerState {
		IDLE(0), PLAYING(1);
		private int value;

		private PlayerState(int value) {
			this.value = value;
		}
	}
}
