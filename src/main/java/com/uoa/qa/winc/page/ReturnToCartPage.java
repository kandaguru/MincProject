package com.uoa.qa.winc.page;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uoa.qa.winc.base.TestBase;

public class ReturnToCartPage extends TestBase {

	public ReturnToCartPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="ui-button-text")
	WebElement finishBtn;
	
	
	@FindBy(id="quoteMailtop")
	WebElement emailTextBox;
	
	
	@FindBy(linkText="Send Email")
	WebElement SendEmailBtn;
	
	
	public void sendEmail(String email) {
		
		WebDriverWait wait= new WebDriverWait(driver,2);
		
		emailTextBox.clear();
		emailTextBox.sendKeys(email);
		SendEmailBtn.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
		
	}
	
	
	
}
