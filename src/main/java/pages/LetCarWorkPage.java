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

    @FindBy(xpath = "//div[contains(text(), 'Car registration number is required')]")
    WebElement errorMessageSerialNumber;

    @FindBy(xpath = "//div[contains(text(), 'Year required')]")
    WebElement errorMessageYear;

    @FindBy(xpath = "//div[contains(text(), 'Wrong address')]")
    WebElement errorMessageCity;

    @FindBy(xpath = "//div[contains(text(), 'Car class is required')]")
    WebElement errorMessageCarClass;

    @FindBy(xpath = "//div[contains(text(), 'Price must be positive')]")
    WebElement errorMessagePrice;

    @FindBy(xpath = "//div[contains(text(), 'About is required')]")
    WebElement errorMessageAbout;

    @FindBy(xpath = "//div[contains(text(), 'Model is required')]")
    WebElement errorMessageModel;

    @FindBy(xpath = "//div[contains(text(), 'Car must have min 2 seat')]")
    public WebElement errorMessageSeats;

    @FindBy(xpath = "//div[contains(text(), 'Make is required')]")
    public WebElement errorMessageManufacture;


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
    public boolean isErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageSerialNumber, text);
    }
    public boolean isYearErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageYear, text);
    }
    public boolean isCityErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageCity, text);
    }
    public boolean isCarClassErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageCarClass, text);
    }

    public boolean isPriceErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessagePrice, text);
    }

    public boolean isModelErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageModel, text);
    }
    public boolean isSeatsErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageSeats, text);
    }
    public boolean isManufactureErrorMessagePresent(String text) {
        return isTextInElementPresent(errorMessageManufacture, text);
    }

}
