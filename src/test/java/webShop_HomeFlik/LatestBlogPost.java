package webShop_HomeFlik;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LatestBlogPost {

	private static WebDriver driver;

	@Before
	public void setUpBefore() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://store.demoqa.com/");
		
		//Set browser width to 850 and height to 650
				Dimension d = new Dimension(1500,900);
				driver.manage().window().setSize(d);

	}

	@Test
	public void testi() throws InterruptedException {
		

	//Verifiera att ”Latest Blog Post”-sektionen visar produkter
		
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='footer']/section[2]/ul/li"));
		  assertEquals("Error unexpected number of items", 4, list.size());
		  
		  driver.close();
	}
	
	@Test
	public void testii() throws InterruptedException {
//Verifiera att produkttitlarna leder till produktens produktsida
	
	String prodTitle = (driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).getText()).substring(0, 7);
	System.out.println("prodTitle:" +prodTitle);
	driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).click();
	
	assertTrue("Error, clicking on the product titel does not open the product page!",(driver.findElement(By.xpath("//*[@id='single_product_page_container']/div/div[2]/h1")).getText()).contains(prodTitle));
	
	driver.close();
	}
	
	
	@Test
	public void testiii() throws InterruptedException {
		
//Verifiera att man kommer till produktens produktsida om man trycker på bilden
	
		String prodTitle = (driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).getText()).substring(0,7);
		driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[2]")).click();
		
		System.out.println("New Page: " + driver.findElement(By.xpath("//*[@id='single_product_page_container']/div/div[2]/h1")).getText());
		System.out.println("prodTitle:" +prodTitle);
		assertTrue("Error, clicking on the product image does not open the product page!",(driver.findElement(By.xpath("//*[@id='single_product_page_container']/div/div[2]/h1")).getText()).contains(prodTitle));
			
		driver.close();
	}
	
	@Test
	public void testiv() throws InterruptedException {
	
//Verifiera att ”More Details”-länken leder till produktens produktsidan
		String prodTitle = (driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).getText()).substring(0,7);
		System.out.println("prodTitle:" +prodTitle);
		driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[3]")).click();
		
		assertTrue("Error, clicking on the ”More Details” link does not open the product page!",(driver.findElement(By.xpath("//*[@id='single_product_page_container']/div/div[2]/h1")).getText()).contains(prodTitle));
			
		driver.close();
		
	}
	
	
}
