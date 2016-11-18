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

//Now we have added and inhereted the Base class, we can do all global test related functions such as
//setup and tear down in that class.  This makes the test class much more readable as it now only
// contains test cases
public class TestLogin extends Base{

    private Login login;
    private SeleniumTimers wait;

    @Before
    public void setUp() {
        login = new Login(driver);
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
    }
    @Test
    public void harrysTest() {
        login.with("HarryWilliams", "harrysPassword");
        assertTrue("Logged in when we shouldn't have", login.failureMessagePresent());
    }
}
