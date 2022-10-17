package ru.yandex.practicum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondStepOrderPage {

    public static final By rentDay = By.cssSelector("[placeholder=\"* Когда привезти самокат\"]");
    public static final By comment = By.cssSelector("[placeholder=\"Комментарий для курьера\"]");
    public static final By rentDurationLoc = By.cssSelector("div.Dropdown-placeholder");
    public static final By dayInCalendar = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");
    public static final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    public static final By confirmationButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Да')]");
    public static final By successfulRegistrationMessage = By.xpath("//*[contains(text(), 'Заказ оформлен')]");

    private final WebDriver driver;
    //Конструктор
    public SecondStepOrderPage(WebDriver driver){
        this.driver = driver;
    }
    //выбор даты аренды
    public void chooseRentDay(String dayOfStartRent){
        driver.findElement(rentDay).sendKeys(dayOfStartRent);
        driver.findElement(dayInCalendar).click();
    }
    //выбор срока аренды
    public void chooseRentDuration(String durationOfRent){
        driver.findElement(rentDurationLoc).click();
        driver.findElement(By.xpath(".//*[contains(text(), '"+durationOfRent+"')]")).click();
    }
    //выбор цвета
    public void chooseColor(String scooterColor){
        driver.findElement(By.xpath(".//*[contains(text(), '"+scooterColor+"')]")).click();
    }
    //ввод комментария для курьера
    public void writeComment(String commentText){
        driver.findElement(comment).sendKeys(commentText);
    }
    //нажать кнопку Заказать
    public void clickOrder(){
        driver.findElement(orderButton).click();
    }
    //нажать кнопку Да для подвтверждения заказа
    public void confirmOrder(){
        driver.findElement(confirmationButton).click();
    }
    //проверяем, что появиалось сообщение об успешной регистрации
    public void successfulRegistrationMessage(){
        driver.findElement(successfulRegistrationMessage).isDisplayed();
    }

}
