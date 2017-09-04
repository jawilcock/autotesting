import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {


    WebDriver driver;
    @Before
    public void setup(){
        driver = new ChromeDriver();
    }

    @After
    public void testOver(){
        driver.quit();
    }

    @Test
    public void myTest(){
        //driver.navigate().to("https://twitch.tv/musseduch");
        System.out.println("Hello World");
    }

}
