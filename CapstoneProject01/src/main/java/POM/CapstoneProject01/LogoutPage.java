package POM.CapstoneProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
	
	WebDriver driver;
	
	@FindBy (xpath = "//a[contains(text(),'Click here to login again')]")
	public WebElement loginAgainLink;
	

	public LogoutPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickLoginAgainLink() {
		loginAgainLink.click();
    }


    public boolean isLoginAgainLinkVisible() {
        return loginAgainLink.isDisplayed();
    }

}
