import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GoogleSearchTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver"); // Update path to your ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void successfulSearchWithCommonKeywords() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("functionality test");
        searchBox.submit(); // You can also use searchBox.sendKeys(Keys.ENTER);

        assertTrue(driver.getTitle().contains("functionality test"));
    }

    @Test
    public void usingSearchSuggestion() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("gherkin");
        Thread.sleep(2000); // Wait for suggestions to appear. Consider using explicit waits instead.

        // Select the first suggestion and search. This step might require adjustment based on the actual behavior of suggestions.
        searchBox.sendKeys(Keys.ARROW_DOWN);
        searchBox.sendKeys(Keys.ENTER);

        assertTrue(driver.getTitle().contains("gherkin"));
    }

    @Test
    public void failedSearchWithRandomString() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("qweqweqwe123123");
        searchBox.submit();

        // This assertion might need adjustments based on the specific output for a failed search.
        // For demonstration, we're simply checking if the no results message appears.
        boolean noResults = driver.getPageSource().contains("did not match any documents");
        assertTrue(noResults);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
