import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.Page;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class shopTest {

    WebDriver driver;
    Actions builder;
    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void beforeSetup(){
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ShopTestReport",
                "Basic Extent Report","Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML,reportDetails);
    }

    @Before
    public void setup(){

        driver = new ChromeDriver();
        builder = new Actions(driver);
        PageFactory.initElements(driver,shopHome.class);
        PageFactory.initElements(driver,Cart.class);
    }

    @After
    public void testOver(){
        driver.quit();
    }

    @Test
    public void TestSearchAndAdd(){

        ExtentTest searching = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        searching.log(Status.INFO,"Moved to the home page.");

        shopHome.searchBox.sendKeys("Printed Summer Dress");
        shopHome.searchButton.click();
        searching.log(Status.INFO,"Moved to the Summer Dress page.");

        shopHome.addCartButton.click();
        searching.log(Status.INFO,"Added dress to cart.");

        try{
            Assert.assertEquals(shopHome.successTick.getAttribute("class").equalsIgnoreCase("icon-ok"),true);

            searching.log(Status.INFO,"Added dress to cart successfully");
            searching.pass("Success");

        } catch(AssertionError a){
            searching.log(Status.INFO,"Failed adding to cart.");
            searching.fail("Failed");
        }
    }

    @Test
    public void ClearCart(){
        ExtentTest clearCart = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        clearCart.log(Status.INFO,"Moved to the home page.");

        shopHome.cartButton.click();
        clearCart.log(Status.INFO,"Moved to the cart page.");

        Cart.deleteButton.click();
        try{
            Assert.assertEquals(Cart.deleteMessage.getAttribute("class").equalsIgnoreCase("alert alert-warning"),true);

            clearCart.log(Status.INFO,"Cleared cart successfully");
            clearCart.pass("Success");

        } catch(AssertionError a){
            clearCart.log(Status.INFO,"Failed clearing the cart.");
            clearCart.fail("Failed");
        }
    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }
}
