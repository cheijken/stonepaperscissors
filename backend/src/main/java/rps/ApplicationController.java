package rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rps.app.PlayActionsService;
import rps.app.Response;
import rps.app.SpawnGameService;
import rps.app.game.GameSessionsCache;
import rps.app.player.Player;
import rps.app.player.PlayersStack;

@RestController
public class ApplicationController {

	@Autowired
	private final SpawnGameService spawnGameService;

	@Autowired
	private final PlayActionsService playActionsService;

	@Autowired
	public ApplicationController(SpawnGameService spawnGameService, PlayActionsService playActionsService) {
		this.spawnGameService = spawnGameService;
		this.playActionsService = playActionsService;
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

	@RequestMapping(value = "/ready/{gamesessionid}/{playerid}", method = RequestMethod.GET)
	public Response ready(@PathVariable("gamesessionid") String gamesessionid, @PathVariable("playerid") long playerid) {
		return playActionsService.readyPlayer(gamesessionid, playerid);
	}

	@RequestMapping(value = "/move/{gamesessionid}/{player}/{move}", method = RequestMethod.GET)
	public Response play(@PathVariable("gamesessionid") String gamesessionid,@PathVariable("player") long player, @PathVariable("move") String move) {
		return playActionsService.makeAMove(gamesessionid, player, move);
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