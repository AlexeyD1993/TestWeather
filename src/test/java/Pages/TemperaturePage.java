package Pages;

import TableParcers.ResultTableParcer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void init(final WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public String GetTemperature() {
        return temperature.getText();
    }

    public void SwithTemperature() throws InterruptedException {
        //Ожидание прогрузки изменений температуры на странице
        //var waitLoadPage = new WebDriverWait(driver, 10);

        //TODO надо разобраться с ожиданием доступности переключателя
        Thread.currentThread().sleep(3000);
        if (temperatureSelector.getText().contains("°C")) {
            ChangeTempToFarengeith();
            //waitLoadPage.until(ExpectedConditions.attributeContains(farengieth, ".widget-switch.switch-blue .switch-input:checked + label[data-v-7bd695c3]", "color:#fff"));
        }
        else {
            ChangeTempToCelsius();
            //waitLoadPage.until(ExpectedConditions.textToBe(By.xpath("/html/body/main/div/div[1]/div/div/div[1]/div/div[2]/div/span"), "Metric: °C, m/s"));
        }

        Thread.currentThread().sleep(3000);
        return;
    }

    public void ChangeTempToFarengeith() {
        farengieth.click();

    }

    public void ChangeTempToCelsius() {
        celsius.click();
    }
}
