package com.uoa.qa.winc.utilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uoa.qa.winc.base.TestBase;

public class TestUtil extends TestBase {

	public static String parentWindow, childWindow;

	public TestUtil() throws IOException {
		super();
	}

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 5;

	public static int giveFrameCount(By byloc) {

		List<WebElement> frames = driver.findElements(By.tagName("frame"));

		int a = frames.size();

		System.out.println("size:" + a);

		int frameNum = -1;

		for (int i = 0; i < a; i++) {

			driver.switchTo().frame(i);

			List<WebElement> txt = driver.findElements(byloc);

			System.out.println("txt" + txt.size());

			if (txt.size() > 0) {

				frameNum = i;
				System.out.println(frameNum);
				break;
			}

			driver.switchTo().defaultContent();

		}

		return frameNum;

	}

	public static void switchToChildWindow() {

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();

		parentWindow = it.next();
		childWindow = it.next();

		driver.switchTo().window(childWindow);
		System.out.println("Current window title ====> " + driver.getTitle());

	}

	public static void switchToParentWindow() {

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();

		driver.switchTo().window(it.next());

		System.out.println("Current Window Title====> " + driver.getTitle());

	}

	public static String getTwoFA() {

		String otpKeyStr = "UKMKVCOZOELMD5XZO3V72ZHW2CHPECDU";

		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now(); // <- got 2FA coed at this time!

		System.out.println(twoFactorCode);

		return twoFactorCode.trim();

	}

	public static boolean isElementPresent(WebDriver driver, By by) {

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		}

	}

	
	public static void clickByJS(WebElement element,WebDriver driver) {
		
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();",element );	
	}
	
	
}