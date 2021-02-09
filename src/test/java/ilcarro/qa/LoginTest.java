package ilcarro.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver wd;

    @BeforeClass
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro-dev-v1.firebaseapp.com/");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLogIn() throws InterruptedException {
        //click on Login
//        click(By.cssSelector("[href='/login']"));
        clickByCss("[href='/login']");//login btn
        fillLoginForm("JohnDoe864905@mail.com", "Test12345");
        Thread.sleep(2000);
        clickByCss("[type='submit']");
    }

   public void fillLoginForm(String email, String password) {
        typeByCss("input[placeholder='Email']", email);//fill the email field
        typeByCss("[name=password]", password);//fill the pswd field
    }

    public void typeByCss(String cssSelector, String text) {
        clickByCss(cssSelector); //text field
        wd.findElement(By.cssSelector(cssSelector)).clear();
        wd.findElement(By.cssSelector(cssSelector)).sendKeys(text);
    }

    public void clickByCss(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    public void clickByXpath(String xpath) {
        wd.findElement(By.cssSelector(xpath)).click();
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    @AfterClass
    public void tearDown() {

    }
}
