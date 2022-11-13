package com.google.selenium.test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeDemo {
	ChromeDriver driver;

	@BeforeClass
	public void openUrl() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();


	}


	@Test
	public void scenario1()

	{
		driver.navigate().to("https://www.amazon.eg/");
		Actions hoover = new Actions(driver);
		WebElement menuOption = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		hoover.moveToElement(menuOption).perform();
		WebElement signin = driver.findElement(By.className("nav-action-inner"));
		signin.click();
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("ahmedmalek1719@gmail.com");
		WebElement submit = driver.findElement(By.id("continue"));
		submit.click();
		WebElement alertError = driver.findElement(By.className("a-alert-heading"));
		System.out.println(alertError.getText());
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.eg/ap/signin");


	}


	@Test
	public void scenario2()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to("https://www.amazon.eg/");
		WebElement tabs = driver.findElement(By.className("hm-icon-label"));
		tabs.click();
		WebElement deals = driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[17]/a"));
		js.executeScript("arguments[0].scrollIntoView();", deals);
		deals.click();
		WebElement thirdCateogry = driver.findElement(By.xpath("//*[@id=\"grid-main-container\"]/div[2]/span[3]/ul/li[3]/label/input"));
		js.executeScript("arguments[0].scrollIntoView();", thirdCateogry);
		thirdCateogry.click();
		WebElement firstProduct = driver.findElement(By.className("a-row a-spacing-small"));
		firstProduct.click();	
	}


	@AfterMethod

	public void takeScreenshot(ITestResult result)
	{
		if (ITestResult.FAILURE == result.getStatus())
		{
			TakesScreenshot ts= (TakesScreenshot) driver ;
			File source = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(source , new File("./screenshotFailure/" + result.getName() + ".png" )) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Test
	public void scenario3()
	{
		driver.navigate().to("https://www.amazon.eg/");
		Actions hoover = new Actions(driver);
		WebElement menuOption = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		hoover.moveToElement(menuOption).perform();
		WebElement yourOrders = driver.findElement(By.xpath("//*[@id=\"nav_prefetch_yourorders\"]/span"));
		yourOrders.click();
		assertTrue(driver.getCurrentUrl().contains("https://www.amazon.eg/ap/signin"));
		driver.navigate().back();
		Actions hoover1 = new Actions(driver);
		WebElement menuOption1 = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		hoover1.moveToElement(menuOption1).perform();
		WebElement yourAddress = driver.findElement(By.xpath("//*[@id=\"nav_prefetch_youraddresses\"]/span"));
		yourAddress.click();
		assertTrue(driver.getCurrentUrl().contains("https://www.amazon.eg/ap/signin"));
		driver.navigate().back();
		Actions hoover2 = new Actions(driver);
		WebElement menuOption2 = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		hoover2.moveToElement(menuOption2).perform();
		WebElement yourLists = driver.findElement(By.xpath("//*[@id=\"nav-al-your-account\"]/a[4]/span"));
		yourLists.click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.eg/hz/wishlist/intro");


	}






	@AfterClass
	public void closeDriver()
	{
		driver.quit();
	}



}
