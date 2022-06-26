package Tugas4.yopmail2022;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTestYopmail {
	
	@Test
	public void login () {
		WebDriver driver;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://yopmail.com/en/");
		
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("automationtest");
		driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();
		
		
		//switch ke frame pertama
		driver.switchTo().frame("ifinbox");
		driver.findElement(By.xpath("//span[normalize-space()='Rajaraman']")).click();
	
		
		driver.switchTo().parentFrame();
		driver.switchTo().frame("ifmail");
		String purpose = driver.findElement(By.xpath("//h1[normalize-space()='Your subscription has ended']")).getText();
		System.out.println(purpose);
		
		driver.quit();
	}
	

}
