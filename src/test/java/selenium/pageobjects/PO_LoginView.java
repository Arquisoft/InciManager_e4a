package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PO_LoginView extends PO_NavView{
	
	static public void fillForm(WebDriver driver, String usernamep, String passwordp, String kindp) {
		WebElement username = driver.findElement(By.name("login"));
		username.click();
		username.clear();
		username.sendKeys(usernamep);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		new Select (driver.findElement(By.id("kind"))).selectByIndex(0);
		//Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
