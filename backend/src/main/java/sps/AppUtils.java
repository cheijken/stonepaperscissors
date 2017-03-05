package sps;

public class AppUtils {

	public static String generateRandomID() {
		return String.valueOf(Math.random() * System.currentTimeMillis());
	}

}
