package tests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.Config;

import java.net.URL;

import static tests.Config.*;

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
            if (host.equals("saucelabs")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser);
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platform);
                String sauceUrl = String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
                        sauceUser, sauceKey);
                driver = new RemoteWebDriver(new URL(sauceUrl), capabilities);
            } else if (host.equals("localhost")) {
                switch (browser.toLowerCase()){
                    case "firefox":
                        //Note that the use of the gecko driver is required if using Firefox versions later than 47.  The
                        //    driver is not currently in a maven package and so must be manually downloaded from
                        //    https://github.com/mozilla/geckodriver/releases
                        System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
                        driver = new FirefoxDriver();
                        break;
                    case "ie":
                    case "internetexplorer":
                        //In order to use IE then the IE driver needs to be installed, and the "webdriver.ie.driver" property must be
                        //set.  There are 2 versions of the IE driver, a 32 bit and 64 bit.  Even though the machine I'm running
                        //on is a 64 bit machine, there is currently a performance bug in the 64 bit version that means that the
                        //sendkeys() method sends each character individually 5 seconds apart and therefore makes it unusable.
                        //The 32 bit version of the driver can be used instead and does not have the same performance problem.
                        System.setProperty("webdriver.ie.driver", "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
                        driver = new InternetExplorerDriver();
                        break;
                    case "headless":
                    case "htmlunit":
                    case "html-unit":
                        //Note that this is the fastest test driver, but it does not implement a standard javascript engine.
                        //Setting javascript to true in the driver instance
                        driver = new HtmlUnitDriver();
                        break;
                    default:
                        System.out.println("unrecognised browser type configured:" + browser.toLowerCase());
                        break;
                }
                System.out.println("***********Launching test with configured browser set to: " + browser);
            }
        }

        @Override
        protected void after() {
            driver.quit();
        }
    };
}
