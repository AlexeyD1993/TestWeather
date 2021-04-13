package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loader {
    @FindBy(xpath="/html/body/div[3]/div")
    public WebElement loaderElement;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void waitLoad() {
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Ожидание прогрузки страницы
//        try {
//            $(loader.loaderElement).should(Condition.exist);
//        }
//        catch (NullPointerException ex) {}
    }
}
