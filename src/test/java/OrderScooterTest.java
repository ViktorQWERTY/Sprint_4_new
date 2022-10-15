import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.model.FirstStepOrderPage;
import ru.yandex.practicum.model.MainPage;
import ru.yandex.practicum.model.SecondStepOrderPage;


@RunWith(Parameterized.class)
public class OrderScooterTest {

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

    public OrderScooterTest(String firstName, String secondName, String address, String metroStation, String phone, String rentDay, String rentDuration, String color, String comment){
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
                {"Вениамин", "Самокатов", "Москва, пр-кт Мира, дом 45", "Комсомольская", "+79879879879", "15.10.2022", "двое суток", "чёрный жемчуг", "Комментирую"},
                {"Самокат", "Вениаминов", "Москва, пр-кт Мира, дом 54", "Сокольники", "+79899898989", "16.10.2022", "сутки", "серая безысходность", "Опять комментирую"}
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
    public void UpperButton(){
        MainPage mainPage = new MainPage(driver);
        mainPage.startOrderButton(mainPage.UPPER_ORDER_BUTTON);
        FirstStepOrderPage firstStepOrderPage = new FirstStepOrderPage(driver);
        mainPage.clickAcceptCookie();
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

    @Test
    public void DownButton(){
        MainPage mainPage = new MainPage(driver);
        WebElement element = driver.findElement(mainPage.DOWN_ORDER_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        mainPage.startOrderButton(mainPage.DOWN_ORDER_BUTTON);
        FirstStepOrderPage firstStepOrderPage = new FirstStepOrderPage(driver);
        mainPage.clickAcceptCookie();
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
