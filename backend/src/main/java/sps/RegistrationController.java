package sps;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sps.registration.player.Player;
import sps.registration.player.pool.PlayerPool;

@RestController
public class RegistrationController {

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public Status ping() {
		return new Status("pong");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Player registerPlayer(@RequestBody Registration registration) {
		Player newPlayer = new Player(registration.getNickname());
		pushToPlayersPool(newPlayer);
		return newPlayer;
	}

	private void pushToPlayersPool(Player newPlayer) {
		PlayerPool.getInstance().getPlayers().put(String.valueOf(newPlayer.getSessionId()), newPlayer);
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

	public class Status {

		private String reply;
		private int    playersOnline;

		public String getReply() {
			return reply;
		}

		public Status(String reply) {
			PlayerPool players = PlayerPool.getInstance();
			this.playersOnline = players.size();
			this.reply = reply;
		}

		public int getPlayersOnline() {
			return playersOnline;
		}

	}

}