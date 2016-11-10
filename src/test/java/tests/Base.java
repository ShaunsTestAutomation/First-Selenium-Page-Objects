package tests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * Created by shaun on 08/11/2016.
 * This is the base test class.  The purpose is to extract the test setup and teardown
 * so that it can be removed from the test class.
 */
public class Base {
    protected WebDriver driver;

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            //Note that the use of the gecko driver is required if using Firefox versions later than 47.  The
            //    driver is not currently in a maven package and so must be manually downloaded from
            //    https://github.com/mozilla/geckodriver/releases
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
            //In order to use IE then the IE driver needs to be installed, and the "webdriver.ie.driver" property must be
            //set.  There are 2 versions of the IE driver, a 32 bit and 64 bit.  Even though the machine I'm running
            //on is a 64 bit machine, there is currently a performance bug in the 64 bit version that means that the
            //sendkeys() method sends each character individually 5 seconds apart and therefore makes it unusable.
            //The 32 bit version of the driver can be used instead and does not have the same performance problem.
            //
            //To use IE instead of firefox, simply comment out the firefox config above and uncomment out the IE config
            //below.
            //System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
            //driver = new InternetExplorerDriver();
            System.out.println("Debug statement in Base-->Setup method with driver: ");
        };

        @Override
        protected void after() {
            driver.quit();
        };
    };
};
