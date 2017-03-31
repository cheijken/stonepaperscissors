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

	@Autowired
	public ApplicationController(SpawnGameService spawnGameService) {
		this.spawnGameService = spawnGameService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public DefaultResponse ping() {
		return new DefaultResponse("pong", "TEST");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response registerPlayer(@RequestBody RegistrationDetails details) {
		return createNewPlayerAndNewGame(details);
	}

	@RequestMapping(value = "/check/{sessionid}", method = RequestMethod.GET, produces = "application/json")
	public Response checkGame(@PathVariable("sessionid") String sessionid) {
		Response gameSession = GameSessionsCache.getInstance().fetch(sessionid);
		if (gameSession == null) {
			return new DefaultResponse("Game Session Not Found", "INVALID");
		}
		return gameSession;
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