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

public class SelectHotelTest {
	
	WebDriver driver;
	
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		HomePage hp = new HomePage(driver);
		hp.openUrl("https://adactinhotelapp.com/index.php");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(hp.userNameField));
        hp.enterUsername("ManiishAKGuvi");
        hp.enterPassword("Maniish@0212");
        hp.clickLoginButton();
        SearchHotelPage shhp = new SearchHotelPage(driver);
        wait.until(ExpectedConditions.visibilityOf(shhp.welcomeMessage));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SearchHotel"), "User not logged in successfully.");
    }
	
	@Test (priority=1)
    public void testSelectHotelAndCancel() throws InterruptedException {
		SearchHotelPage shhp = new SearchHotelPage(driver);
        shhp.enterLocation("Sydney");
        shhp.enterHotels("Hotel Creek");
        shhp.enterRoomType("Super Deluxe");
        shhp.enterNoofRooms("1 - One");
        shhp.enterCheckInDate("14/02/2025");
        shhp.enterCheckOutDate("15/02/2025");
        shhp.enterAdultPerRoom("2 - Two");
        shhp.enterChildPerRoom("2 - Two");
        shhp.clickSearchButton();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SelectHotel"), "User not redirected to select hotel page.");
        SelectHotelPage shp = new SelectHotelPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(shp.selectHotelRadiobutton));
        Assert.assertTrue(shp.isSelectHotelRadioButtonVisible(), "Search hotel did not provide any result to select.");
        shp.clickSelectHotelRadioButton();
        shp.clickCancelButton();
        Thread.sleep(3000);
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl1.contains("/SearchHotel"), "Cancel button is not working in Select hotel page.");
    }
	
	@Test (priority=1)
    public void testSelectHotelAndContinue() throws InterruptedException {
		SearchHotelPage shhp = new SearchHotelPage(driver);
		shhp.clickResetButton();
        shhp.enterLocation("Sydney");
        shhp.enterHotels("Hotel Cornice");
        shhp.enterRoomType("Super Deluxe");
        shhp.enterNoofRooms("1 - One");
        shhp.enterCheckInDate("14/02/2025");
        shhp.enterCheckOutDate("15/02/2025");
        shhp.enterAdultPerRoom("2 - Two");
        shhp.enterChildPerRoom("2 - Two");
        shhp.clickSearchButton();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SelectHotel"), "User not redirected to select hotel page.");
        SelectHotelPage shp = new SelectHotelPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(shp.selectHotelRadiobutton));
        Assert.assertTrue(shp.isSelectHotelRadioButtonVisible(), "Search hotel did not provide any result to select.");
        shp.clickSelectHotelRadioButton();
        shp.clickContinueButton();
        Thread.sleep(3000);
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl1.contains("/BookHotel"), "Continue button is not working in Select hotel page.");
        BookHotelPage bhp = new BookHotelPage(driver);
        bhp.clickLogoutButton();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
