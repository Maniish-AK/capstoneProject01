package POM.CapstoneProject01;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookHotelPage {
	
	WebDriver driver;
	
	@FindBy (id = "first_name")
	public WebElement firstNameField;
	
	@FindBy (id = "last_name")
	public static WebElement lastNameField;
	
	@FindBy (id = "address")
	public static WebElement addressField;
	
	@FindBy (id = "cc_num")
	public WebElement creditcardField;
	
	@FindBy (id = "cc_type")
	public static WebElement cardTypeDropDown;
	
	@FindBy (id = "cc_exp_month")
	public static WebElement monthExpiryDropDown;
	
	@FindBy (id = "cc_exp_year")
	public static WebElement yearExpiryDropDown;
	
	@FindBy (id = "cc_cvv")
	public WebElement cvvField;
	
	@FindBy (id = "book_now")
	public static WebElement booknowButton;
	
	@FindBy (id = "cancel")
	public static WebElement cancelButton;
	
	@FindBy (xpath = "//a[contains(text(),'Back')]")
	public WebElement backButton;
	
	@FindBy (xpath = "//a[contains(text(),'Please wait! We are processing your Hotel Booking...')]")
	public static WebElement processingMessage;
	
	@FindBy (xpath = "//a[contains(text(),'Logout')]")
	public WebElement logoutButton;
	
	@FindBy (xpath = "//label[@id='cc_num_span']")
	public WebElement ccErrorMessage;
	
	@FindBy (xpath = "//label[@id='cc_type_span']")
	public WebElement cctypeErrorMessage;
	
	@FindBy (xpath = "//label[@id='cc_expiry_span']")
	public WebElement ccexpiryErrorMessage;
	
	@FindBy (xpath = "//label[@id='cc_cvv_span']")
	public WebElement cvvErrorMessage;
	
	public BookHotelPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
    }
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
    }
	
	public void enterAddress(String address) {
		addressField.sendKeys(address);
    }
	
	public void enterCreditCardNo(String ccno) {
		creditcardField.sendKeys(ccno);
    }
	
	public void enterCreditcardType(String cctype) {
		cardTypeDropDown.click();
		waitForTime(1000);
		List<WebElement> myCcTypeElements = driver.findElements(By.xpath("//select[@id='cc_type']//option"));
		for(int index=0;index<myCcTypeElements.size();index++) {
			if(myCcTypeElements.get(index).getText().equalsIgnoreCase(cctype)) {
				myCcTypeElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterCreditcardExpMonth(String ccexpmonth) {
		monthExpiryDropDown.click();
		waitForTime(1000);
		List<WebElement> myMonthExpElements = driver.findElements(By.xpath("//select[@id='cc_exp_month']//option"));
		for(int index=0;index<myMonthExpElements.size();index++) {
			if(myMonthExpElements.get(index).getText().equalsIgnoreCase(ccexpmonth)) {
				myMonthExpElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterCreditcardExpYear(String ccexpyear) {
		yearExpiryDropDown.click();
		waitForTime(1000);
		List<WebElement> myYearExpElements = driver.findElements(By.xpath("//select[@id='cc_exp_year']//option"));
		for(int index=0;index<myYearExpElements.size();index++) {
			if(myYearExpElements.get(index).getText().equalsIgnoreCase(ccexpyear)) {
				myYearExpElements.get(index).click();
				break;
			}
		}
    }
	
	public void enterCvvNo(String cvvno) {
		cvvField.sendKeys(cvvno);
    }
	
	public void clickBookNowButton() {
		booknowButton.click();
    }
	
	public void clickCancelButton() {
		cancelButton.click();
    }
	
	public void clickBackLink() {
		backButton.click();
    }
	
	public void clickLogoutButton() {
		logoutButton.click();
    }
	
	public String getCcErrorMessage() {
        return ccErrorMessage.getText();
    }
	
	public String getCctypeErrorMessage() {
        return cctypeErrorMessage.getText();
    }
	
	public String getCcexpiryErrorMessage() {
        return ccexpiryErrorMessage.getText();
    }
	
	public String getCvvErrorMessage() {
        return cvvErrorMessage.getText();
    }
	
	public boolean isCcErrorMessageVisible() {
        return ccErrorMessage.isDisplayed();
    }
	
	public boolean isCctypeErrorMessageVisible() {
        return cctypeErrorMessage.isDisplayed();
    }
	
	public boolean isCcexpiryErrorMessageVisible() {
        return ccexpiryErrorMessage.isDisplayed();
    }
	
	public boolean isCvvErrorMessageVisible() {
        return cvvErrorMessage.isDisplayed();
    }
	
	public static void waitForTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
