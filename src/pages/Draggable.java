import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Draggable {
    WebDriver driver;

    @FindBy(id = "draggable")
    static WebElement draggable;
}