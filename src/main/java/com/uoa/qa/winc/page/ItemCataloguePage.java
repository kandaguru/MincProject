package com.uoa.qa.winc.page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uoa.qa.winc.base.TestBase;
import com.uoa.qa.winc.utilities.TestUtil;

public class ItemCataloguePage extends TestBase {

	public ItemCataloguePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	By viewAlternative = By.xpath("//input[@value='View alternative']");
	By alternateAdd = By.xpath("//input[@name='k2c_additeminline_submit']");

	@FindBy(id = "cart_checkout")

	WebElement checkOut;

	@FindBy(className = "cart_image")

	WebElement cartImage;

	@FindBy(name = "completeorder")

	WebElement completeOrder;

	@FindBy(id = "checkoutgate_submit")

	WebElement checkoutGate;

	@FindBy(id = "returncart_button")

	WebElement returnToCartBtn;
	
	
	By noLongerAvail=By.xpath("//button[text()='No longer available']");

	public void selectItems(double numberOfItems, String item) {

		double number = numberOfItems / 25.0;
		int numberOfPages = (int) Math.ceil(number);

		for (int j = 1; j <= numberOfPages; j++) {

			for (int i = 1; i <= numberOfItems; i++) {

				try {

					if (i > 25) {

						j += 1;
						String j1 = Integer.toString(j);

						driver.findElement(By.linkText(j1)).click();

						i = 1;
						numberOfItems = numberOfItems - 25;

					}

					By addToCartBtn = By.xpath("//input[@value='ident:" + item + "|position:" + i
							+ "|match_mode:matchall']/following-sibling::input[@value='Add']");

//					WebDriverWait wait = new WebDriverWait(driver, 1);
//					wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
					
					TestUtil.clickByJS(driver.findElement(addToCartBtn), driver);

				} catch (Exception e) {

//					if (driver.findElements(By.xpath("//button[text()='No longer available']")).size() > 0) 
					
					if(TestUtil.isElementPresent(driver, noLongerAvail))
					{
						driver.findElement(viewAlternative).click();
						driver.findElement(alternateAdd).click();

						continue;

					}

				}

			}

		}

		System.out.println(item + "price ==>" + driver.findElement(By.id("item_count")).getText());

	}

	public void CheckOut() {

		WebDriverWait wait = new WebDriverWait(driver, 3);

		Actions action = new Actions(driver);

		Action moveOverCartImage = action.moveToElement(cartImage).build();
		moveOverCartImage.perform();

		checkOut = wait.until(ExpectedConditions.visibilityOf(checkOut));

		Action clickCheckout = action.click(checkOut).build();
		clickCheckout.perform();

		String totalPriceString = driver.findElement(By.id("total_price")).getText();

		totalPriceString = totalPriceString.replaceAll("[^a-z0-9.]", "");

		double totalPrice = Double.parseDouble(totalPriceString);

		System.out.println("Total Price ==>" + totalPrice);

		if (totalPrice < 50.0) {

			completeOrder.click();
			checkoutGate.click();

		}

		else {

			completeOrder.click();

		}

	}

	public ReturnToCartPage clickReturnTocart() throws IOException {

		returnToCartBtn.click();
		return new ReturnToCartPage();

	}

}