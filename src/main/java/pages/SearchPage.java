package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[contains(@class,'navigation-link') and contains(@href,'/login')]")
    WebElement btnLogin;
   // @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;

    public void clickBtnLogin(){
        btnLogin.click();
    }
      public void clickBtnSignUp(){
        btnSignUp.click();
    }


}