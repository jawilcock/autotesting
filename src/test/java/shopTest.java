import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.Page;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class shopTest {

    WebDriver driver;
    Actions builder;
    private static ExtentReportManager reportManager;
    SpreadSheetReader spreadSheetReader;

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
        PageFactory.initElements(driver,Dresses.class);

        spreadSheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/Login_Details.xlsx");

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

        TestSearchAndAdd();

        ExtentTest clearCart = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        clearCart.log(Status.INFO,"Moved to the home page.");

        shopHome.cartButton.click();
        clearCart.log(Status.INFO,"Moved to the cart page.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    @Test
    public void Customise(){

        ExtentTest customiseDress = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        customiseDress.log(Status.INFO,"Moved to the home page.");

        shopHome.dressButton.click();
        customiseDress.log(Status.INFO,"Moved to the dresses page.");

        Dresses.dressCategory.click();
        customiseDress.log(Status.INFO,"Selected summer dresses only.");

        Dresses.colourPicker.click();
        customiseDress.log(Status.INFO,"Selected blue summer dresses only.");

        Dresses.dressColour.click();
        customiseDress.log(Status.INFO,"Moved to the blue summer dress page.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            Assert.assertEquals(Dresses.dressName.getText(),"Printed Summer Dress");
            Assert.assertEquals(Dresses.colour.getAttribute("class").equalsIgnoreCase("color_pick selected"),true);

            customiseDress.log(Status.INFO,"Found the dress successfully");
            customiseDress.pass("Success");

        } catch(AssertionError a){
            customiseDress.log(Status.INFO,"Failed finding the dress.");
            customiseDress.fail("Failed");
        }

    }

    @Test
    public void CreateAccount(){

        ExtentTest createAccount = reportManager.setUpTest();

        driver.navigate().to("http://automationpractice.com/index.php");
        createAccount.log(Status.INFO,"Moved to the home page.");

        shopHome.signInButton.click();
        createAccount.log(Status.INFO,"Moved to the login page.");

        shopHome.emailTextBox.sendKeys(spreadSheetReader.readRow(0, "Sheet1").get(0));
        shopHome.createButton.click();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Account.firstName.sendKeys(spreadSheetReader.readRow(1, "Sheet1").get(0));
        Account.lastName.sendKeys(spreadSheetReader.readRow(2, "Sheet1").get(0));
        Account.password.sendKeys(spreadSheetReader.readRow(3, "Sheet1").get(0));
        Account.firstNameAddress.sendKeys(spreadSheetReader.readRow(1, "Sheet1").get(0));
        Account.lastNameAddress.sendKeys(spreadSheetReader.readRow(2, "Sheet1").get(0));
        Account.address.sendKeys(spreadSheetReader.readRow(4, "Sheet1").get(0));
        Account.city.sendKeys(spreadSheetReader.readRow(5, "Sheet1").get(0));
        Account.stateDrop.click();
        Account.alabama.click();
        Account.postcode.sendKeys(spreadSheetReader.readRow(6, "Sheet1").get(0));
        Account.phone.sendKeys(spreadSheetReader.readRow(7, "Sheet1").get(0));
        Account.register.click();
        createAccount.log(Status.INFO,"Filled the account form");

    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }
}
