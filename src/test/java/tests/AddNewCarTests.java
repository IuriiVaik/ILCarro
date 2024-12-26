package tests;

import dto.CarDto;
import dto.UserDtoLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LetCarWorkPage;
import pages.LoginPage;
import pages.SearchPage;
import utils.Fuel;

import java.util.Random;

public class AddNewCarTests extends ApplicationManager {
    LoginPage loginPage;
    LetCarWorkPage letCarWorkPage;

    @BeforeMethod
    public void login() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("alexmed123@gmail.com")
                .password("Qwerty123!")
                .build();
        new SearchPage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        if (loginPage.isPopUpLoginMessagePresent("Logged in success")) {
            System.out.println("login success");
            loginPage.clickBtnOK();
            loginPage.clickBtnLetCarWork();
        } else
            System.out.println("Something went wrong");
    }

    @Test
    public void addNewCarPositiveTest() {
        CarDto car = CarDto.builder()
                .serialNumber(new Random().nextInt(1000) + "-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();
        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letCarWorkPage
                .isPopUpMessagePresent(car.getManufacture() + " " + car.getModel() + " " + "added successful"));

    }

    @Test
    public void addNewCarNegativeTest_emptySerialNumber() {

        CarDto car = CarDto.builder()
                .serialNumber("")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();


        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);
        Assert.assertTrue(letCarWorkPage
                        .isErrorMessagePresent("Car registration number is required"),
                "Error message for empty serial number is not displayed as expected");
    }
    @Test
    public void addNewCarNegativeTest_emptyYear() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isYearErrorMessagePresent("Year required"),
                "Error message for empty year is not displayed as expected");
    }

    @Test
    public void addNewCarNegativeTest_emptyCity() {
        // Создаем объект с пустым городом
        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isCityErrorMessagePresent("Wrong address"),
                "Error message for empty city is not displayed as expected");
    }
    @Test
    public void addNewCarNegativeTest_emptyCarClass() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isCarClassErrorMessagePresent("Car class is required"),
                "Error message for empty car class is not displayed as expected");
    }

    @Test
    public void addNewCarNegativeTest_emptyPrice() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(-123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isPriceErrorMessagePresent("Price must be positive"),
                "Error message for empty price per day is not displayed as expected");
    }

    @Test
    public void addNewCarNegativeTest_emptyModel() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isModelErrorMessagePresent("Model is required"),
                "Error message for empty model is not displayed as expected");
    }

    @Test
    public void addNewCarNegativeTest_invalidSeats() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("Mazda")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(0)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isSeatsErrorMessagePresent("Car must have min 2 seat"),
                "Error message for invalid number of seats is not displayed as expected");
    }

    @Test
    public void addNewCarNegativeTest_emptyManufacture() {

        CarDto car = CarDto.builder()
                .serialNumber("123-055")
                .city("Haifa")
                .manufacture("")
                .model("CX-90")
                .year("2022")
                .fuel(Fuel.HYBRID.getLocator())
                .seats(4)
                .carClass("A")
                .pricePerDay(123.99)
                .about("About my car")
                .build();

        letCarWorkPage = new LetCarWorkPage(getDriver());
        letCarWorkPage.typeLetCarWorkForm(car);


        Assert.assertTrue(letCarWorkPage
                        .isManufactureErrorMessagePresent("Make is required"),
                "Error message for empty manufacture is not displayed as expected");
    }



}
