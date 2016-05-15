package webShop_HomeFlik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_Final {

	private static WebDriver driver;

	@Before
	public void setUpBefore() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://store.demoqa.com/");
		
		//Set browser width to 850 and height to 650
				Dimension d = new Dimension(1500,900);
				driver.manage().window().setSize(d);

	}
	
	@After
	public void tearDown() throws Exception {
		
		driver.close();
		
	}
	

	@Test
	public void testi() throws InterruptedException {
		
//a. Verifiera att fraktkostnaden (”Total Shipping”) fortfarande är samma som tidigare
//b. Verifiera att totalkostnaden (”Total”) är samma som tidigare
//c. Verifiera samtliga texter i förloppsindikatorn (progress bar) är svarta
		
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		 //Clicking on the Continue Button
		 driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/a/span")).click();
		 TimeUnit.MILLISECONDS.sleep(3000);
			
			 //Selecting the Shipping Option
			 WebElement dropDownList = driver.findElement(By.id("current_country"));
			 new Select(dropDownList).selectByVisibleText("India");
			 driver.findElement(By.name("wpsc_submit_zipcode")).click();
			 
			//Clicking on the checkbox "Same as billing address:"
			 driver.findElement(By.id("shippingSameBilling")).click();
			 TimeUnit.MILLISECONDS.sleep(1000);
			 
			//Enter details in the Mandatory Fields
			 //Email ID
			 driver.findElement(By.id("wpsc_checkout_form_9")).clear();
			 driver.findElement(By.id("wpsc_checkout_form_9")).sendKeys("betsy.b.samuel@gmail.com");
			 //First Name
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[2]/td[2]/input")).clear();
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[2]/td[2]/input")).sendKeys("John");
			 //Last Name
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[3]/td[2]/input")).clear();
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[3]/td[2]/input")).sendKeys("Smith");
			//Address
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[4]/td[2]/textarea")).clear();
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[4]/td[2]/textarea")).sendKeys("Bangalore");
			//City
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[5]/td[2]/input")).clear();
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[5]/td[2]/input")).sendKeys("Bangalore");
			 //Dropdown
			 WebElement dropDownList1 = driver.findElement(By.id("wpsc_checkout_form_7"));
			 new Select(dropDownList1).selectByVisibleText("India");
			//Phone Number
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[9]/td[2]/input")).clear();
			 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[9]/td[2]/input")).sendKeys("0755009967");
			
			 //Total Shipping in Cart Info Page
			 String totalShippingPrice = driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-4']/tbody/tr[2]/td[2]/span/span")).getText();
			 System.out.println("Total Shipping Price in Cart Info Page :" +totalShippingPrice);
			 
			 //Total Price in the Cart Info Page
			 String totalPrice = driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-4']/tbody/tr[5]/td[2]/span/span")).getText();
			 System.out.println("Total Price in Cart Info Page :" +totalPrice);
			 
			//Clicking on the Purchase button
			driver.findElement(By.xpath("//div[@class='wpsc_make_purchase']/div/div/span/input")).click();
			TimeUnit.MILLISECONDS.sleep(3000);
				
			//Total Shipping in Final Page
			String finalShippingPrice = (driver.findElement(By.xpath("//div[@class='wpsc-transaction-results-wrap']/p[3]")).getText()).substring(16,22);
			System.out.println("Total Shipping Price in Final Page :" +finalShippingPrice);
			
			//Total Price in Final Page
			String finalPrice = (driver.findElement(By.xpath("//div[@class='wpsc-transaction-results-wrap']/p[3]")).getText()).substring(30);
			System.out.println("Total Price in Final Page :" +finalPrice+"\n");
			
			assertEquals("Error, Total Shipping value has been changed!",totalShippingPrice,finalShippingPrice);
			assertEquals("Error, Total Price has been changed!",totalPrice,finalPrice);
			
			//Checking the colors of the Progress Bar
			 WebElement obj1 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[1]"));
			 String actualCartColor = obj1.getCssValue("color");
			 System.out.println("Cart Color : "+ actualCartColor);
			 
			 WebElement obj2 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[2]"));
			 String actualInfoColor = obj2.getCssValue("color");
			 System.out.println("Info Color : "+ actualInfoColor);
			 
			 WebElement obj3 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[3]"));
			 String actualFinalColor = obj3.getCssValue("color");
			 System.out.println("Final Color : "+ actualFinalColor);
			 
			 assertEquals("Error, The text 'Your Cart' is NOT Black in color!","rgba(0, 0, 0, 1)", actualInfoColor);
			 assertEquals("Error, The text 'Your Info' is NOT Black in color!","rgba(0, 0, 0, 1)", actualFinalColor);
			 assertEquals("Error, The text 'Final' is NOT Black in color!","rgba(0, 0, 0, 1)", actualFinalColor );
			 
			
		
	}
		
}
