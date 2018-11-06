package com.uoa.qa.minc.testcase;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;

import com.uoa.qa.winc.base.TestBase;
import com.uoa.qa.winc.page.CurrentOrderPage;
import com.uoa.qa.winc.page.HomePage;
import com.uoa.qa.winc.page.ItemCataloguePage;
import com.uoa.qa.winc.page.LoginPage;

import com.uoa.qa.winc.page.ReturnToCartPage;
import com.uoa.qa.winc.utilities.TestUtil;

public class MincAddToCartTest extends TestBase {

	public MincAddToCartTest() throws IOException {
		super();
	}

	LoginPage loginPage;
	HomePage homePage;
	CurrentOrderPage currentOrderPage;
	ItemCataloguePage itemCataloguePage;
	ReturnToCartPage returnToCartPage;

	@BeforeClass
	public void setUp() throws IOException {
		intialize(); // driver instance initialisation

		loginPage = new LoginPage();

		homePage = loginPage.login();
		
		homePage = loginPage.enter2FA();

		while (loginPage.returnErrorMsg()) {

			homePage = loginPage.enter2FA();

		}
		

	}

	@Test(priority = 1)
	public void switch_To_window_test() throws IOException, InterruptedException {

		currentOrderPage = homePage.clickMincLink(); // clicking on the Winc. (formerly staples ) Link

		Thread.sleep(1000L); // static wait

		TestUtil.switchToChildWindow(); // switching to child pop-up tab/window

	}

	@Test(priority = 2, dataProvider = "getdata")
	public void add_To_Cart_test(String itemName, String numberOfItemsString) throws IOException {

		try {

			itemName = itemName.trim();
			numberOfItemsString = numberOfItemsString.trim();

			double numberOfItems = Double.parseDouble(numberOfItemsString); // retrieve the number of items as string
																			// and
																			// convert it into double

			itemCataloguePage = currentOrderPage.searchItem(itemName); // enter choice of item

			itemCataloguePage.selectItems(numberOfItems, itemName); // method to select the desired items and pushing
																	// the
																	// same into the cart
		} catch (Exception e) {

			System.out.println("Handled null elements from the data provider to the testcase");
		}

	}

	@Test(priority = 3)
	public void complete_Order_test() throws InterruptedException, IOException {

		itemCataloguePage.CheckOut(); // checkout process method branching based on the total price
		returnToCartPage = itemCataloguePage.clickReturnTocart();
	}

	@Test(priority = 4)
	public void send_Email_test() throws IOException, InterruptedException {

		TestUtil.switchToParentWindow();
		returnToCartPage.sendEmail(prop.getProperty("email"));

	}

	@DataProvider
	public Object[][] getdata() {

		// int
		// numberOfCombinations=Integer.parseInt(prop.getProperty("numberofcombinations"));

		Object data[][] = new Object[5][2];

		data[0][0] = prop.getProperty("itemname1");
		data[0][1] = prop.getProperty("numberofitems1");

		data[1][0] = prop.getProperty("itemname2");
		data[1][1] = prop.getProperty("numberofitems2");

		data[2][0] = prop.getProperty("itemname3");
		data[2][1] = prop.getProperty("numberofitems3");

		data[3][0] = prop.getProperty("itemname4");
		data[3][1] = prop.getProperty("numberofitems4");

		data[4][0] = prop.getProperty("itemname5");
		data[4][1] = prop.getProperty("numberofitems5");

		return data;

	}

	@AfterClass
	public void tearDown() {
		
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;
	}

}
