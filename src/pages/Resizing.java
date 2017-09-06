import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Resizing {
    WebDriver driver;

    @FindBy(css = "#resizable > div:nth-child(4)")
    static WebElement resizable;

}