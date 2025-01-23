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

public class SearchHotelTest {
	
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
    public void testSearchHotelWithAllFields() throws InterruptedException {
		SearchHotelPage shhp = new SearchHotelPage(driver);
        shhp.enterLocation("Paris");
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
    }
	
	@Test (priority=2)
    public void testSearchHotelWithoutMandatoryFields() throws InterruptedException {
		SearchHotelPage shhp = new SearchHotelPage(driver);
		SelectHotelPage shp = new SelectHotelPage(driver);
		shp.clickCancelButton();
		Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SearchHotel"), "User not redirected to search hotel page.");
//        shhp.enterLocation("Paris");
        shhp.enterHotels("Hotel Creek");
        shhp.enterRoomType("Super Deluxe");
        shhp.enterNoofRooms("1 - One");
//        shhp.enterCheckInDate("14/02/2025");
//        shhp.enterCheckOutDate("15/02/2025");
        shhp.dateCheckinField.clear();
        shhp.dateCheckoutField.clear();
        shhp.enterAdultPerRoom("2 - Two");
        shhp.enterChildPerRoom("2 - Two");
        shhp.clickSearchButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(shhp.locationErrorMessage));
        String locErrorMessage = shhp.getLocationErrorMessage();
        System.out.println(locErrorMessage);
        Assert.assertTrue(shhp.isLocationErrorMessageVisible(), "Location field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(shhp.checkinDateErrorMessage));
        String checkinErrorMessage = shhp.getCheckinErrorMessage();
        System.out.println(checkinErrorMessage);
        Assert.assertTrue(shhp.isCheckinErrorMessageVisible(), "Checkin date field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(shhp.checkoutDateErrorMessage));
        String checkoutErrorMessage = shhp.getCheckoutErrorMessage();
        System.out.println(checkoutErrorMessage);
        Assert.assertTrue(shhp.isCheckoutErrorMessageVisible(), "Checkout date field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(shhp.resetButton));
        shhp.clickResetButton();
        shhp.clickLogoutButton();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
