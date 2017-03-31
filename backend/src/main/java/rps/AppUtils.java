package rps;

import rps.app.game.GameSessionsCache;

public class AppUtils {

	public static String generateRandomID() {
		return String.valueOf(System.currentTimeMillis());
	}
}
