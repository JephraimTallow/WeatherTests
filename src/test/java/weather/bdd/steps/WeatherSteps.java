package weather.bdd.steps;
 
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import pages.HomePage;
import weather.DailyWeatherDetail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
 
public class WeatherSteps {
 
	private HomePage homePage;
	private WebDriver driver;
	private List<DailyWeatherDetail> testDataDetailsList;
	
	@Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/");
        homePage = PageFactory.initElements(driver, HomePage.class);

    }
	
    @Given("^The weather homepage has loaded successfully$")
    public void homepageSuccessfullyLoaded() {
    	
        assertThat("Weather page was not loaded", homePage.isPageLoaded(driver), is(true));
    }
    
    @When("^I Change the city name to \"([^\"]*)\"$")
    public void changeTheCityName(String cityName) {
        homePage.setCityName(cityName);
        
    }
    
    @When("^I select summary row \"([^\"]*)\"$")
    public void theFirstDaysRowIsSelected(String rowNumber) throws InterruptedException {
    	if ("one".equals(rowNumber)) {
    		homePage.clickDayOneRow();
    	} else if ("two".equals(rowNumber)) {
    		homePage.clickDayTwoRow();
    	} else {
    		throw new NotImplementedException("Only rows one and two implemented so far");
    	}
        
        // detail section transitions so WebDriverWait cannot distinguish visibility, so using sleep
        Thread.sleep(1000);
    }
    
    @Then("^The weather intra-day details are shown for that day$")
    public void theIntraDayDetailsAreShown() {
        String details = homePage.getDayOneDetails();
        assertThat("There are details hidden when they should be shown", details.length() > 0, is(true));
    }
    
    @Then("^The weather intra-day details are hidden for that day$")
    public void theIntraDayDetailsAreHidden() {
        String details = homePage.getDayOneDetails();
        assertThat("There are details displayed when they should be hidden", details, isEmptyString());
    }
    
    @When("^The weather service provides the following values:$")
    public void theServiceProvidesFollowingValues(List<DailyWeatherDetail> testDataDetailsList) {

        this.testDataDetailsList = testDataDetailsList;
        for (DailyWeatherDetail dailyWeatherDetail : testDataDetailsList) {
			dailyWeatherDetail.roundValuesDown();
		}
    }
    
    @Then("^The daily details displayed are correct for the provided values$")
    public void theDailyDetailsAreCorrect() {
        
    	List<DailyWeatherDetail> dailyDetailsList = getDetailsListForDay(homePage.getDayOneDetails());	
        //List<DailyWeatherDetail> pageDataList = new ArrayList<DailyWeatherDetail>(dailyDetailsList);
    	for (DailyWeatherDetail testDataRow : this.testDataDetailsList) {
	
	        for (Iterator<DailyWeatherDetail> dailyDetailsIter = dailyDetailsList.iterator(); dailyDetailsIter.hasNext();) {
	        	DailyWeatherDetail pageDataRow = dailyDetailsIter.next();
	        	if (testDataRow.equals(pageDataRow)) {
	        		dailyDetailsIter.remove();
	        		break;
	        	}
			}
    	}
    	
    	assertThat("There are unmatched items in the details list", dailyDetailsList.size() == 0, is(true));
    }
    
    @Then("^The weather daily summary is correct on row \"([^\"]*)\" for date \"([^\"]*)\"$")
    public void theDailySummaryIsCorrect(String rowNumber, String dateRequired) {
        
    	// need to get the summary line...
    	DailyWeatherDetail dominantWeatherValues = getDominantWeatherValues();
    	dominantWeatherValues.setTime(dateRequired);
    	
    	String daySummary = null;
    	if ("one".equals(rowNumber)) {
    		daySummary = homePage.getDayOneSummary();
    	} else if ("two".equals(rowNumber)) {
    		daySummary = homePage.getDayTwoSummary();
    	} else {
    		throw new NotImplementedException("Only rows one and two implemented so far");
    	}

    	List<DailyWeatherDetail> detailsListForDay = getDetailsListForDay(daySummary);
    	
    	assertThat("Summary values are incorrect", dominantWeatherValues, equalTo(detailsListForDay.get(0)));
    }
    
    private DailyWeatherDetail getDominantWeatherValues() {
    	
    	DailyWeatherDetail dominantWeather = new DailyWeatherDetail();
    	
    	for (DailyWeatherDetail dailyWeatherDetail : this.testDataDetailsList) {
    		dailyWeatherDetail.roundValuesDown();
    		if (dominantWeather.getMinTemp() == null || dominantWeather.getMinTempInt() > dailyWeatherDetail.getMinTempInt()) {
    			dominantWeather.setMinTemp(dailyWeatherDetail.getMinTemp());
    		}
    		if (dominantWeather.getMaxTemp() == null || dominantWeather.getMaxTempInt() < dailyWeatherDetail.getMaxTempInt()) {
    			dominantWeather.setMaxTemp(dailyWeatherDetail.getMaxTemp());
    		}
    		if (dominantWeather.getWindSpeed() == null || dominantWeather.getWindSpeedInt() < dailyWeatherDetail.getWindSpeedInt()) {
    			dominantWeather.setWindSpeed(dailyWeatherDetail.getWindSpeed());
    		}
    		if (dominantWeather.getRainfall() == null || dominantWeather.getRainfallInt() < dailyWeatherDetail.getRainfallInt()) {
    			dominantWeather.setRainfall(dailyWeatherDetail.getRainfall());
    		}
    		if (dominantWeather.getPressure() == null || dominantWeather.getPressureInt() < dailyWeatherDetail.getPressureInt()) {
    			dominantWeather.setPressure(dailyWeatherDetail.getPressure());
    		}
		}
    	return dominantWeather;
    }
    
    private List<DailyWeatherDetail> getDetailsListForDay(String detailSection) {
    	
        List<DailyWeatherDetail> detailsList = new ArrayList<DailyWeatherDetail>();
        String detailsRow[] = detailSection.split("mb\\r?\\n");
        
        for (int rowCount = 0; rowCount < detailsRow.length; rowCount++) {
        	String detailColumn[] = detailsRow[rowCount].split("\\r?\\n");
        	
        	DailyWeatherDetail dailyDetails = new DailyWeatherDetail();
			dailyDetails.setTime(detailColumn[0]);
			String temp[] = detailColumn[1].split("°");
			dailyDetails.setMaxTemp(temp[0]);
			dailyDetails.setMinTemp(temp[1]);
			dailyDetails.setWindSpeed(detailColumn[2].replaceAll("kph", ""));
			String rainAndPressure[] = detailColumn[3].split("mm");
			dailyDetails.setRainfall(rainAndPressure[0]);
			dailyDetails.setPressure(rainAndPressure[1].replaceAll("mb", ""));
			
			detailsList.add(dailyDetails);
		}
        return detailsList;
        
    }
    
    @After
    public void afterScenario() {
    	driver.quit();
    }
}
