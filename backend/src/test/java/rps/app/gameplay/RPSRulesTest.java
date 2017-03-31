package rps.app.gameplay;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RPSRulesTest {

	private RPSRules underTest = new RPSRules();

	@Test
	public void shouldReturnTieWhenBothMovesAreSame() throws Exception {
		assertThat(underTest.evaluate(Move.PAPER, Move.PAPER), is(.MoveTIE));
	}

	@Test
	public void shouldReturnScissorWhenScissorsAndPaper() throws Exception {
		assertThat(underTest.evaluate(Move.PAPER, Move.SCISSORS), is(Move.SCISSORS));
	}

	@Test
	public void shouldReturnRockWhenScissorsAndRock() throws Exception {
		assertThat(underTest.evaluate(Move.ROCK, Move.SCISSORS), is(Move.ROCK));
	}

	@Test
	public void shouldReturnScissorWhenPaperAndRock() throws Exception {
		assertThat(underTest.evaluate(Move.ROCK, Move.SCISSORS), is(Move.ROCK));
	}

}