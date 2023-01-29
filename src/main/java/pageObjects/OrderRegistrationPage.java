package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderRegistrationPage {

    private WebDriver driver;

    // Поле Имя
    private By name = By.cssSelector("[placeholder='* Имя']");
    // Поле Фамилия
    private By surname = By.cssSelector("[placeholder='* Фамилия']");
    // Поле Адрес
    private By address = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    // Поле Метро
    private By metroStation = By.cssSelector("[placeholder='* Станция метро']");
    //Список возможный станций
    private By listOfMetroStations = By.xpath(".//li[@class='select-search__row']/button");
    // Поле Телефон
    private By phone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");

    // Поле Дата
    private By date = By.cssSelector("[placeholder='* Когда привезти самокат']");
    // Поле Срок аренды
    private By rentalPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Меню срока аренды
    private By rentalPeriodMenu = By.xpath(".//div[@class='Dropdown-option']");
    // Поле Цвет самоката
    private By color = By.className("Checkbox_Label__3wxSf");
    // Поле Комментарий
    private By comment = By.cssSelector("[placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Подтверждение заказа
    private By confirmOrderButton = By.xpath(".//button[text()='Да']");

    // Окно Заказ оформлен
    private By orderSucceedTitle = By.xpath(".//div[text()='Заказ оформлен']");
    // Кнопка Посмотреть статус
    private By statusButton = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForOrderPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(name));
    }

    public void fillOrderFields(String clientName, String clientSurname, String clientAddress, int numberOfStation, String clientPhone, String clientDate, int numberOfRentalPeriod, int clientColor, String clientComment) {
        driver.findElement(name).sendKeys(clientName);
        driver.findElement(surname).sendKeys(clientSurname);
        driver.findElement(address).sendKeys(clientAddress);
        driver.findElement(metroStation).click();
        driver.findElements(listOfMetroStations).get(numberOfStation).click();
        driver.findElement(phone).sendKeys(clientPhone);
        driver.findElement(nextButton).click();
        driver.findElement(date).sendKeys(clientDate);
        driver.findElement(date).sendKeys(Keys.ENTER);
        driver.findElement(rentalPeriod).click();
        driver.findElements(rentalPeriodMenu).get(numberOfRentalPeriod).click();
        driver.findElements(color).get(clientColor).click();
        driver.findElement(comment).sendKeys(clientComment);
    }

    public void orderRegistration() {
        driver.findElement(orderButton).isEnabled();
        driver.findElement(orderButton).click();
        driver.findElement(confirmOrderButton).isDisplayed();
        driver.findElement(confirmOrderButton).click();
    }

    public void checkSucceedOrderRegistration() {
        driver.findElement(orderSucceedTitle).isDisplayed();
        driver.findElement(statusButton).isDisplayed();
    }
}
