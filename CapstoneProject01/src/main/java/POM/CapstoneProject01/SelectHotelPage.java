package POM.CapstoneProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotelPage {
	
	WebDriver driver;
	
	@FindBy (id = "radiobutton_0")
	public WebElement selectHotelRadiobutton;
	
	@FindBy (id = "continue")
	public static WebElement continueButton;
	
	@FindBy (id = "cancel")
	public static WebElement cancelButton;
	
	public SelectHotelPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSelectHotelRadioButton() {
		selectHotelRadiobutton.click();
    }
	
	public void clickContinueButton() {
		continueButton.click();
    }
	
	public void clickCancelButton() {
		cancelButton.click();
    }
	
	public boolean isSelectHotelRadioButtonVisible() {
        return selectHotelRadiobutton.isDisplayed();
    }

}
