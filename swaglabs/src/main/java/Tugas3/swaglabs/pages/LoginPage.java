package Tugas3.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage  {
	
	By username = By.xpath("//input[@id='user-name']"); 
	By password = By.xpath("//input[@id='password']");
	By loginButton = By.xpath("//input[@id='login-button']");
	By checkouFirstname = By.xpath("//input[@id='first-name']");
	By checkoutLastname = By.xpath("//input[@id='last-name']");
	By checkoutPostalCode = By.xpath("//input[@id='postal-code']");
	By clickContinue = By.xpath("//input[@id='continue']");
	By finishButton = By.xpath("//button[@id='finish']");
	By buttonBack = By.xpath("//button[@id='back-to-products']");
	
	public LoginPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
	}
	
	public void LoginWeb(String user, String pass) {
	setText(username, user);
	setText(password, pass);
	click(loginButton);
	}
	
	public void LoginCheckout (String firstname, String Lastname, String postalcode) {
	setName(checkouFirstname, firstname);
	SetLast(checkoutLastname, Lastname);
	setPostal (checkoutPostalCode, postalcode);
	click(clickContinue);
	
		
	}

	
	public void click() {
		click(loginButton);
	}
	
	public void click3() {
		click(finishButton);
	}
	
	public void click4() {
		click(buttonBack);
	}

}
