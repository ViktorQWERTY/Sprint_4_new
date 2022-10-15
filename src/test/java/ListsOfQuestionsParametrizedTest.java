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

//По сути дублирует класс ListsOfQuestionsTest, но только параметризованный. Вероятно, в данном случае корректнее писать этот тест не параметризованным.
@RunWith(Parameterized.class)
public class ListsOfQuestionsParametrizedTest {
    private static By questionElement;
    private static By answerElement;
    private static String answerText;

    private static final By costQuestionLocator = By.cssSelector("div#accordion__heading-0");
    private static final By quantityQuestionLocator = By.cssSelector("div#accordion__heading-1");
    private static final By rentTimeQuestionLocator = By.cssSelector("div#accordion__heading-2");
    private static final By todayRentQuestionLocator = By.cssSelector("div#accordion__heading-3");
    private static final By extendQuestionLocator = By.cssSelector("div#accordion__heading-4");
    private static final By batteryQuestionLocator = By.cssSelector("div#accordion__heading-5");
    private static final By cancelQuestionLocator = By.cssSelector("div#accordion__heading-6");
    private static final By deliveryTerritoryQuestionLocator = By.cssSelector("div#accordion__heading-7");

    private static final By costAnswerLocator = By.cssSelector("div#accordion__panel-0 > p");
    private static final By quantityAnswerLocator = By.cssSelector("div#accordion__panel-1 > p");
    private static final By rentTimeAnswerLocator = By.cssSelector("div#accordion__panel-2 > p");
    private static final By todayRentAnswerLocator = By.cssSelector("div#accordion__panel-3 > p");
    private static final By extendAnswerLocator = By.cssSelector("div#accordion__panel-4 > p");
    private static final By batteryAnswerLocator = By.cssSelector("div#accordion__panel-5 > p");
    private static final By cancelAnswerLocator = By.cssSelector("div#accordion__panel-6 > p");
    private static final By deliveryTerritoryAnswerLocator = By.cssSelector("div#accordion__panel-7 > p");

    private static final String costAnswerText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String quantityAnswerText = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String rentTimeAnswerText = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String todayRentAnswerText = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String extendOrderAnswerText = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String batteryAnswerText = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String cancelRentAnswerText = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String deliveryTerritoryAnswerText = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

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
        WebElement element = driver.findElement(mainPage.itemQuestionsBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        mainPage.clickAcceptCookie();
    }

    @Test
    public void checkAnswerText(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnQuestion(questionElement);
        mainPage.getAnswerText(answerElement);
        Assert.assertTrue("Проверка, что в раздле Вопросы о важном содержатся соответствующие ответы", mainPage.getAnswerText(answerElement).contains(answerText));
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
