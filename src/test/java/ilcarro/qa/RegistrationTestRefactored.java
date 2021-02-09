package ilcarro.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTestRefactored {
    WebDriver wd;
    String firstName = "John";
    String secondName = "Doe";
    String password = "Test12345";

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro-dev-v1.firebaseapp.com/"); //the same as wd.get
    }

    @Test
    public void testRegistrationPositive() throws InterruptedException {
        getSignUp();
        fillRegForm();
        selectCheckBox();
        pause(2000);
        clickSubmitBtn();
        pause(3000);
        isLogIn();
    }

    @Test
    public void testRegistrationNegative() throws InterruptedException {
        getSignUp();
        fillIncorrectMailRegForm();
        selectCheckBox();
        pause(2000);
        clickSubmitBtn();
        pause(3000);
        isRegistration();
    }

    public void isLogIn() {
        WebElement regFormTitle = wd.findElement(By.xpath("//h2[contains(.,'Log in')]"));
        Assert.assertEquals(regFormTitle.getText(), "Log in");
    }

    public void isRegistration() {
        WebElement regFormTitle = wd.findElement(By.xpath("//h2[contains(.,'Registration')]"));
        Assert.assertEquals(regFormTitle.getText(), "Registration");
    }

    public int randomiser() {
        Random random = new Random();
        return random.nextInt(1000000);
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
        type(firstNameElement, firstName);

        WebElement secondNameElement = wd.findElement(By.id("second_name"));
        type(secondNameElement, secondName);

        String email = firstName + secondName + randomiser()
                + "@mail.com";

        //simple log of credentials
        System.out.println("email is:" + email);

        WebElement emailElement = wd.findElement(By.id("email"));
        type(emailElement, email);

        WebElement pswdElement = wd.findElement(By.id("password"));
        type(pswdElement, password);
    }

    public void fillIncorrectMailRegForm() {
        WebElement firstNameElement = wd.findElement(By.id("first_name"));
        type(firstNameElement, firstName);

        WebElement secondNameElement = wd.findElement(By.id("second_name"));
        type(secondNameElement, secondName);

        String email = firstName + secondName + randomiser()
                + "mail.com";

        WebElement emailElement = wd.findElement(By.id("email"));
        type(emailElement, email);

        WebElement pswdElement = wd.findElement(By.id("password"));
        type(pswdElement, password);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        click(element);
        element.clear();
        element.sendKeys(text);
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
