package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/registration?url=%2Fsearch");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;

    public void clickBtnLoginHeader() {
        btnLogin.click();
        pause(5);
    }

    // Реализация pause в HomePage
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Pause was interrupted", e);
        }
    }
}