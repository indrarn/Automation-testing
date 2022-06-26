package Tugas3.swaglabs;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTestTugasTiga2 {
	
	@Test
	public void testLogin() {
		WebDriver driver;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
		//cek apakah sudah login dengan benar dengan menemukan kata products
		String findWord = driver.findElement(By.xpath("//span[@class='title']")).getText();
		String expectedWord = "PRODUCTS";
		Assert.assertTrue(findWord.contains(expectedWord));
		
		//lanjut checkout barang
		driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		//isi user untuk checkout
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("indra");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("nugraha");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("999666");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		//memastikan bahwa kolom pemesanan sudah terisi
		//String makeSure = driver.findElement(By.xpath("//h3[normalize-space()='Error: First Name is required']")).getText();
		//String badCondition = "Error: First Name is required";
		//Assert.assertTrue(makeSure.contains(badCondition));
		//driver.quit();
		
		//lanjut klik finish
		driver.findElement(By.xpath("//button[@id='finish']")).click();
		String succes = driver.findElement(By.xpath("//h2[normalize-space()='THANK YOU FOR YOUR ORDER']")).getText();
		String goodCondition = "THANK YOU FOR YOUR ORDER";
		Assert.assertTrue(succes.contains(goodCondition));
		
		driver.quit();
		
		
	}	
	
	@Test
	public void testCheckout() {
		WebDriver driver;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		
		//cek apakah sudah login dengan benar dengan menemukan kata products
		String findWord = driver.findElement(By.xpath("//span[@class='title']")).getText();
		String expectedWord = "PRODUCTS";
		Assert.assertTrue(findWord.contains(expectedWord));
		
		//lanjut checkout barang
		driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		// melanjutkan tanpa mengisi kolom area pemesanan
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		//memastikan bahwa pemesanan gagal
		String makeSure = driver.findElement(By.xpath("//h3[normalize-space()='Error: First Name is required']")).getText();
		String badCondition = "Error: First Name is required";
		Assert.assertTrue(makeSure.contains(badCondition));
		driver.quit();
			
	}

	
	

}
