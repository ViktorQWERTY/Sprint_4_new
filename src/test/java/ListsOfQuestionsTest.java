import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practicum.model.MainPage;

public class ListsOfQuestionsTest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        WebElement element = driver.findElement(mainPage.itemQuestionsBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        mainPage.clickAcceptCookie();
    }

    @Test
    public void priceQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.costQuestionLocator);
        mainPage.getAnswerText(mainPage.costAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.costAnswerLocator).contains(mainPage.costAnswerText));
    }
    @Test
    public void quantityQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.quantityQuestionLocator);
        mainPage.getAnswerText(mainPage.quantityAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.quantityAnswerLocator).contains(mainPage.quantityAnswerText));
    }
    @Test
    public void rentTimeQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.rentTimeQuestionLocator);
        mainPage.getAnswerText(mainPage.rentTimeAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.rentTimeAnswerLocator).contains(mainPage.rentTimeAnswerText));
    }
    @Test
    public void todayRentQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.todayRentQuestionLocator);
        mainPage.getAnswerText(mainPage.todayRentAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.todayRentAnswerLocator).contains(mainPage.todayRentAnswerText));
    }
    @Test
    public void extendQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.extendQuestionLocator);
        mainPage.getAnswerText(mainPage.extendAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.extendAnswerLocator).contains(mainPage.extendOrderAnswerText));
    }
    @Test
    public void batteryQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.batteryQuestionLocator);
        mainPage.getAnswerText(mainPage.batteryAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.batteryAnswerLocator).contains(mainPage.batteryAnswerText));
    }
    @Test
    public void cancelQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.cancelQuestionLocator);
        mainPage.getAnswerText(mainPage.cancelAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.cancelAnswerLocator).contains(mainPage.cancelRentAnswerText));
    }
    @Test
    public void deliveryTerritoryQuestions(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(mainPage.deliveryTerritoryQuestionLocator);
        mainPage.getAnswerText(mainPage.deliveryTerritoryAnswerLocator);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(mainPage.deliveryTerritoryAnswerLocator).contains(mainPage.deliveryTerritoryAnswerText));
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
