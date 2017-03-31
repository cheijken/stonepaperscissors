package rps.app.game;

import java.util.ArrayList;
import java.util.List;

import rps.app.Response;
import rps.app.gameplay.Move;
import rps.app.gameplay.PlayAction;
import rps.app.gameplay.RPSRules;
import rps.app.player.Player;

public class Game implements Response {

	private String       sessionId;
	private List<Player> players;
	private State        state;
	private List<PlayAction> actions = new ArrayList<PlayAction>();

	public Game(String sessionId) {
		this.sessionId = sessionId;
		this.state = State.WAIT;
	}

	public void updatePlayAction(PlayAction playAction) {
		actions.add(playAction);
	}

	public boolean hasOtherPlayerPlayed() {
		return actions.size() == 1;
	}

	public boolean hasAReadyPlayer() {
		for (Player player : this.getPlayers()) {
			if (Player.State.READY.equals(player.getState())) {
				return true;
			}
		}
		return false;
	}

	public long evaluate() {
		Move result = evaluateMoves();
		if (Move.TIE.equals(result)) {
			return 0;
		}
		for (PlayAction action : actions) {
			if (result.equals(action.getMove())) {
				return action.getPlayer();
			}
		}
		return 0;
	}

	private Move evaluateMoves() {
		if (actions.size() == 2) {
			PlayAction player1 = actions.get(0);
			PlayAction player2 = actions.get(1);
			RPSRules rules = new RPSRules();
			return rules.evaluate(player1.getMove(), player2.getMove());
		}
		return null;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
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
	}

}
