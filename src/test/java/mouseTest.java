import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.Page;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class mouseTest {

    WebDriver driver;
    Actions builder;
    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void beforeSetup(){
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\MouseTestReport",
                "Basic Extent Report","Basic Report");
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML,reportDetails);
    }

    @Before
    public void setup(){

        driver = new ChromeDriver();
        builder = new Actions(driver);
        PageFactory.initElements(driver,Draggable.class);
        PageFactory.initElements(driver,Dropping.class);
        PageFactory.initElements(driver,Resizing.class);
    }

    @After
    public void testOver(){
        driver.quit();
    }

    @Test
    public void TestDraggable(){

        ExtentTest draggingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/draggable/");
        draggingbox.log(Status.INFO,"Moved to the draggable page.");

        int oldX = Draggable.draggable.getLocation().x;
        int oldY = Draggable.draggable.getLocation().y;

        builder.dragAndDropBy(Draggable.draggable, 300, 200).perform();
        draggingbox.log(Status.INFO,"Attempted to drag box");
        try{
            int difX = Draggable.draggable.getLocation().x - oldX;
            int difY = Draggable.draggable.getLocation().y - oldY;

            Assert.assertEquals(difX,300);
            Assert.assertEquals(difY,200);
            draggingbox.log(Status.INFO,"Box dragged successfully");
            draggingbox.pass("Dragged");
        }
        catch(AssertionError a){
            draggingbox.log(Status.INFO,"Failed dragging.");
            draggingbox.fail("Failed");
        }
    }

    @Test
    public void TestDroppable(){
        ExtentTest droppingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/droppable/");
        droppingbox.log(Status.INFO,"Moved to the dropping page.");

        builder.dragAndDropBy(Dropping.draggableBox, 150, 20).perform();
        droppingbox.log(Status.INFO,"Attempted to drag box");

        try{
            Assert.assertEquals(Dropping.droppingView.getText(),"Dropped!");

            droppingbox.log(Status.INFO,"Box dropped successfully");
            droppingbox.pass("Dragged");
        }
        catch(AssertionError a){
            droppingbox.log(Status.INFO,"Failed dropping.");
            droppingbox.fail("Failed");
        }
    }

    @Test
    public void TestResizable(){
        ExtentTest resizingbox = reportManager.setUpTest();

        driver.navigate().to("http://demoqa.com/resizable/");
        resizingbox.log(Status.INFO,"Moved to the resizing page.");

        int oldWidth = Resizing.resizable.getRect().width;
        int oldHeight = Resizing.resizable.getRect().height;

        builder.dragAndDropBy(Resizing.resizable, 100, 100).perform();
        resizingbox.log(Status.INFO,"Attempted to resize box");

        try{
            int difX = Resizing.resizable.getRect().width - oldWidth;
            int difY = Resizing.resizable.getRect().height - oldHeight;

            Assert.assertEquals(difX,100);
            Assert.assertEquals(difY,100);
            resizingbox.log(Status.INFO,"Box dragged successfully");
            resizingbox.pass("Dragged");
        }
        catch(AssertionError a){
            resizingbox.log(Status.INFO,"Failed dragging.");
            resizingbox.fail("Failed");
        }
    }

    @AfterClass
    public static void cleanUp(){
        reportManager.clearTests();
    }

}
