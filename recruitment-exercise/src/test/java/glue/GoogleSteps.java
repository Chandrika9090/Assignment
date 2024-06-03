package glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import page.GooglePage;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSteps {

    private static final Logger logger = LogManager.getLogger(GoogleSteps.class);

    GooglePage googlePage = new GooglePage(W.get().driver);
    @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    @When("About page is shown")
    public void aboutPageIsShown() {
        Assert.assertTrue("About link is not present",googlePage.verifyAndClickAboutLink());
        logger.info("About is displayed");
    }

    @Then("page displays {string}")
    public void pageDisplays(String expectedText) {
       String actualText = googlePage.getMissionStatement();
       Assert.assertEquals("Mission statement isn't matching",expectedText,actualText);
       logger.info("Page displays the correct statement");
       W.get().driver.navigate().back();
    }

    @When("searching for {string}")
    public void searchingFor(String item) {
        googlePage.search("BBC News");
        logger.info("BBC News searched");
    }

    @Then("results contain {string}")
    public void resultsContain(String searchResult) {
        List<String> allResults = googlePage.verifySearchResult().stream().map(result->result.getText().trim()).toList();
        Assert.assertTrue("Search result not matching",allResults.contains(searchResult));
        logger.info("Result contains the search result");
    }

    @And("result stats are displayed")
    public void resultStatsAreDisplayed() {
      Assert.assertTrue("Result stats not displayed",googlePage.verifyResultStatsDisplayed());
      logger.info("Result stats are present");
    }

    @And("number of {string} is more than {int}")
    public void numberOfIsMoreThan(String key, int param) {
        String resultStat = googlePage.validateResultStats();
        logger.info("Result stat is "+resultStat);
        Assert.assertTrue("Stats are wrong for "+key, resultStat.contains(key));
        Assert.assertFalse("Zero results found "+key, resultStat.contains("About 0 results"));
        logger.info("Number of result"+key+" is more than "+param);
    }

}
