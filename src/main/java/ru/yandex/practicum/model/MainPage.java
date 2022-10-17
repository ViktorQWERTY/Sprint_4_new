package ru.yandex.practicum.model;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    //локаторы вопросов
    public static final By costQuestionLocator = By.cssSelector("div#accordion__heading-0");
    public static final By quantityQuestionLocator = By.cssSelector("div#accordion__heading-1");
    public static final By rentTimeQuestionLocator = By.cssSelector("div#accordion__heading-2");
    public static final By todayRentQuestionLocator = By.cssSelector("div#accordion__heading-3");
    public static final By extendQuestionLocator = By.cssSelector("div#accordion__heading-4");
    public static final By batteryQuestionLocator = By.cssSelector("div#accordion__heading-5");
    public static final By cancelQuestionLocator = By.cssSelector("div#accordion__heading-6");
    public static final By deliveryTerritoryQuestionLocator = By.cssSelector("div#accordion__heading-7");
    //локаторы ответов
    public static final By costAnswerLocator = By.cssSelector("div#accordion__panel-0 > p");
    public static final By quantityAnswerLocator = By.cssSelector("div#accordion__panel-1 > p");
    public static final By rentTimeAnswerLocator = By.cssSelector("div#accordion__panel-2 > p");
    public static final By todayRentAnswerLocator = By.cssSelector("div#accordion__panel-3 > p");
    public static final By extendAnswerLocator = By.cssSelector("div#accordion__panel-4 > p");
    public static final By batteryAnswerLocator = By.cssSelector("div#accordion__panel-5 > p");
    public static final By cancelAnswerLocator = By.cssSelector("div#accordion__panel-6 > p");
    public static final By deliveryTerritoryAnswerLocator = By.cssSelector("div#accordion__panel-7 > p");
    //текст ответов
    public static final String costAnswerText = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String quantityAnswerText = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String rentTimeAnswerText = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String todayRentAnswerText = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String extendOrderAnswerText = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String batteryAnswerText = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String cancelRentAnswerText = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String deliveryTerritoryAnswerText = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    //локатор блока с FAQ
    public static By itemQuestionsBlock = By.cssSelector("div.Home_FAQ__3uVm4");
    //кнопка принятия куки
    private static final By acceptCookieButton = By.cssSelector("button#rcc-confirm-button");
    //лэндинг
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //кнопка Заказать наверху
    public static final By UPPER_ORDER_BUTTON = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g");
    //кнопка Заказать внизу
    public static final By DOWN_ORDER_BUTTON = By.cssSelector("div.Home_FinishButton__1_cWm > button");

    private final WebDriver driver;

    //Конструктор
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    //Открыть страницу
    public void openPage(){
        driver.get(PAGE_URL);
    }
    //Нажать на кнопку Заказать
    public void startOrderButton(By orderButton){
        driver.findElement(orderButton).click();
    }
    //Получить текст ответа
    public String getAnswerText(By locator){
        return driver.findElement(locator).getText();
    }
    //Кликнуть на вопрос
    public void clickOnQuestion(By question){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(question)).click();
    }
    //Принять куки
    public void clickAcceptCookie(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
    }
    //Скролл до блока с FAQ
    public void scrollToTheFAQ(){
        WebElement element = driver.findElement(itemQuestionsBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
