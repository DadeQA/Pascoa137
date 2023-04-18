package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveclassLoginWD {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void paginaDeLoginTeste(){
        driver.get("https://testando.eveclass.com/pt/auth/entrar");
        driver.findElement(By.cssSelector("input[type=email]")).sendKeys("dedphm@gmail.com");
        driver.findElement(By.cssSelector("input[type=password]")).sendKeys("Daniel123");
        driver.findElement(By.cssSelector("button[type=submit]")).click();








    }
}
