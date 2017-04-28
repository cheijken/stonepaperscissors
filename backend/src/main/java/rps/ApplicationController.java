package rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rps.app.GameService;
import rps.app.game.Game;
import rps.app.gameplay.Move;
import rps.app.player.Player;

@RestController
public class ApplicationController {

	@Autowired
	private final GameService gameService;

	@Autowired
	public ApplicationController(GameService gameService) {
		this.gameService = gameService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public DefaultResponse ping() {
		return new DefaultResponse("pong", "TEST");
	}

	@RequestMapping(value = "/register/{nickname}", method = RequestMethod.PUT, produces = "application/json"	)
	public PlayerResponse registerPlayer(@PathVariable("nickname") String nickname) {
		return new PlayerResponse(gameService.createPlayer(nickname));
	}

	@RequestMapping(value = "/check/{playerid}", method = RequestMethod.GET, produces = "application/json")
	public PlayerResponse checkGame(@PathVariable("playerid") Long playerId) {
		Player player = gameService.findPlayerById(playerId);
		return new PlayerResponse(player);
	}

	@RequestMapping(value = "/ready/{playerid}", method = RequestMethod.GET, produces = "application/json")
	public PlayerResponse ready(@PathVariable("playerid") long playerid) {
		Player player = gameService.ready(playerid);
		return new PlayerResponse(player);
	}

	@RequestMapping(value = "/move/{playerid}/{move}", method = RequestMethod.GET, produces = "application/json")
	public PlayerResponse play(@PathVariable("playerid") long playerId, @PathVariable("move") Move move) {
		Player player = gameService.findPlayerById(playerId);
		player.move(move);
		return new PlayerResponse(player);
	}

	public class PlayerResponse {

		Long playerId;
		String nickName;
		Player.State state;
		String opponent;
		Game.State gameState;

		PlayerResponse(Player player) {
			this.playerId = player.getPlayerId();
			this.nickName = player.getNickName();
			this.state = player.getState();
			this.opponent = player.getOpponentNickName();
			this.gameState = player.getGameState();
		}

		public Long getPlayerId() {
			return playerId;
		}

		public String getNickName() {
			return nickName;
		}

		public Player.State getState() {
			return state;
		}

		public String getOpponent() {
			return opponent;
		}

		public Game.State getGameState() {
			return gameState;
		}
	}
}