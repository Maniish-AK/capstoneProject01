package POM.CapstoneProject01;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmBookingPage {
	
	WebDriver driver;
	
	@FindBy (id = "search_hotel")
	public WebElement searchHotelButton;
	
	@FindBy (id = "my_itinerary")
	public WebElement myItineraryButton;
	
	@FindBy (id = "logout")
	public static WebElement logoutButton;
	
	@FindBy (id = "hotel_name")
	public WebElement hotelNameField;
	
	@FindBy (id = "location")
	public WebElement locationField;
	
	@FindBy (id = "room_type")
	public static WebElement roomtypeField;
	
	@FindBy (id = "arrival_date")
	public static WebElement arrivalDateField;
	
	@FindBy (id = "departure_text")
	public static WebElement departureDateField;
	
	@FindBy (id = "total_rooms")
	public WebElement totalRoomsField;
	
	@FindBy (id = "adults_room")
	public WebElement adultsRoomField;
	
	@FindBy (id = "children_room")
	public static WebElement childrenRoomField;
	
	@FindBy (id = "price_night")
	public static WebElement pricePerNightField;
	
	@FindBy (id = "total_price")
	public static WebElement totalPriceField;
	
	@FindBy (id = "gst")
	public WebElement gstField;
	
	@FindBy (id = "final_price")
	public WebElement finalPriceField;
	
	@FindBy (id = "first_name")
	public static WebElement firstNameField;
	
	@FindBy (id = "last_name")
	public static WebElement lastNameField;
	
	@FindBy (id = "address")
	public static WebElement addressField;
	
	@FindBy (id = "order_no")
	public static WebElement orderNoField;
	
	public ConfirmBookingPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@SuppressWarnings("deprecation")
	public void getBookingConfirmation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(hotelNameField));
		String hotelName = hotelNameField.getAttribute("value");
		System.out.println("Hotel Name :: "+hotelName);
		String location = locationField.getAttribute("value");
		System.out.println("Location :: "+location);
		String roomType = roomtypeField.getAttribute("value");
		System.out.println("Room Type :: "+roomType);
		String arrivalDate = arrivalDateField.getAttribute("value");
		System.out.println("Arrival Date :: "+arrivalDate);
		String departureDate = departureDateField.getAttribute("value");
		System.out.println("Departure Date :: "+departureDate);
		String totalRooms = totalRoomsField.getAttribute("value");
		System.out.println("Totel Room :: "+totalRooms);
		String adultsPerRoom = adultsRoomField.getAttribute("value");
		System.out.println("Adults Per Room :: "+adultsPerRoom);
		String childPerRoom = childrenRoomField.getAttribute("value");
		System.out.println("Children Per Room :: "+childPerRoom);
		String pricePerNight = pricePerNightField.getAttribute("value");
		System.out.println("Price Per Night :: "+pricePerNight);
		String totalPrice = totalPriceField.getAttribute("value");
		System.out.println("Total Price :: "+totalPrice);
		String gst = gstField.getAttribute("value");
		System.out.println("GST :: "+gst);
		String finalPrice = finalPriceField.getAttribute("value");
		System.out.println("Final Billed Price :: "+finalPrice);
		String firstName = firstNameField.getAttribute("value");
		System.out.println("First Name :: "+firstName);
		String lastName = lastNameField.getAttribute("value");
		System.out.println("Last Name :: "+lastName);
		String billingAddress = addressField.getAttribute("value");
		System.out.println("Billing Address :: "+billingAddress);
		String orderNo = orderNoField.getAttribute("value");
		System.out.println("Order No :: "+orderNo);
    }
	
	public void clickLogoutButton() {
		logoutButton.click();
    }
	
	public void clickSearchHotelButton() {
		searchHotelButton.click();
    }
	
	public void clickMyItineraryButton() {
		myItineraryButton.click();
    }
	
	public boolean isMyItineraryButtonVisible() {
        return myItineraryButton.isDisplayed();
    }

}
