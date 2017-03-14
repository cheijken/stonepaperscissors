package rps.app.gameplay;

public class Play {
	private String game;
	private String player;
	private Move   move;

	public Play(String game, String player, Move move) {
		this.game = game;
		this.player = player;
		this.move = move;
	}

	public String getPlayer() {
		return player;
	}

	public Move getMove() {
		return move;
	}

	public String getGame() {
		return game;
	}

}
