package Pages;

import Elements.Loader;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;

public class TemperaturePage {
    private WebDriver driver;

    @FindBy(xpath="/html/body/main/div/div[2]/div[1]/div[1]/div[2]/div[1]/span")
    private WebElement temperature;

    @FindBy(xpath = "/html/body/main/div/div[1]/div/div/div[1]/div/div[2]/div/label[2]")
    private WebElement farengieth;

    @FindBy(xpath = "/html/body/main/div/div[1]/div/div/div[1]/div/div[2]/div/label[1]")
    private WebElement celsius;

    @FindBy(css = ".switch-input:checked + label[data-v-7bd695c3]")
    private WebElement temperatureSelector;

    private Loader loader;

    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loader = new Loader();
        loader.init(driver);
    }

    public String GetTemperature() {
        loader.waitLoad();
        return temperature.getText();
    }

    public void SwitchTemperature() {
        //Ожидание загрузки страницы
        $(temperature).should(Condition.exist);
        if (temperatureSelector.getText().contains("°C")) {
            ChangeTempToFarengeith();
        }
        else {
            ChangeTempToCelsius();
        }

        //Устал бороться с JS. Заглушка на ожидание прогрузки смены температуры
        loader.waitLoad();
    }

    public void ChangeTempToFarengeith() {
        farengieth.click();
    }

    public void ChangeTempToCelsius() {
        celsius.click();
    }
}
