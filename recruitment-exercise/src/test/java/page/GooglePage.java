package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class GooglePage {

    private WebDriver driver;

    public GooglePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//a[text()='About']")
    private WebElement linkAbout;

    @FindBy(xpath = ".//h1[contains(@class, 'mission-statement')]")
    private WebElement lblMissionStatement;

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(xpath = ".//div[contains(@class, 'g')]//h3")
    private List<WebElement> searchResults;

    @FindBy(xpath = ".//div[@id='result-stats']")
    private WebElement searchResultsNumber;

    public boolean verifyAndClickAboutLink() {
        boolean isPresent = linkAbout.isDisplayed();
        linkAbout.click();
        return isPresent;
    }

    public String getMissionStatement() {
        return lblMissionStatement.getAttribute("innerText").trim();
    }

    public void search(String str) {
        searchBox.sendKeys(str);
        searchBox.sendKeys(Keys.ENTER);

    }
    public List<WebElement> verifySearchResult() {
        return searchResults;
    }

    public boolean verifyResultStatsDisplayed() {
        return searchResultsNumber.isEnabled();
    }

    public String validateResultStats() {
        return searchResultsNumber.getAttribute("innerText").trim();
    }

}
