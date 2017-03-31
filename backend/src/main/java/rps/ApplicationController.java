package rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rps.app.Response;
import rps.app.SpawnGameService;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

@RestController
public class ApplicationController {

	private final SpawnGameService spawnGameService;

	private final GamePlayService gamePlayService;

	@Autowired
	public ApplicationController(SpawnGameService spawnGameService, GamePlayService gamePlayService) {
		this.spawnGameService = spawnGameService;
		this.gamePlayService = gamePlayService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public DefaultResponse ping() {
		return new DefaultResponse("pong", "TEST");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response registerPlayer(@RequestBody RegistrationDetails details) {
		return createNewPlayerAndNewGame(details);
	}

	@RequestMapping(value = "/check/{gamesessionid}", method = RequestMethod.GET, produces = "application/json")
	public Response checkGame(@PathVariable("gamesessionid") String gamesessionid) {
		Response gameSession = GameSessionsCache.getInstance().fetch(gamesessionid);
		if (gameSession == null) {
			return new DefaultResponse("Game Session Not Found", "INVALID");
		}
		return gameSession;
	}

	@RequestMapping(value = "/ready/{gamesessionid}/{playerid}", method = RequestMethod.POST)
	public Response ready(@PathVariable("gamesessionid") String gamesessionid, @PathVariable("playerid") long playerid) {
		return gamePlayService.readyPlayer(gamesessionid, playerid);
	}

	@RequestMapping(value = "/makeamove/{player}/{action}", method = RequestMethod.POST)
	public Response play(@PathVariable("player") String player, @PathVariable("move") String move) {
		return null;
	}

	public static class RegistrationDetails {

		private String nickname;

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	}

	private Response createNewPlayerAndNewGame(@RequestBody RegistrationDetails details) {
		Player newPlayer = new Player(details.getNickname());
		PlayersStack.getInstance().push(newPlayer);
		return spawnGameService.spawnGame(PlayersStack.getInstance());
	}

}