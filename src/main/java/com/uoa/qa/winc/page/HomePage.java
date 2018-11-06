package com.uoa.qa.winc.page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uoa.qa.winc.base.TestBase;

public class HomePage extends TestBase {
	
	public HomePage() throws IOException {
		super();
	}



	By wincLink = By.linkText("Winc (formerly Staples)");
	
	
	
	
	public CurrentOrderPage clickMincLink() throws IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, 8);
		wait.until(ExpectedConditions.elementToBeClickable(wincLink)).click();
		
		//driver.findElement(wincLink).click();
		
		return new CurrentOrderPage();
		
		
	}
	
	
}
