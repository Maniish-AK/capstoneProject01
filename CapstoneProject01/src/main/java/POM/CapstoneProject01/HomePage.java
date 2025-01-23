package POM.CapstoneProject01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy (id = "username")
	public WebElement userNameField;
	
	@FindBy (id = "password")
	public static WebElement passwordField;
	
	@FindBy (id = "login")
	public static WebElement loginButton;
	
	@FindBy (xpath = "//b[contains(text(),'Invalid Login details or Your Password might have expired. ')]")
	public WebElement loginErrorMessage;
	

	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		userNameField.sendKeys(username);
    }
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
    }
	
	public void clickLoginButton() {
		loginButton.click();
    }


    public boolean isUsernameFieldVisible() {
        return userNameField.isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }
    
    public boolean isErrorMessageVisible() {
        return loginErrorMessage.isDisplayed();
    }
    
    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }
    
    public WebDriver getDriver() {
		return this.driver;
	}
	
	public void openUrl(String url) {
		driver.get(url);
	}

}
