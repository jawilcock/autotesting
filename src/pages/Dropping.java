import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dropping {
    WebDriver driver;

    @FindBy(id = "draggableview")
    static WebElement draggableBox;

    @FindBy(id = "droppableview")
    static WebElement droppingView;

}