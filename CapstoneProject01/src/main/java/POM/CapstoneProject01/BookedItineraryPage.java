package POM.CapstoneProject01;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookedItineraryPage {
	
	WebDriver driver;
	
	@FindBy (id = "order_id_text")
	public WebElement searchOrderField;
	
	@FindBy (id = "search_hotel_id")
	public static WebElement searchOrderButton;
	
	@FindBy (xpath = "//input[@value='Cancel Selected']")
	public WebElement cancelSelectedButton;
	
	@FindBy (id = "search_hotel")
	public WebElement searchHotelButton;
	
	@FindBy (id = "logout")
	public WebElement logoutButton;
	
	@FindBy (xpath = "(//input[@name='ids[]'])[1]")
	public WebElement selectFirstOrderCheckbox;
	
	@FindBy (xpath = "(//input[@type='button' and contains(@value, 'Cancel')])[1]")
	public WebElement firstCancelButton;
	
	@FindBy (xpath = "(//input[@type='text' and contains(@name, 'order_id')])[2]")
	public WebElement firstOrderId;
	
	@FindBy (xpath = "//input[@type='text' and contains(@name, 'order_id')]")
    public List<WebElement> noofOrderids;
	
	@FindBy (xpath = "//label[@id='search_result_error']")
	public WebElement searchResultMessage;
	
	@FindBy (xpath = "//a[contains(text(),'Show all')]")
	public WebElement showAllLink;
	
	
	
	public BookedItineraryPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterOrderId(String orderid) {
		searchOrderField.sendKeys(orderid);
    }
	
	public void clickSearchOrderButton() {
		searchOrderButton.click();
    }
	
	public void clickCancelSelectedButton() {
		cancelSelectedButton.click();
    }
	
	public void clickSearchHotelButton() {
		searchHotelButton.click();
    }
	
	public void clickLogoutButton() {
		logoutButton.click();
    }
	
	public void clickSelectFirstOrderCheckbox() {
		selectFirstOrderCheckbox.click();
    }
	
	public boolean isSearchOrderFieldVisible() {
        return searchOrderField.isDisplayed();
    }
	
	public boolean isSelectFirstOrderCheckboxVisible() {
        return selectFirstOrderCheckbox.isDisplayed();
    }
	
	public boolean isSearchResultMessageVisible() {
        return searchResultMessage.isDisplayed();
    }
	
	public int getNumberOfOrderids() {
        return noofOrderids.size();
    }
	
	public String getSearchResultMessage() {
        return searchResultMessage.getText();
    }
	
	public void clickShowAllLink() {
		showAllLink.click();
    }
	
	public void clickFirstCancelButton() {
		firstCancelButton.click();
    }
	
	public List<String> getAllOrderIds() {
        List<String> orderIds = new ArrayList<>();
        for (WebElement row : noofOrderids) {
            WebElement nameElement = row.findElement(By.xpath(".//td[2]"));
          //table[@id='myTable']//tr//td[2]
//            contactNames.add(nameElement.getText());
            orderIds.add(nameElement.getText().trim());
        }
        return orderIds;
    }

}
