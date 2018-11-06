package com.uoa.qa.winc.page;

import java.awt.RenderingHints.Key;
import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uoa.qa.winc.base.TestBase;

public class CurrentOrderPage extends TestBase {

	public CurrentOrderPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "terms")

	WebElement searchbox;

	public ItemCataloguePage searchItem(String itemName) throws IOException {
		
		searchbox.clear();
		searchbox.sendKeys(itemName);
		searchbox.sendKeys(Keys.ENTER);
		
		return new ItemCataloguePage();
		
	}
	
	
	
	
	
	
}
