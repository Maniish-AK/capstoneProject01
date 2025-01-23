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

public class BookHotelTest {
	
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
        shhp.enterLocation("Los Angeles");
        shhp.enterHotels("Hotel Sunshine");
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
    public void testBookHotelWithoutMandatoryFields() throws InterruptedException {
        BookHotelPage bhp = new BookHotelPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bhp.firstNameField));
        bhp.enterFirstName("Maniish");
        bhp.enterLastName("Guvi");
        bhp.enterAddress("456 Oak Avenue, Suite 302, Toronto, Ontario, M5A 1A1, Canada");
//        bhp.enterCreditCardNo("4123567890112233");
//        bhp.enterCreditcardType("Master Card");
//        bhp.enterCreditcardExpMonth("December");
//        bhp.enterCreditcardExpYear("2030");
//        bhp.enterCvvNo("001");
        bhp.clickBookNowButton();
        wait.until(ExpectedConditions.visibilityOf(bhp.ccErrorMessage));
        String CcErrorMessage = bhp.getCcErrorMessage();
        System.out.println(CcErrorMessage);
        Assert.assertTrue(bhp.isCcErrorMessageVisible(), "Location field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(bhp.cctypeErrorMessage));
        String CctypeErrorMessage = bhp.getCctypeErrorMessage();
        System.out.println(CctypeErrorMessage);
        Assert.assertTrue(bhp.isCctypeErrorMessageVisible(), "Location field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(bhp.ccexpiryErrorMessage));
        String CcexpiryErrorMessage = bhp.getCcexpiryErrorMessage();
        System.out.println(CcexpiryErrorMessage);
        Assert.assertTrue(bhp.isCcexpiryErrorMessageVisible(), "Location field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(bhp.cvvErrorMessage));
        String CvvErrorMessage = bhp.getCvvErrorMessage();
        System.out.println(CvvErrorMessage);
        Assert.assertTrue(bhp.isCvvErrorMessageVisible(), "Location field in Search hotel page is not mandatory field.");
        wait.until(ExpectedConditions.visibilityOf(bhp.backButton));
        bhp.clickBackLink();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/SelectHotel"), "Back link is not working in Book hotel page."); 
    }
	
	@Test (priority=3)
    public void testBookHotelWithAllFields() throws InterruptedException {
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
        wait.until(ExpectedConditions.visibilityOf(bhp.firstNameField));
        bhp.enterFirstName("Maniish");
        bhp.enterLastName("Guvi");
        bhp.enterAddress("456 Oak Avenue, Suite 302, Toronto, Ontario, M5A 1A1, Canada");
        bhp.enterCreditCardNo("4123567890112233");
        bhp.enterCreditcardType("VISA");
        bhp.enterCreditcardExpMonth("December");
        bhp.enterCreditcardExpYear("2030");
        bhp.enterCvvNo("001");
        bhp.clickBookNowButton();
        ConfirmBookingPage cbp = new ConfirmBookingPage(driver);
        wait.until(ExpectedConditions.visibilityOf(cbp.myItineraryButton));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/BookingConfirm"), "Book now button is not working in Book hotel page.");
        cbp.getBookingConfirmation();
        cbp.clickLogoutButton();
    }
	
	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	


}
