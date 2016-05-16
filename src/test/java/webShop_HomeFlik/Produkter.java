package webShop_HomeFlik;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Produkter {

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
		
//Verifiera att ”Buy Now”-knappen leder till produktens produktsida
		
		String ExpectedTitle = driver.findElement(By.xpath("//div[@class='product_description'][1]/h2")).getText();
		driver.findElement(By.className("buynow")).click();
		String ActualTitle = driver.findElement(By.className("prodtitle")).getText();
		
		assertEquals("Error, The Buy Now button does not lead to the product page!",ExpectedTitle,ActualTitle);
		driver.close();
	}
	
		
		@Test
		public void testii() throws InterruptedException {
			
//Verifiera att man kommer till produktens produktsida om man trycker på bilden
		
		String ExpectedTitle1 = driver.findElement(By.xpath("//div[@class='product_description'][1]/h2")).getText();
		driver.findElement(By.cssSelector(".slide")).click();
		String ActualTitle1 = driver.findElement(By.className("prodtitle")).getText();
		
		assertEquals("Error, Clicking on the Product Image does not lead to the product page!",ExpectedTitle1,ActualTitle1);
		driver.close();
		
		}
		
		@Test
		public void testiii() throws InterruptedException {
			
//Verifiera att ”More Info”-länken leder till produktens produktsida
		
		String ExpectedTitle2 = driver.findElement(By.xpath("//div[@class='product_description'][1]/h2")).getText();
		driver.findElement(By.linkText("More Info >")).click();
		String ActualTitle2 = driver.findElement(By.className("prodtitle")).getText();
		
		assertEquals("Error, Clicking on the 'More Info' link does not lead to the product page!",ExpectedTitle2,ActualTitle2);
		driver.close();
		
		}
		
		@Test
		public void testiv() throws InterruptedException {
		
//Verifiera att tre produkter visas i bildspelet och att det bläddras automatiskt
		
			ArrayList<String> l = new ArrayList();
			
		for(int i=0;i<5;i++){
			String prodName = driver.findElement(By.xpath("//div[@class='product_description'][1]/h2")).getText();
			System.out.println("prodName: " +prodName);
			
			if(!l.contains(prodName) && (prodName != "")){
				l.add(prodName);
			}
			
			TimeUnit.MILLISECONDS.sleep(4000);
			
		}
		
		assertEquals(3,l.size());
		driver.close();
		
}
	
		@Test
		public void testv() throws InterruptedException {
//Verifiera att det går att bläddra manuellt i bildspelet
			
			driver.findElement(By.xpath("//ul[@class='group']/a[2]")).click();
			TimeUnit.MILLISECONDS.sleep(1000);
			assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"iPhone 5");
			
			driver.findElement(By.xpath("//ul[@class='group']/a[3]")).click();
			TimeUnit.MILLISECONDS.sleep(1000);
			assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"iPod Nano Blue");
			
			driver.findElement(By.xpath("//ul[@class='group']/a[1]")).click();
			TimeUnit.MILLISECONDS.sleep(1000);
			assertEquals(driver.findElement(By.xpath("//*[@id='slides']/div[1]/div[1]/h2")).getText(),"Magic Mouse");
			
			driver.close();
			
			
		}
}
