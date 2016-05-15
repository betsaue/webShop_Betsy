package webShop_HomeFlik;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

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
		
//Verifiera att korrekt produkttitel visas i fönstret som dyker upp när man trycker på ”Add To Cart”
		
		driver.findElement(By.cssSelector(".slide")).click();
		
		String selectedProdName = driver.findElement(By.className("prodtitle")).getText();
		System.out.println("Selected Product Title"+selectedProdName);
		
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		
		String actualProdTitle = driver.findElement(By.xpath("//*[@id='fancy_notification_content']/span")).getText();
		System.out.println("Actual Product Title"+actualProdTitle);
		
		assertEquals("Error product title in the pop up is not the same" , true, actualProdTitle.contains(selectedProdName));
		
		driver.close();
		
		}
		

	@Test
	public void testii() throws InterruptedException {
		
//Verifiera att ”Go to Checkout”-knappen fungerar
		
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		TimeUnit.MILLISECONDS.sleep(2000);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		Assert.assertTrue("Error the checkout button does not work!", driver.getTitle().contains("Checkout"));
		
		driver.close();
		
	}
	
	@Test
	public void testiii() throws InterruptedException {
		
//Verifiera att ”Continue Shopping”-knappen fungerar
		
		driver.findElement(By.cssSelector(".slide")).click();
		
		String selectedProdName = driver.findElement(By.className("prodtitle")).getText();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[2]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[2]")).click();
		TimeUnit.MILLISECONDS.sleep(2000);
		
		Assert.assertTrue("Error the Continue Shopping button does not work!", driver.getTitle().contains(selectedProdName));
		
		driver.close();
	}
		
	
}
