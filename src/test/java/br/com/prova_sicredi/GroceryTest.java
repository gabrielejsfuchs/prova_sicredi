package br.com.prova_sicredi;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GroceryTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testProva1() {
        WebDriverWait wait = new WebDriverWait(driver, 60);

        //1.1
        driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");

        //1.2 seletor do switch de mudar versao
        By selectVersion = By.id("switch-version-select");
        wait.until(presenceOfElementLocated(selectVersion));
        wait.until(elementToBeClickable(selectVersion));
        driver.findElement(selectVersion).sendKeys("bootstrap_theme_v4");

        //1.3 esse seletor linkText é usada para elementos que sejam links como o botao, passa-se o texto do botao
        By addCostumersButton = By.linkText("Add Customer");
        wait.until(elementToBeClickable(addCostumersButton));
        driver.findElement(addCostumersButton).click();

        //1.4
        By customerNameInput = By.id("field-customerName");
        wait.until(presenceOfElementLocated(customerNameInput));
        driver.findElement(customerNameInput).sendKeys("Teste Sicredi");

        By customerLastNameInput = By.id("field-contactLastName");
        wait.until(presenceOfElementLocated(customerLastNameInput));
        driver.findElement(customerLastNameInput).sendKeys("Teste");

        By customerContactNameInput = By.id("field-contactFirstName");
        wait.until(presenceOfElementLocated(customerContactNameInput));
        driver.findElement(customerContactNameInput).sendKeys("Gabriele");

        By customerPhoneInput = By.id("field-phone");
        wait.until(presenceOfElementLocated(customerPhoneInput));
        driver.findElement(customerPhoneInput).sendKeys("51 9999-9999");

        By customerAdress1 = By.id("field-addressLine1");
        wait.until(presenceOfElementLocated(customerAdress1));
        driver.findElement(customerAdress1).sendKeys("Av Assis Brasil, 3970");

        By customerAdress2 = By.id("field-addressLine2");
        wait.until(presenceOfElementLocated(customerAdress2));
        driver.findElement(customerAdress2).sendKeys("Torre D");

        By customerCity = By.id("field-city");
        wait.until(presenceOfElementLocated(customerCity));
        driver.findElement(customerCity).sendKeys("Porto Alegre");

        By customerState = By.id("field-state");
        wait.until(presenceOfElementLocated(customerState));
        driver.findElement(customerState).sendKeys("RS");

        By customerPostalCode = By.id("field-postalCode");
        wait.until(presenceOfElementLocated(customerPostalCode));
        driver.findElement(customerPostalCode).sendKeys("91000-000");

        By customerCountry = By.id("field-country");
        wait.until(presenceOfElementLocated(customerCountry));
        driver.findElement(customerCountry).sendKeys("Brasil");


        wait.until(presenceOfElementLocated(By.id("field-salesRepEmployeeNumber")));
        WebElement opcoes = driver.findElement(By.cssSelector(".chosen-single"));
        opcoes.click();
        WebElement inputText = driver.findElement(By.cssSelector(".chosen-drop"));
        inputText.findElement(By.tagName("input")).sendKeys("Fixter");
        wait.until(presenceOfElementLocated(By.cssSelector(".active-result")));
        driver.findElement(By.tagName("li")).click();


        By customerCreditLimit = By.id("field-creditLimit");
        wait.until(presenceOfElementLocated(customerCreditLimit));
        driver.findElement(customerCreditLimit).sendKeys("200");

        //1.5
        By saveButton = By.id("form-button-save");
        wait.until(elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();

        //1.6
        By sucessMessage = By.id("report-success");
        wait.until(presenceOfElementLocated(sucessMessage));
        wait.until(textToBePresentInElementLocated(sucessMessage,
                "Your data has been successfully stored into the database."));
        assertThat(textToBePresentInElementLocated(sucessMessage, "Your data has been successfully stored into the database.").toString(),
                containsString("present in element found"));


        //2.1
        By goBackLink = By.linkText("Go back to list");
        wait.until(elementToBeClickable(goBackLink));
        driver.findElement(goBackLink).click();

        //2.2
        By searchButtonBy = By.cssSelector(".search-button");
        wait.until(elementToBeClickable(searchButtonBy));
        WebElement searchButton = driver.findElement(searchButtonBy);
        searchButton.click();

        By searchInputBy = By.cssSelector(".search-input");
        wait.until(presenceOfElementLocated(searchInputBy));
        WebElement searchInput = driver.findElement(searchInputBy);
        searchInput.sendKeys("Teste Sicredi");
        searchInput.sendKeys(Keys.RETURN);

        //2.3
        By selectAllBy = By.cssSelector(".select-all-none");
        wait.until(elementToBeClickable(selectAllBy));
        driver.findElement(selectAllBy).click();

        //2.4
        By deleteBy = By.linkText("Delete");
        wait.until(elementToBeClickable(deleteBy));
        driver.findElement(deleteBy).click();

        //2.5
        By confirmDeleteBy = By.cssSelector(".alert-delete-multiple-one");
        wait.until(presenceOfElementLocated(confirmDeleteBy));
        wait.until(textToBePresentInElementLocated(confirmDeleteBy, "Are you sure that you want to delete this 1 item?"));
        Boolean textPresent = textToBePresentInElementLocated(confirmDeleteBy, "Are you sure that you want to delete this 1 item?").apply(driver);
        assertTrue(false || textPresent);

        //2.6
        By confirmDeleteButtonBy = By.cssSelector(".delete-multiple-confirmation-button");
        wait.until(elementToBeClickable(confirmDeleteButtonBy));
        driver.findElement(confirmDeleteButtonBy).click();

        //2.7
        By alertModal = By.xpath("//p[contains(.,'data has been successfully deleted')]");
        wait.until(presenceOfElementLocated(alertModal));
        final WebElement alert = driver.findElement(alertModal);
        final String assertText = "Your data has been successfully deleted from the database.";
        wait.until(textToBePresentInElement(alert, assertText));
        String text = alert.getText();
        assertTrue(text.contains(assertText));
    }
}
