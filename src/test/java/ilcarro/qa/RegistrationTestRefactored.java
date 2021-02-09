package ilcarro.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTestRefactored {
    //TODO: refactor
    //assertion
    //TODO: @Test2 negative

    WebDriver wd;

    String firstName = "John";
    String secondName = "Doe";
    String password = "Test12345";

    Random random = new Random();
    int int_random = random.nextInt(1000000);
    String email = firstName + secondName + int_random + "@mail.com";

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro-dev-v1.firebaseapp.com/"); //the same as wd.get
    }

    @Test
    public void testRegistration() throws InterruptedException {
        getSignUp();
        fillRegForm();
        selectCheckBox();
        pause(2000);
        clickSubmitBtn();
        pause(3000);

        //TODO: assertion
//        Assert.assertEquals();

    }

    public void clickSubmitBtn() {
        WebElement submitBtn = wd.findElement(By.cssSelector("[type='submit']"));
        click(submitBtn);
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void selectCheckBox() {
        WebElement checkBox = wd.findElement(By.id("check_policy"));
        click(checkBox);
    }

    public void getSignUp() {
        wd.findElement(By.cssSelector("[href='/signup']")).click();
    }

    public void fillRegForm() {
        WebElement firstNameElement = wd.findElement(By.id("first_name"));
        type(firstNameElement,firstName);

        WebElement secondNameElement = wd.findElement(By.id("second_name"));
        type(secondNameElement,secondName);

        //simple log of credentials
        System.out.println("email is:" + email);

        WebElement emailElement = wd.findElement(By.id("email"));
        type(emailElement, email);

        WebElement pswdElement = wd.findElement(By.id("password"));
        type(pswdElement,password);
    }

    public void click(WebElement element){
        element.click();
    }

    public void type(WebElement element,String text){
        click(element);
        element.clear();
        element.sendKeys(text);
    }

    @AfterMethod(enabled = false) //method/test cancellation
    public void tearDown() {
//        wd.close(); //close only tab, but web browser close only if it was one and only window
        wd.quit(); //close windows and web browser

    }
}
