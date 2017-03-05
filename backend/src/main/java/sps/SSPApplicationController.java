package sps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sps.app.Response;
import sps.app.SpawnGameService;
import sps.registration.player.Player;
import sps.registration.player.PlayersStack;

@RestController
public class SSPApplicationController {

	private final SpawnGameService spawnGameService;

	@Autowired
	public SSPApplicationController(SpawnGameService spawnGameService) {
		this.spawnGameService = spawnGameService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public Reply ping() {
		return new Reply("pong");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response registerPlayer(@RequestBody Registration registration) {
		Player newPlayer = new Player(registration.getNickname());
		PlayersStack.push(newPlayer);
		return spawnGameService.spawnGame(PlayersStack.getInstance());
	}

	public static class Registration {

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		private String nickname;

	}

	public class Reply {

		private String reply;
		private int    playersReady;

		public String getReply() {
			return reply;
		}

		public Reply(String reply) {
			this.playersReady = PlayersStack.getInstance().size();
			this.reply = reply;
		}

		public int getPlayersReady() {
			return playersReady;
		}

	}

}