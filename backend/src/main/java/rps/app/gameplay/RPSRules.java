package rps.app.gameplay;

public class RPSRules {

	public Move evaluate(Move move1, Move move2) {
		if(move1.getValue() == move2.getValue()) {
			return Move.TIE;
		} else if((isRock(move1) && isPaper(move2)) || (isPaper(move1) && isRock(move2))) {
			return Move.PAPER;
		} else 	if((isRock(move1) && isScissors(move2)) || (isScissors(move1) && isRock(move2))) {
			return Move.ROCK;
		} else 	if((isPaper(move1) && isScissors(move2)) || (isScissors(move1) && isPaper(move2))) {
			return Move.SCISSORS;
		}
		return Move.TIE;
	}

	private boolean isRock(Move move) { return move.getValue() == Move.ROCK.getValue(); }
	private boolean isPaper(Move move) { return move.getValue() == Move.PAPER.getValue(); }
	private boolean isScissors(Move move) { return move.getValue() == Move.SCISSORS.getValue(); }

}
