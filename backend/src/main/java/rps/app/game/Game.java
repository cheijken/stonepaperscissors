package rps.app.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import rps.app.Response;
import rps.app.gameplay.Move;
import rps.app.gameplay.PlayAction;
import rps.app.gameplay.RPSRules;
import rps.app.player.Player;

public class Game implements Response {

	private String       sessionId;
	private List<Player> players;
	private State        state;
	private Player winner;
	private List<PlayAction> actions = new ArrayList<PlayAction>();

	private final Consumer<Player> makeWinnerOfGame = winner -> {
		setState(State.OVER);
		this.winner = winner;
	};

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
		if (result == null || Move.TIE.equals(result)) {
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

	public String getOpponentNickName(Player player) {
		return getOpponent(player)
				.map(Player::getNickName)
				.orElse("");
	}

	public Optional<Player> getOpponent(Player player) {
		if (players.size() < 2) {
			return Optional.empty();
		}
		return Optional.of(players.get(0).equals(player) ? players.get(1) : players.get(0));
	}

	public void playerReady(Player player) {
		if (getOpponent(player)
				.map(Player::getState)
				.orElse(Player.State.WAIT)
				.equals(Player.State.READY)) {
			state = State.READY;
		}
	}

	public void playerMove(Player player) {
		if (State.READY.equals(getState())) {
			setState(State.INPROGRESS);
		}

		getOpponent(player).ifPresent(opponent -> opponent.getMove().ifPresent(move -> {
			evaluateMove(player, opponent).ifPresent(makeWinnerOfGame);
			opponent.updateState();
		}));

	}

	private Optional<Player> evaluateMove(Player playerOne, Player playerTwo) {
		Move movePlayerOne = playerOne.getMove().get();
		Move movePlayerTow = playerTwo.getMove().get();
		RPSRules rules = new RPSRules();
		Move result = rules.evaluate(movePlayerOne, movePlayerTow);
		if (result.equals(Move.TIE)) {
			return Optional.empty();
		}
		return Optional.of(result.equals(movePlayerOne) ? playerOne : playerTwo);
	}

	public Optional<Player> getWinner() {
		return Optional.ofNullable(winner);
	}

	public enum State {
		WAIT(3), READY(2), INPROGRESS(1), OVER(0);
		private int value;

		private State(int value) {
			this.value = value;
		}
	}


}
