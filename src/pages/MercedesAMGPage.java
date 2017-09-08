import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MercedesAMGPage {

    WebDriver driver;

    @FindBy(css = ".search-form__fields-list > li:nth-child(4) > div:nth-child(1)" +
            " > button:nth-child(1) > span:nth-child(1) > span:nth-child(2)")
    static WebElement make;

    @FindBy(css = ".search-form__fields-list > li:nth-child(5) > div:nth-child(1)" +
            " > button:nth-child(1) > span:nth-child(1) > span:nth-child(2)")
    static WebElement model;
}
