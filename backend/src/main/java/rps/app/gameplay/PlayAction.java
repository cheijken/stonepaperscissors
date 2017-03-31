package rps.app.gameplay;

public class PlayAction {
	private String game;
	private long player;
	private Move   move;

	public PlayAction(String game, long player, Move move) {
		this.game = game;
		this.player = player;
		this.move = move;
	}

	public long getPlayer() {
		return player;
	}

	public Move getMove() {
		return move;
	}

	public String getGame() {
		return game;
	}

}
