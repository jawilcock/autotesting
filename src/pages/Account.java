import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Account {
    WebDriver driver;

    @FindBy(css = "#customer_firstname")
    static WebElement firstName;

    @FindBy(css = "#customer_lastname")
    static WebElement lastName;

    @FindBy(css = "#passwd")
    static WebElement password;

    @FindBy(css = "#firstname")
    static WebElement firstNameAddress;

    @FindBy(css = "#lastname")
    static WebElement lastNameAddress;

    @FindBy(css = "#address1")
    static WebElement address;

    @FindBy(css = "#customer_lastname")
    static WebElement city;

    @FindBy(css = "#id_state")
    static WebElement stateDrop;

    @FindBy(css = "#id_state > option:nth-child(2)")
    static WebElement alabama;

    @FindBy(css = "#postcode")
    static WebElement postcode;

    @FindBy(css = "#customer_lastname")
    static WebElement phone;

    @FindBy(css = "#submitAccount")
    static WebElement register;
}