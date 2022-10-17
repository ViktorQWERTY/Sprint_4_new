import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.model.FirstStepOrderPage;
import ru.yandex.practicum.model.MainPage;
import ru.yandex.practicum.model.SecondStepOrderPage;

import static ru.yandex.practicum.model.MainPage.DOWN_ORDER_BUTTON;
import static ru.yandex.practicum.model.MainPage.UPPER_ORDER_BUTTON;


@RunWith(Parameterized.class)
public class OrderScooterTest {

    private static By orderButton;
    private static String firstName;
    private static String secondName;
    private static String address;
    private static String metroStation;
    private static String phone;
    private static String rentDay;
    private static String rentDuration;
    private static String color;
    private static String comment;

    private WebDriver driver;

    public OrderScooterTest(By orderButton, String firstName, String secondName, String address, String metroStation, String phone, String rentDay, String rentDuration, String color, String comment){
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentDay = rentDay;
        this.rentDuration = rentDuration;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object [][] testData(){
        return new Object[][]{
                {UPPER_ORDER_BUTTON, "Вениамин", "Самокатов", "Москва, пр-кт Мира, дом 45", "Комсомольская", "+79879879879", "15.10.2022", "двое суток", "чёрный жемчуг", "Комментирую"},
                {DOWN_ORDER_BUTTON, "Вениамин", "Самокатов", "Москва, пр-кт Мира, дом 45", "Комсомольская", "+79879879879", "15.10.2022", "двое суток", "чёрный жемчуг", "Комментирую"},
                {UPPER_ORDER_BUTTON, "Самокат", "Вениаминов", "Москва, пр-кт Мира, дом 54", "Сокольники", "+79899898989", "16.10.2022", "сутки", "серая безысходность", "Опять комментирую"},
                {DOWN_ORDER_BUTTON, "Самокат", "Вениаминов", "Москва, пр-кт Мира, дом 54", "Сокольники", "+79899898989", "16.10.2022", "сутки", "серая безысходность", "Опять комментирую"}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
    }

    @Test
    public void orderButton(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAcceptCookie();
        mainPage.startOrderButton(orderButton);
        FirstStepOrderPage firstStepOrderPage = new FirstStepOrderPage(driver);
        firstStepOrderPage.enterFirstName(firstName);
        firstStepOrderPage.enterSecondName(secondName);
        firstStepOrderPage.enterAddress(address);
        firstStepOrderPage.enterMetroStation(metroStation);
        firstStepOrderPage.enterPhone(phone);
        firstStepOrderPage.moveToNextSteps();

        SecondStepOrderPage secondStepOrderPage = new SecondStepOrderPage(driver);
        secondStepOrderPage.chooseRentDay(rentDay);
        secondStepOrderPage.chooseRentDuration(rentDuration);
        secondStepOrderPage.chooseColor(color);
        secondStepOrderPage.writeComment(comment);
        secondStepOrderPage.clickOrder();
        secondStepOrderPage.confirmOrder();
        secondStepOrderPage.successfulRegistrationMessage();
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
