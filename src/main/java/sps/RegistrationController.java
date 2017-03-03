package sps;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sps.registration.player.Player;

@RestController
public class RegistrationController {

	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "application/json")
	public Ping ping() {
		return new Ping("pong");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Player registerPlayer(@RequestBody Register registerPlayer) {
		return new Player(registerPlayer.getNickname());
	}

	public static class Register {

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		private String nickname;

	}

	public class Ping {

		private String reply;

		public Ping(String reply) {
			this.reply = reply;
		}

		public String getReply() {
			return reply;
		}

	}

}