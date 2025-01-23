package POM.CapstoneProject01;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHotelPage {
	
	WebDriver driver;
	
	@FindBy (id = "location")
	public WebElement locationDropDown;
	
	@FindBy (id = "hotels")
	public WebElement hotelsDropDown;
	
	@FindBy (id = "room_type")
	public WebElement roomtypeDropDown;
	
	@FindBy (id = "room_nos")
	public WebElement roomnoDropDown;
	
	@FindBy (id = "datepick_in")
	public WebElement dateCheckinField;
	
	@FindBy (id = "datepick_out")
	public WebElement dateCheckoutField;
	
	@FindBy (id = "adult_room")
	public WebElement adultsPerRoomDropDown;
	
	@FindBy (id = "child_room")
	public WebElement childPerRoomDropDown;
	
	@FindBy (id = "Submit")
	public static WebElement submitButton;
	
	@FindBy (id = "Reset")
	public WebElement resetButton;
	
	@FindBy (id = "username_show")
	public WebElement welcomeMessage;
	
	@FindBy (xpath = "//a[contains(text(),'Logout')]")
	public WebElement logoutButton;
	
	@FindBy (xpath = "//span[@id='location_span']")
	public WebElement locationErrorMessage;
	
	@FindBy (xpath = "//span[@id='checkin_span']")
	public WebElement checkinDateErrorMessage;
	
	@FindBy (xpath = "//span[@id='checkout_span']")
	public WebElement checkoutDateErrorMessage;
	
	public SearchHotelPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterLocation(String location) {
		locationDropDown.click();
		waitForTime(1000);
		List<WebElement> myLocationElements = driver.findElements(By.xpath("//select[@id='location']//option"));
		for(int index=0;index<myLocationElements.size();index++) {
			if(myLocationElements.get(index).getText().equalsIgnoreCase(location)) {
				myLocationElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterHotels(String hotels) {
		hotelsDropDown.click();
		waitForTime(1000);
		List<WebElement> myHotelsElements = driver.findElements(By.xpath("//select[@id='hotels']//option"));
		for(int index=0;index<myHotelsElements.size();index++) {
			if(myHotelsElements.get(index).getText().equalsIgnoreCase(hotels)) {
				myHotelsElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterRoomType(String roomType) {
		roomtypeDropDown.click();
		waitForTime(1000);
		List<WebElement> myRoomTypeElements = driver.findElements(By.xpath("//select[@id='room_type']//option"));
		for(int index=0;index<myRoomTypeElements.size();index++) {
			if(myRoomTypeElements.get(index).getText().equalsIgnoreCase(roomType)) {
				myRoomTypeElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterNoofRooms(String noofRooms) {
		roomnoDropDown.click();
		waitForTime(1000);
		List<WebElement> myNoofRoomsElements = driver.findElements(By.xpath("//select[@id='room_nos']//option"));
		for(int index=0;index<myNoofRoomsElements.size();index++) {
			if(myNoofRoomsElements.get(index).getText().equalsIgnoreCase(noofRooms)) {
				myNoofRoomsElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterCheckInDate(String checkinDate) {
		dateCheckinField.sendKeys(checkinDate);
    }
	
	public void enterCheckOutDate(String checkoutDate) {
		dateCheckoutField.sendKeys(checkoutDate);
    }
	
	public void enterAdultPerRoom(String adultPerRoom) {
		adultsPerRoomDropDown.click();
		waitForTime(1000);
		List<WebElement> myAdultPerRoomElements = driver.findElements(By.xpath("//select[@id='adult_room']//option"));
		for(int index=0;index<myAdultPerRoomElements.size();index++) {
			if(myAdultPerRoomElements.get(index).getText().equalsIgnoreCase(adultPerRoom)) {
				myAdultPerRoomElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterChildPerRoom(String childPerRoom) {
		childPerRoomDropDown.click();
		waitForTime(1000);
		List<WebElement> myChildPerRoomElements = driver.findElements(By.xpath("//select[@id='child_room']//option"));
		for(int index=0;index<myChildPerRoomElements.size();index++) {
			if(myChildPerRoomElements.get(index).getText().equalsIgnoreCase(childPerRoom)) {
				myChildPerRoomElements.get(index).click();
				break;
			}
		}
    }
	
	public void clickSearchButton() {
		submitButton.click();
    }
	
	public void clickResetButton() {
		resetButton.click();
    }
	
	public void clickLogoutButton() {
		logoutButton.click();
    }
	
	public String getLocationErrorMessage() {
        return locationErrorMessage.getText();
    }
	
	public String getCheckinErrorMessage() {
        return checkinDateErrorMessage.getText();
    }
	
	public String getCheckoutErrorMessage() {
        return checkoutDateErrorMessage.getText();
    }
	
	public boolean isLocationErrorMessageVisible() {
        return locationErrorMessage.isDisplayed();
    }
	
	public boolean isCheckinErrorMessageVisible() {
        return checkinDateErrorMessage.isDisplayed();
    }
	
	public boolean isCheckoutErrorMessageVisible() {
        return checkoutDateErrorMessage.isDisplayed();
    }
	
	public static void waitForTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
