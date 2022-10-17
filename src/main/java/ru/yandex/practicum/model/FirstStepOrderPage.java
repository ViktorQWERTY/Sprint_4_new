package ru.yandex.practicum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstStepOrderPage {

    public static final By firstNameField = By.cssSelector("[placeholder=\"* Имя\"]");
    public static final By secondNameField = By.cssSelector("[placeholder=\"* Фамилия\"]");
    public static final By addressField = By.cssSelector("[placeholder=\"* Адрес: куда привезти заказ\"]");
    public static final By phoneField = By.cssSelector("[placeholder=\"* Телефон: на него позвонит курьер\"]");
    public static final By metroField = By.cssSelector("[placeholder=\"* Станция метро\"]");
    public static final By moveToNextSteps = By.xpath("//*[contains(text(), 'Далее')]");

    private final WebDriver driver;
    //Конструктор
    public FirstStepOrderPage(WebDriver driver){
        this.driver = driver;
    }
    //ввод имени
    public void enterFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    //ввод фамилии
    public void enterSecondName(String secondName){
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    //ввод адреса
    public void enterAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }
    //ввод телефона
    public void enterPhone(String phone){
        driver.findElement(phoneField).sendKeys(phone);
    }
    //выбор станции метро
    public void enterMetroStation(String metroStation){
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(metroStation);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[contains(text(), '"+metroStation+"')]//parent::button"))).click();
    }
    //нажать кнопку далее
    public void moveToNextSteps(){
        driver.findElement(moveToNextSteps).click();
    }

}
