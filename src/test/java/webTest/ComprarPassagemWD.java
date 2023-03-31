// pacote
package webTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 1 - bibliotecas ( conjunto de funciones ja prontas)
// 2 - Classe
public class ComprarPassagemWD {//Inicio da classe
    // 2.1 -Atributos
    private WebDriver driver; //declaramos o  objeto do Selenium WebDriver

    // 2.2 -Funções e Métodos

    // Antes do teste
    @BeforeEach
    public void setUp(){
        //declarar o gerenciador para baixar o chrome driver
        WebDriverManager.chromedriver().setup();
        // configuração specifica a partir do selenium WebDriver 4.8.1
        ChromeOptions options = new ChromeOptions(); // Instancia (liga) o ChromeOptions
        options.addArguments("--remote-allow-origins=*");// adicionou ao ChromeOptions a opção de permitir qualquer origem de acesso remoto
        driver = new ChromeDriver(options);//  instancia o chrome driver com options
        driver.manage().window().maximize();//maximiza a janela do navegador
    }

    // Depois do teste
    @AfterEach
    public void tearDown(){
        driver.quit();// destroi a instancia do selenium WebDriver

    }

    // O Teste
    @Test
    public void comprarPassagemWD(){
        driver.get("https://www.blazedemo.com");// Abre o endereço alvo
        // selecionar a lista de cidades de origem
        driver.findElement(By.name("fromPort")).click();
        // selecionar a cidade na lista
        {//inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("fromPort"));
            lista.findElement(By.xpath("//option[ . = 'São Paolo']")).click();
        }//fin da seleção dentro da lista
        driver.findElement(By.name("toPort")).click();
        // selecionar a cidade na lista
        {//inicio da seleção dentro da lista
            WebElement lista = driver.findElement(By.name("toPort"));
            lista.findElement(By.xpath("//option[ . = 'Berlin']")).click();
        }//fin da seleção dentro da lista
        // apretar o botão Find flights
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        //validar a frasre que indica o vôo

        assertEquals("Flights from São Paolo to Berlin:",driver.findElement(By.cssSelector("h3")).getText());

    }
}// fin da classe
