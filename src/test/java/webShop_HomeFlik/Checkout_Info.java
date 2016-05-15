package webShop_HomeFlik;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

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

public class Checkout_Info {

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
		
//a. ”Calculate Shipping Price”
//i. Verifiera att du får lägre fraktkostnader för ”USA” än för ”Sweden” (fraktkostnaden visas längst ned på sidan som ”Total Shipping”)
				
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
		 
		 WebElement dropDownList = driver.findElement(By.id("current_country"));
		 new Select(dropDownList).selectByVisibleText("USA");
		 driver.findElement(By.name("wpsc_submit_zipcode")).click();
	 
		 String shipUSAPrice = (driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-4']/tbody/tr[2]/td[2]/span/span")).getText()).substring(1);
		 System.out.println("Shipping to USA :" +shipUSAPrice);
	
		 Double shipUSA = Double.parseDouble(shipUSAPrice);
		
		 WebElement dropDownList1 = driver.findElement(By.id("current_country"));
		 new Select(dropDownList1).selectByVisibleText("Sweden");
		 driver.findElement(By.name("wpsc_submit_zipcode")).click();
		 
		 String shipSwedenPrice = (driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-4']/tbody/tr[2]/td[2]/span/span")).getText()).substring(1);
		 System.out.println("Shipping to Sweden :" +shipSwedenPrice);
		 
		 Double shipSweden = Double.parseDouble(shipSwedenPrice);
		 
		 assertTrue("Error, Shipping Price to USA is not less than Shipping price to Sweden!", shipUSA < shipSweden);
			
		 driver.close();
		 
	}
	
	@Test
	public void testii() throws InterruptedException {
		
//c. Verifiera att mejladressen valideras
		
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
		
		driver.findElement(By.id("wpsc_checkout_form_9")).clear();
		driver.findElement(By.id("wpsc_checkout_form_9")).sendKeys("wrong@com");
		
		//Clicking on the Purchase button
		driver.findElement(By.xpath("//div[@class='wpsc_make_purchase']/div/div/span/input")).click();
		TimeUnit.MILLISECONDS.sleep(3000);
		
		//Clicking on the Continue Button
		 driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/a/span")).click();
		 TimeUnit.MILLISECONDS.sleep(3000);
		 
		 assertTrue("Error, Email ID is not verified!",driver.findElement(By.xpath("//*[@id='wpsc_shopping_cart_container']/form/div/div/p[2]")).isDisplayed());
		 
		 driver.close();
		
	}
	
	@Test
	public void testiii() throws InterruptedException {
		
//d. ”Shipping Address”
//i. Verifiera att ”Shipping Address” inte längre visas om man kryssar i ”Same as billing address”
//ii. Verifiera att ”Shipping Address” visas igen om man tar bort krysset från ”Same as billing address” igen och att data som man tidigare har fyllt i inte har försvunnit
		
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
		 
		 //Checking the checkbox "Same as billing address:"
		 driver.findElement(By.id("shippingSameBilling")).click();
		 TimeUnit.MILLISECONDS.sleep(2000);
		 
		 WebElement obj = driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-2']/tbody/tr[3]"));
		 String str1 = obj.getAttribute("style");
		  
		 assertTrue("Error, Shipping Address is Displayed!",str1.contains("display: none"));
		 assertFalse("Error, Shipping Address is Displayed!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-2']/tbody/tr[3]/td[2]/input")).isDisplayed());
		 
		//Unchecking the checkbox "Same as billing address:"
		 driver.findElement(By.id("shippingSameBilling")).click();
		 TimeUnit.MILLISECONDS.sleep(2000);
		 
		 WebElement obj1 = driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-2']/tbody/tr[3]"));
		 String str2 = obj1.getAttribute("style");
		 
		 assertTrue("Error, Shipping Address is Not Displayed!",str2.contains("display: table-row"));
		 assertTrue("Error, Shipping Address is Not Displayed!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-2']/tbody/tr[3]/td[2]/input")).isDisplayed());
		 
		 driver.close();
		
	}
		
	@Test
	public void testiv() throws InterruptedException {
	
//e. Verifiera att texterna ”Your Cart” och ”Info” är svarta i förloppsindikatorn (progress bar) men inte ”Final”
		
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
		 
		 WebElement obj1 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[1]"));
		 String actualCartColor = obj1.getCssValue("color");
		 System.out.println("Cart Color : "+ actualCartColor);
		 
		 WebElement obj2 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[2]"));
		 String actualInfoColor = obj2.getCssValue("color");
		 System.out.println("Info Color : "+ actualInfoColor);
		 
		 WebElement obj3 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[3]"));
		 String actualFinalColor = obj3.getCssValue("color");
		 System.out.println("Final Color : "+ actualFinalColor);
		 
		 assertEquals("Error, The text 'Your Cart' and 'Your Info' is NOT in the same color!",actualCartColor, actualInfoColor);
		 assertNotEquals("Error, The text 'Your Info' and 'Final' is in the same color!",actualInfoColor, actualFinalColor);
		 assertNotEquals("Error, The text 'Final' is in the color 'black'!","rgba(0, 0, 0, 1)", actualFinalColor );
		 
		driver.close();
			
	}
	
	@Test
	public void testv() throws InterruptedException {
		
//f. Verifiera att inget ifyllt data försvinner om man väljer ”Go Back” och sedan ”Continue” igen		
		
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
		 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[4]/td[2]/textarea")).sendKeys("Frazer Town");
		//City
		 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[5]/td[2]/input")).clear();
		 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[5]/td[2]/input")).sendKeys("Bangalore");
		 //Dropdown
		 WebElement dropDownList1 = driver.findElement(By.id("wpsc_checkout_form_7"));
		 new Select(dropDownList1).selectByVisibleText("India");
		//Phone Number
		 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[9]/td[2]/input")).clear();
		 driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[9]/td[2]/input")).sendKeys("0755009967");
		
		 //Clicking on the Go Back Button
		 driver.findElement(By.xpath("//a[@class='step1']/span")).click();
		 TimeUnit.MILLISECONDS.sleep(3000);
		 
		 //Clicking on the Continue Button
		 driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/a/span")).click();
		 TimeUnit.MILLISECONDS.sleep(3000);
		 
		 assertTrue("Error, Data was not saved!",driver.findElement(By.id("wpsc_checkout_form_9")).isDisplayed());
		 assertTrue("Error, Data was not saved!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[2]/td[2]/input")).isDisplayed());
		 assertTrue("Error, Data was not saved!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[4]/td[2]/textarea")).isDisplayed());
		 assertTrue("Error, Data was not saved!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[9]/td[2]/input")).isDisplayed());
		 assertTrue("Error, Data was not saved!",driver.findElement(By.xpath("//table[@class='wpsc_checkout_table table-1']/tbody/tr[5]/td[2]/input")).isDisplayed());
		 
		 driver.close();
		 
	}
	
	
	@Test
	public void testvi() throws InterruptedException {
		
//g. Verifiera att man kan fortsätta med ”Purchase”
		
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
			
			//Clicking on the Purchase button
			driver.findElement(By.xpath("//div[@class='wpsc_make_purchase']/div/div/span/input")).click();
			TimeUnit.MILLISECONDS.sleep(3000);
				
			System.out.print("Title of the next page : " +driver.getTitle());
			assertTrue("Error, Purchase button does not work as expected!",(driver.getTitle()).contains("Transaction Results"));
			 
		  driver.close();
		
	}
		
}
