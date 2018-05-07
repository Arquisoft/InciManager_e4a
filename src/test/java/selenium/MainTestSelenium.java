package selenium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import asw.Application;
import selenium.pageobjects.PO_View;

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
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}
	
	@Test
	public void PR01() {
		PO_View.checkElement(driver, "text", "Bienvenido");
	}
	
	

}
