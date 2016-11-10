package tests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageobjects.Login;
import pageobjects.SeleniumTimers;

public class TestLogin extends Base{

    //private WebDriver driver;
    //removed definition of driver from here as it is inhereted from Base class now.

    private Login login;
    private SeleniumTimers wait;

    @Before
    public void setUp() {
        login = new Login(driver);
        //Note! We have now abstracted the driver setup into the Base class.
        //This means that we no longer start the browser here as it is already taken care off
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present",
                login.successMessagePresent());
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        assertTrue("failure message wasn't present after providing bogus credentials",
                login.failureMessagePresent());
        //assertFalse("success message was present after providing bogus credentials",
        //        login.successMessagePresent());
    }
    @Test
    public void harrysTest() {
        login.with("HarryWilliams", "harrysPassword");
        assertTrue("Logged in when we shouldn't have", login.failureMessagePresent());
    }
    //We can remove the teardown from here as it is now in the Base class along
    //with the setup.  The Base class takes care of handling the driver state.
    //@After
    //public void tearDown() {
    //    driver.quit();
    //}
}
