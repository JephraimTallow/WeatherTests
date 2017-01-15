package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(xpath = "//*[@id='root']/div/div[1]/div[1]")
    private WebElement dayOneSummary;
    
    @FindBy(xpath = "//*[@id='root']/div/div[1]/div[1]/span[1]/span[1]")
    private WebElement dayOneRow;

    @FindBy(xpath = "//*[@id='root']/div/div[1]/div[2]")
    private WebElement dayOneDetail;

    @FindBy(xpath = "//*[@id='root']/div/div[2]/div[1]")
    private WebElement dayTwoSummary;
    
    @FindBy(xpath = "//*[@id='root']/div/div[2]/div[1]/span[1]/span[1]")
    private WebElement dayTwoRow;
    
    @FindBy(xpath = "//*[@id='root']/div/div[1]/div[3]")
    private WebElement dayTwoDetail;
    
    public boolean isPageLoaded(WebDriver driver) {
    	if (!"5 Weather Forecast".equals(driver.getTitle())){
    		return false;
    	}
    	return true;
    }
    
    public void setCityName(String cityName) {
    	cityInput.clear();
        cityInput.sendKeys(cityName);
        cityInput.sendKeys(Keys.ENTER);
    }

    public WebElement getDayOneRowElement() {
    	return dayOneRow;
    }
    public void clickDayOneRow() {
    	dayOneRow.click();
    }

    public String getDayOneDetails() {
    	return dayOneDetail.getText();
    }
    
    public String getDayTwoSummary() {
    	return dayTwoSummary.getText();
    }
    
    public void clickDayTwoRow() {
    	dayTwoRow.click();
    }
    
    public String getDayTwoDetails() {
    	return dayTwoDetail.getText();
    }

	public String getDayOneSummary() {
		return dayOneSummary.getText();
	}
} 