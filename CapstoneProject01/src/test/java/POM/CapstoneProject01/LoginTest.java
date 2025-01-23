package POM.CapstoneProject01;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	
	WebDriver driver;
	
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
		hp.openUrl("https://adactinhotelapp.com/index.php");
    }
	
	@Test (priority=1)
    public void testLoginFunctionalityWithValidCredentials() {
		HomePage hp = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(hp.userNameField));
        hp.enterUsername("ManiishAKGuvi");
        hp.enterPassword("Maniish@0212");
        hp.clickLoginButton();
        SearchHotelPage shhp = new SearchHotelPage(driver);
        wait.until(ExpectedConditions.visibilityOf(shhp.welcomeMessage));
        @SuppressWarnings("deprecation")
		String welcomeMessage = shhp.welcomeMessage.getAttribute("value");
        System.out.println(welcomeMessage);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SearchHotel"), "User not logged in successfully.");
        wait.until(ExpectedConditions.visibilityOf(shhp.logoutButton));
        shhp.clickLogoutButton();
    }
	
	@Test (priority=2)
    public void testLoginFunctionalityWithInvalidCredentials() {
		LogoutPage lop = new LogoutPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lop.loginAgainLink));
        Assert.assertTrue(lop.isLoginAgainLinkVisible(), "User not logged out successfully, so login again link not visible!");
        lop.clickLoginAgainLink();
        HomePage hp = new HomePage(driver);
        wait.until(ExpectedConditions.visibilityOf(hp.userNameField));
        hp.enterUsername("aabbcc");
        hp.enterPassword("aabbcc");
        hp.clickLoginButton();
        wait.until(ExpectedConditions.visibilityOf(hp.loginErrorMessage));
        String errorMessage = hp.getLoginErrorMessage();
        if(errorMessage.contains("Invalid Login details or Your Password might have expired. ")) {
        	System.out.println("Invalid Credentials Used");
        }
        Assert.assertTrue(hp.isErrorMessageVisible(), "User should use valid credentials to Login.");
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
