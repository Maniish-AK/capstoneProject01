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

public class BookedItineraryTest {
	
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
    public void searchAndSelectHotel() throws InterruptedException {
		SearchHotelPage shhp = new SearchHotelPage(driver);
        shhp.enterLocation("London");
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
    }
	
	@Test (priority=2)
    public void testBookHotelWithAllFields() throws InterruptedException {
        BookHotelPage bhp = new BookHotelPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bhp.firstNameField));
        bhp.enterFirstName("Maniish");
        bhp.enterLastName("Guvi");
        bhp.enterAddress("456 Oak Avenue, Suite 302, Toronto, Ontario, M5A 1A1, Canada");
        bhp.enterCreditCardNo("4123567890112233");
        bhp.enterCreditcardType("Master Card");
        bhp.enterCreditcardExpMonth("December");
        bhp.enterCreditcardExpYear("2030");
        bhp.enterCvvNo("001");
        bhp.clickBookNowButton();
        ConfirmBookingPage cbp = new ConfirmBookingPage(driver);
        wait.until(ExpectedConditions.visibilityOf(cbp.myItineraryButton));
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl1.contains("/BookingConfirm"), "Book now button is not working in Book hotel page.");
        cbp.getBookingConfirmation();
        cbp.clickMyItineraryButton();
        BookedItineraryPage bip = new BookedItineraryPage(driver);
        wait.until(ExpectedConditions.visibilityOf(bip.cancelSelectedButton));
        String currentUrl2 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl2.contains("/BookedItinerary"), "My Itinerary button is not working in Confirm Booking page.");
    }
	
	@Test (priority=3)
    public void testCancelSelectedHotel() throws InterruptedException {
		BookedItineraryPage bip = new BookedItineraryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.noofOrderids));
        int initialCount = bip.getNumberOfOrderids();
        wait.until(ExpectedConditions.visibilityOf(bip.selectFirstOrderCheckbox));
        bip.clickSelectFirstOrderCheckbox();
        bip.clickCancelSelectedButton();
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.noofOrderids));
        Assert.assertEquals(bip.getNumberOfOrderids(), initialCount - 1, "Order cancelation failed!");
    }
	
	@SuppressWarnings("deprecation")
	@Test (priority=4)
    public void testSearchOrderId() throws InterruptedException {
		BookedItineraryPage bip = new BookedItineraryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.noofOrderids));
        wait.until(ExpectedConditions.visibilityOf(bip.firstOrderId));
        String firstHotelOrderId = bip.firstOrderId.getAttribute("value");
        System.out.println(firstHotelOrderId);
        wait.until(ExpectedConditions.visibilityOf(bip.searchOrderField));
        bip.enterOrderId(firstHotelOrderId);
        Thread.sleep(2000);
        bip.clickSearchOrderButton();
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.searchResultMessage));
        String resultCount = bip.getSearchResultMessage();
        Assert.assertTrue(resultCount.contains("1 result(s) found."), "Search Order failed!");
    }
	

	@Test (priority=5)
    public void testSearchOrderIdAndClickShowAll() {
		BookedItineraryPage bip = new BookedItineraryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.searchResultMessage));
        String resultCount = bip.getSearchResultMessage();
        Assert.assertTrue(resultCount.contains("1 result(s) found."), "Search Order failed!");
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.showAllLink));
        bip.clickShowAllLink();
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.noofOrderids));
        Assert.assertTrue(bip.getNumberOfOrderids() > 1, "Show All failed!");
    }
	
	@Test (priority=6)
    public void testCancelButtonInTable() throws InterruptedException {
		BookedItineraryPage bip = new BookedItineraryPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(bip.noofOrderids));
        wait.until(ExpectedConditions.visibilityOf(bip.firstCancelButton));
        bip.clickFirstCancelButton();
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        String resultCount = bip.getSearchResultMessage();
        Assert.assertTrue(resultCount.contains("The booking has been cancelled."), "Cancel Order using Cancel button in table failed!");
        bip.clickLogoutButton();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/Logout"), "Logout button is not working in Booked Itinerary page.");
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
