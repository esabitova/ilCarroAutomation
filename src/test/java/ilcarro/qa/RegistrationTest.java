package ilcarro.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest {
    WebDriver wd;

    String firstName = "John";
    String secondName = "Doe";
    String password = "Test12345";

    Random random = new Random();
    int int_random = random.nextInt(1000000);
    String email = firstName + secondName + int_random+"@mail.com";

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.get("https://ilcarro-dev-v1.firebaseapp.com/");
    }

    @Test
    public void testRegistration() throws InterruptedException {
        //open registration form
//        wd.findElement(By.cssSelector("[href=\"/signup\"]")).click();
        wd.findElement(By.cssSelector("[href='/signup']")).click(); //difference in " and '

        //fill the registration form
//        wd.findElement(By.cssSelector("#first_name")).click(); //with cssSelector
        WebElement firstNameElement = wd.findElement(By.id("first_name"));
        firstNameElement.click();
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);

        WebElement secondNameElement = wd.findElement(By.id("second_name"));
        secondNameElement.click();
        secondNameElement.clear();
        secondNameElement.sendKeys(secondName);

        WebElement emailElement = wd.findElement(By.id("email"));
        emailElement.click();
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement pswdElement = wd.findElement(By.id("password"));
        pswdElement.click();
        pswdElement.clear();
        pswdElement.sendKeys(password);

        //select Check box
        WebElement checkBox = wd.findElement(By.id("check_policy"));
        checkBox.click();

        //Click on Yalla Button
        Thread.sleep(2000);
        WebElement submitBtn = wd.findElement(By.cssSelector("[type='submit']"));
        submitBtn.click();
    }

    @AfterMethod
    public void tearDown() {
//        wd.close(); //close only last window, but web browser close only if it was one and only window
        wd.quit(); //close windows and web browser

    }

}
