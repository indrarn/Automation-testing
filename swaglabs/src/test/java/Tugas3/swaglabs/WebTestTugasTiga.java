package Tugas3.swaglabs;
import org.testng.Assert;
import org.testng.annotations.Test;
import Tugas3.swaglabs.pages.LoginPage;
import Tugas3.swaglabs.pages.ProfilePage;

import org.openqa.selenium.By;

public class WebTestTugasTiga extends BaseWebTest {
	
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage (driver, explicitWait);
	
		
	@Test
	public void testLogin() {
		
		//login dengan user dan password
		loginPage.LoginWeb("standard_user", "secret_sauce");
		String findWord = profilePage.getProfileText();
		String expectedWord = "PRODUCTS";
		Assert.assertTrue(findWord.contains(expectedWord));
		
		//lanjut checkout barang
		driver.get().findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).click();
		driver.get().findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
		driver.get().findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.get().findElement(By.xpath("//button[@id='checkout']")).click();
		
		//untuk memastikan pada laman checkout page dan mengisi halaman order
		String findWordCheckoutPage = profilePage.checkoutPage();
		String expectedWordCheckoutPage = "CHECKOUT: YOUR INFORMATION";
		Assert.assertTrue(findWordCheckoutPage.contains(expectedWordCheckoutPage));
		loginPage.LoginCheckout("Indra", "riady", "999666");
		
		//lanjut chekout overview
		String findWordComplateCheckout = profilePage.finishChecout();
		String expectedWordComplete = "CHECKOUT: OVERVIEW";
		Assert.assertTrue(findWordComplateCheckout.contains(expectedWordComplete));
		loginPage.click3();
		
		//lastpage
		String findWordLast = profilePage.lastPage();
		System.out.println(findWordLast);
		String expectedWordLast = "THANK YOU FOR YOUR ORDER";
		Assert.assertTrue(findWordLast.contains(expectedWordLast));
		loginPage.click4();
		
		
	}	
	
	@Test
	public void testCheckout() {
		
		//cek apakah sudah login dengan benar dengan menemukan kata products
		loginPage.LoginWeb("standard_user", "secret_sauce");
		String findWord = profilePage.getProfileText();
		String expectedWord = "PRODUCTS";
		Assert.assertTrue(findWord.contains(expectedWord));
		
		//lanjut checkout barang
		driver.get().findElement(By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']")).click();
		driver.get().findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
		driver.get().findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.get().findElement(By.xpath("//button[@id='checkout']")).click();
		
		// melanjutkan tanpa mengisi kolom area pemesanan
		String findWordCheckoutPage = profilePage.checkoutPage();
		String expectedWordCheckoutPage = "CHECKOUT: YOUR INFORMATION";
		Assert.assertTrue(findWordCheckoutPage.contains(expectedWordCheckoutPage));
		loginPage.LoginCheckout("", "", "");
		
		//memastikan bahwa pemesanan gagal
		String makeSure = driver.get().findElement(By.xpath("//h3[normalize-space()='Error: First Name is required']")).getText();
		String badCondition = "Error: First Name is required";
		Assert.assertTrue(makeSure.contains(badCondition));
			
	}	
}
