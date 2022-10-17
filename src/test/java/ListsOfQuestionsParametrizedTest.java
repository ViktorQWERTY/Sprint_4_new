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

import static ru.yandex.practicum.model.MainPage.*;

@RunWith(Parameterized.class)
public class ListsOfQuestionsParametrizedTest {
    private static By questionElement;
    private static By answerElement;
    private static String answerText;

    private WebDriver driver;

    public ListsOfQuestionsParametrizedTest(By questionElement, By answerElement, String answerText){
        this.questionElement = questionElement;
        this.answerElement = answerElement;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object [][] testData(){
        return new Object[][]{
                {costQuestionLocator, costAnswerLocator, costAnswerText},
                {quantityQuestionLocator, quantityAnswerLocator, quantityAnswerText},
                {rentTimeQuestionLocator ,rentTimeAnswerLocator ,rentTimeAnswerText},
                {todayRentQuestionLocator ,todayRentAnswerLocator ,todayRentAnswerText},
                {extendQuestionLocator ,extendAnswerLocator ,extendOrderAnswerText},
                {batteryQuestionLocator ,batteryAnswerLocator ,batteryAnswerText},
                {cancelQuestionLocator ,cancelAnswerLocator ,cancelRentAnswerText},
                {deliveryTerritoryQuestionLocator ,deliveryTerritoryAnswerLocator ,deliveryTerritoryAnswerText}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickAcceptCookie();
    }

    @Test
    public void checkAnswerText(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(questionElement);
        mainPage.getAnswerText(answerElement);
        mainPage.scrollToTheFAQ();
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(answerElement).contains(answerText));
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
