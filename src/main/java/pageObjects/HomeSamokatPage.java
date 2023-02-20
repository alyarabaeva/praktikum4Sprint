package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HomeSamokatPage {

    private WebDriver driver;

    // Кнопка ринятия куки
    private By cookieButton = By.id("rcc-confirm-button");
    // Заголовок "Вопросы о важном"
    private By questionsHeader = By.className("Home_SubHeader__zwi_E");
    // Блок с вопросами
    private By questions = By.xpath(".//div[@class='accordion']");
    // Список вопросов, к элементам которого можно обращаться
    private By listOfQuestions = By.xpath(".//div[@class='accordion__item']/div/div");
    // Список ответов
    private By listOfAnswers = By.xpath(".//div[@class='accordion__panel']");
    // Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[text()='Заказать']");


    public HomeSamokatPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookie() {
        driver.findElement(cookieButton).isDisplayed();
        driver.findElement(cookieButton).click();
    }

    // Переход к блоку с вопросами на домашней странице
    public void scrollToQuestionsBlock() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questions));
        driver.findElement(questionsHeader).isDisplayed();
        driver.findElement(questions).isDisplayed();
    }

    // Получение ответа на выбранный вопрос
    public String getAnswerOnTheQuestion(int numberOfQuestion) {
        driver.findElements(listOfQuestions).get(numberOfQuestion).click();
        return driver.findElements(listOfAnswers).get(numberOfQuestion).getText();
    }

    //Открытие формы оформления заказа кнопкой из хедера
    public void openOrderRegistrationPage(int numOfOrderButton) {
        if (!driver.findElements(orderButton).get(numOfOrderButton).isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(orderButton).get(numOfOrderButton));
        }
        driver.findElements(orderButton).get(numOfOrderButton).click();
    }
}
