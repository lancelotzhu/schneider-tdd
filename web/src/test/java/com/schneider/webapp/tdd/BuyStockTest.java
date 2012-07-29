package com.schneider.webapp.tdd;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyStockTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		driver.findElement(By.name("j_username")).sendKeys("user");
		driver.findElement(By.name("j_password")).sendKeys("user");
		driver.findElement(By.name("login")).click();
		Actions builder = new Actions(driver); 
		Actions hoverOver = builder.moveToElement(driver.findElement(By.linkText("Stock")));
		hoverOver.perform();
		driver.findElement(By.linkText("Buy Stock")).click();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void refreshAndBuy() {
		driver.findElement(By.name("stockCode")).sendKeys("601857");
		driver.findElement(By.name("refreshStockInfo")).click();

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return StringUtils.isNotBlank(driver.findElement(
                		By.name("requestedPrice")).getText());
            }
        });

		driver.findElement(By.name("quantity")).sendKeys("1000");
		driver.findElement(By.name("placeOrder")).click();

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return driver.getPageSource().contains("购买成功！");
            }
        });
	}

}
