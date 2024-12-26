package pages;

import dto.CarDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetCarWorkPage extends BasePage {
    public LetCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;
    @FindBy(xpath = "//div[@class='pac-item']")
    WebElement locationSubmit;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement inputFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(id = "about")
    WebElement inputAbout;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    //==========================
//    @FindBy(xpath = "//h2[@class='message']")
//    WebElement popUpMessage;

    //Haifa Ford  Focus   Haifa Mazda  Focus
    //TelAviv Mazda CX-5
    public void typeLetCarWorkForm(CarDto car) {
        inputLocation.sendKeys(car.getCity());
        clickWait(locationSubmit, 5);
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        //===================================
        inputFuel.click();
        clickWait(driver.findElement(By.xpath(car.getFuel())), 5);
        inputFuel.sendKeys(Keys.ESCAPE);
        //===================================
        inputSeats.sendKeys(car.getSeats() + "");
        inputClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(Double.toString(car.getPricePerDay()));
        inputAbout.sendKeys(car.getAbout());
        clickWait(btnSubmit, 5);
    }

    public boolean isPopUpMessagePresent(String text){
        return isTextInElementPresent(popUpMessage, text);
    }
}
