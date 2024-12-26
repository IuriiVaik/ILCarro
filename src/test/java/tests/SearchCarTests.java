package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResultsPage;
import pages.SearchPage;

public class SearchCarTests extends ApplicationManager {
    SearchPage searchPage;

    @Test
    public void searchCarPositiveTest(){
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendar("Haifa", "12/25/2024", "12/27/2024");
        Assert.assertTrue(new ResultsPage(getDriver()).isUrlResultsPresent());
    }
}
