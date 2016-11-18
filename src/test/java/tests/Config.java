package tests;

/**
 * Created by shaun on 11/11/2016.
 * This code is copied from Dave Haeffners boot camp day 5 example to set up SauceLabs integration.
 */
public class Config {
    public static final String host           = System.getProperty("host", "localhost");
    public static final String browser        = System.getProperty("browser", "firefox");
    public static final String browserVersion = System.getProperty("browserVersion", "49");
    public static final String platform       = System.getProperty("platform", "Windows 10");
    public static final String sauceUser      = System.getenv("SAUCE_USERNAME");
    public static final String sauceKey       = System.getenv("SAUCE_ACCESS_KEY");
}
