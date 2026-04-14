package utilities;

public class LogUtil {

	  public static void info(String message) {
	        System.out.println("[INFO] " + message);
	    }

	    public static void error(String message) {
	        System.out.println("[ERROR] " + message);
	    }

	    public static void step(String message) {
	        System.out.println("[STEP] " + message);
	    }
	}