package Tugas3.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {
	
	By profileText = By.xpath("//span[@class='title']"); 
	By checkoutProfile = By.xpath("//span[@class='title']");
	By thankYou = By.xpath("//h2[@class='complete-header']");
	
	

	public ProfilePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
	
	}
	
	public String getProfileText() {
		return getText(profileText);
	}
	
	public String checkoutPage () {
		return getText(checkoutProfile);
	}
	
	public String finishChecout () {
		return getText(checkoutProfile);
	}
	
	public String lastPage () {
		return getText(thankYou);
	}
}