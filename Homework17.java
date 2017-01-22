import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinkerbellissimo on 1/22/17.
 */
public class Homework17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/Users/tinkerbellissimo/Downloads/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.className("row"));
        List<String> links = new ArrayList<>();

        for (int i=3; i<rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            links.add(cells.get(2).findElement(By.tagName("a")).getAttribute("href"));
        }
        for (String link : links) {
             checkLogs(link);
        }
    }

    public void checkLogs(String link){
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
