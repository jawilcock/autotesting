import org.junit.*;

public class TestClasses {

    @BeforeClass
    public static void before(){
        System.out.println("Before Class");
    }

    @Before
    public void setup(){
        System.out.println("Before");
    }

    @After
    public void testOver(){
        System.out.println("After");
    }

    @AfterClass
    public static void after(){
        System.out.println("After Class");
    }

    @Test
    public void myTest(){
        System.out.println("Test");
    }

}
