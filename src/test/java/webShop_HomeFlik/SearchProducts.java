package webShop_HomeFlik;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchProducts {

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
	public void testi() {
		
//Verifiera att det går att söka efter produkter
		
		String prodName = "mouse";
		driver.findElement(By.name("s")).clear();
	    driver.findElement(By.name("s")).sendKeys(prodName);
	    driver.findElement(By.name("s")).sendKeys(Keys.RETURN);
	    
	    WebElement obj = driver.findElement(By.xpath("//*[@id='grid_view_products_page_container']/div/div/div/a/img"));
	    
	    String searchResult = obj.getAttribute("alt").toString();
	    System.out.println("s : "+ searchResult);
	   
		 assertEquals("Error the search for product " +prodName + " failed", true, searchResult.contains("Mouse"));
		
		 driver.close();
	}
}
