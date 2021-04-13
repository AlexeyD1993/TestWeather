package Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private WebDriver driver;

    @FindBy(id="open-dropdown")
    private WebElement userName;

    @FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div/div[2]")
    private WebElement popupMenu;

    @FindBy(id = "q")
    private WebElement cityInput;

    @FindBy(id = "pbar")
    private WebElement tableErrorSearchResult;

    @FindBy(xpath = "/html/body/main/div/div[2]/div[1]/div[1]/div[1]/h2")
    private WebElement cityFindedResult;

    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String GetUserName() {
        return userName.getText();
    }

    public String GetPopupLoginCorrectText() {
        try {
            return popupMenu.getText();
        }
        catch (NoSuchElementException e) {
            return "";
        }
    }

    public MainPage FindCity(String city) {
        //Ожидание прогрузки страницы
        $(cityInput).should(Condition.enabled);

        cityInput.sendKeys(city);
        cityInput.submit();

        //Ожидание появления таблицы с результатами поиска
        $(tableErrorSearchResult).shouldNot(Condition.visible);
        return this;
    }
}
