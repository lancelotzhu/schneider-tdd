package com.schneider.tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyStockTest {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
        driver = new FirefoxDriver();

        driver.get("http://localhost:8080/");

        driver.findElement(By.name("j_username")).sendKeys("user");
        driver.findElement(By.name("j_password")).sendKeys("user");
        driver.findElement(By.name("login")).click();        
	}
	
	@After
	public void tearDown() {
        driver.quit();
	}

	@Test
	@Ignore
	public void buyWithEnoughBalance() {
        driver.findElement(By.linkText("Buy Stock")).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Buy Stock");
            }
        });        
        
        driver.findElement(By.name("stockCode")).sendKeys("601006");
        driver.findElement(By.name("price")).sendKeys("8.88");
        driver.findElement(By.name("quantity")).sendKeys("1000");
        driver.findElement(By.name("buy")).click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains("Stock has been successfully bought!");
            }
        });                
	}
	
}
