package utils;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {
  protected static WebDriver driver;
  protected static WebDriverWait wait;
  protected static Actions actions;
  
  public static void startDriver() {
    String userDir = System.getProperty("user.dir");
    System.setProperty("webdriver.chrome.driver", userDir + "\\driver\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    // options.addArguments("headless");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, 10);
    actions = new Actions(driver);
    driver.manage().window().maximize();
  }
  
  public static void clicar(String xpath) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    driver.findElement(By.xpath(xpath)).click();
  }
  
  public static void clicar(Integer posX, Integer posY) {
    actions.moveByOffset(posX, posY).click().build().perform();
  }
  
  public static void arrastar(Integer posInicialX, Integer posInicialY, Integer posFinalX, Integer posFinalY) throws Exception {
    Robot robot = new Robot();
//    Thread.sleep(5000);
    robot.mouseMove(posInicialX, posInicialY);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    Thread.sleep(500);
    robot.mouseMove(posFinalX, posFinalY);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
//    actions.moveByOffset(posInicialX, posInicialY).pause(Duration.ofSeconds(1)).click().pause(Duration.ofSeconds(1))
//        .moveByOffset(posFinalX, posFinalY).release().perform();
  }
  
  public static void preencher(String xpath, String texto) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    driver.findElement(By.xpath(xpath)).sendKeys(texto);
  }
  
  public static void selecionar(String xpath, String opcao) {
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    Select selector = new Select(driver.findElement(By.xpath(xpath)));
    selector.selectByVisibleText(opcao);
  }
  
  public static void acessar(String url) {
    driver.get(url);
  }
  
  public static void esperarPagina() {
    new WebDriverWait(driver, 30)
        .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  }
  
  public static void mudarFrame(Integer numero) {
    driver.switchTo().frame(numero);
  }
}
