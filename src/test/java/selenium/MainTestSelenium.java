package selenium;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import asw.Application;
import selenium.pageobjects.PO_LoginView;
import selenium.pageobjects.PO_View;
import selenium.util.SeleniumUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
// @WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTestSelenium {

	private static WebDriver driver;
	static String URL;

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		URL = "http://localhost:8880";
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() {
		//driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@Test
	public void PR01IndexView() {
		PO_View.checkElement(driver, "text", "Login");
	}

	@Test
	public void PR02ChatbotNotLogged() {
		driver.navigate().to(URL+"/chatbot");
		PO_View.checkElement(driver, "text", "Login");
	}

	@Test
	public void PR03SeguimientoIncidenciasNotLogged() {
		driver.navigate().to(URL+"/seguimientoIncidencias");
		PO_View.checkElement(driver, "text", "Login");
	}

	@Test
	public void PR04Login() {
		driver.navigate().to(URL+"/login");
		PO_View.checkElement(driver, "text", "Login");
		PO_LoginView.fillForm(driver, "13864928P", "123456","Kind");
		PO_View.checkElement(driver, "text", "Bienvenido");
	}

	@Test
	public void PR05Chatbot() {
		driver.navigate().to(URL+"/login");
		PO_View.checkElement(driver, "text", "Login");
		PO_LoginView.fillForm(driver, "13864928P", "123456","Kind");
		PO_View.checkElement(driver, "text", "Bienvenido");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'chatbot')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "Chatbot");
	}

	@Test
	public void PR06SeguimientoIncidencias() {
		driver.navigate().to(URL+"/login");
		PO_View.checkElement(driver, "text", "Login");
		PO_LoginView.fillForm(driver, "13864928P", "123456","Kind");
		PO_View.checkElement(driver, "text", "Bienvenido");
		List<WebElement> elementos2 = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'seguimiento')]/a", PO_View.getTimeout());
		elementos2.get(0).click();
		PO_View.checkElement(driver, "text", "Seguimiento");
	}

}
