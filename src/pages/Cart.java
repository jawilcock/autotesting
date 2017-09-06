import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart {
    WebDriver driver;

    @FindBy(css = ".icon-trash")
    static WebElement deleteButton;

    @FindBy(css = ".alert")
    static WebElement deleteMessage;
}