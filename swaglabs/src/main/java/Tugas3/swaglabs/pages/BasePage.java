package Tugas3.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	ThreadLocal<WebDriver> driver = new ThreadLocal <WebDriver>();
	ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal <WebDriverWait>();
	
	//penambahan constractor
	
	public BasePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	public void click(By testing) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		driver.get().findElement(testing).click();		
	}	
	
	public void setText(By testing, String text) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		driver.get().findElement(testing).sendKeys(text);
	}
	
	public void setName(By testing, String text) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		driver.get().findElement(testing).sendKeys(text);
		
	}
	
	public void SetLast(By testing, String text) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		driver.get().findElement(testing).sendKeys(text);
	}
	
	public void setPostal(By testing, String text) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		driver.get().findElement(testing).sendKeys(text);
		
	}
	
	public String getText(By testing) {
		explicitWait.get().until(ExpectedConditions.elementToBeClickable(testing));
		return driver.get().findElement(testing).getText();
	}
	

	
}

