import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Resizing {
    WebDriver driver;

    @FindBy(className = "ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se")
    static WebElement resizable;
}