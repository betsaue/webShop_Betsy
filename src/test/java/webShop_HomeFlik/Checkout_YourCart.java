package webShop_HomeFlik;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_YourCart {

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
		
//Tom varukorg 
//i. Verifiera att texten ”Oops, there is nothing in your cart.” visas

		
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		while(!driver.findElements(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).isEmpty()){
		
			driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).click();
		}
		
		assertEquals("Oops, there is nothing in your cart.", driver.findElement(By.className("entry-content")).getText());
		
		/*Boolean continueVisibility = true;
		
		if(driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/a/span"))==null){
			continueVisibility = false;
		}
		assertEquals(false,continueVisibility); */
		
		driver.close();
	}
	
	
	@Test
	public void testii() throws InterruptedException {
		
//c. Verifiera att det går att ta bort produkter från varukorgen med ”Remove”- knappen
		
		driver.findElement(By.cssSelector(".slide")).click();
		
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		TimeUnit.MILLISECONDS.sleep(3000);
		
		String initialCount = driver.findElement(By.className("count")).getText();
		System.out.println(initialCount);
		
		driver.findElement(By.cssSelector("form.adjustform.remove > input[name=\"submit\"]")).click();
		TimeUnit.MILLISECONDS.sleep(1000);
			
		String finalCount = driver.findElement(By.className("count")).getText();
		System.out.println(finalCount);
		
		assertNotEquals("Error, the remove button does not work as expected!", initialCount, finalCount);
		
		driver.close();
		
	}
		
	@Test
	public void testiii() throws InterruptedException {
		
//d. ”Quantity”
//i. Verifiera att det går att öka antalet produkter på ”Checkout”-sidan och att totalsumman uppdateras korrekt
		
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		String selectedProdName = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[2]")).getText();
		System.out.println("Product Name : "+ selectedProdName);
		
		WebElement obj = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[3]/form/input"));
	    String initialQuantity = obj.getAttribute("value").toString();
	    System.out.println("Initial Quantity : "+ initialQuantity);
	    
	    String initialPrice = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[5]/span/span")).getText();
	    System.out.println("Initial Price : "+ initialPrice);
	    
	    //Updating the quantity
	    driver.findElement(By.name("quantity")).clear();
	    driver.findElement(By.name("quantity")).sendKeys("5");
	    
	    //Clicking on the update button
	    driver.findElement(By.name("submit")).click();
	    
	    WebElement obj1 = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[3]/form/input"));
	    String updatedQuantity = obj1.getAttribute("value").toString();
	    System.out.println("Updated Quantity : "+ updatedQuantity);
	    
	    String updatedPrice = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[5]/span/span")).getText();
	    System.out.println("Updated Price : "+ updatedPrice);
	    
	    int newQuantity = Integer.parseInt(updatedQuantity);
	    String pricePerPiece = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[4]/span")).getText();
	    
	    double prisPerPiece = Double.parseDouble(pricePerPiece.substring(1));
	    System.out.println("Price Per Piece : "+ prisPerPiece);
	    double newPrice = newQuantity*prisPerPiece;
	    //String price = String.valueOf(newPrice);
	    String expectedPrice = "$" + String.valueOf(newPrice);
	    System.out.println("Expected Price : "+ expectedPrice);
	    
	    assertNotEquals("Error, Product quantity was not changed!",initialQuantity , updatedQuantity);
	    
	    assertTrue("Error, the total price is not correct", updatedPrice.contains(expectedPrice));
	    
	    driver.close();
	    	
	}
	
	
	@Test
	public void testiv() throws InterruptedException {
		
//d. ”Quantity”
//ii. Verifiera att produkten försvinner från varukorgen om man sätter antalet till noll
			
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		String selectedProdName = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[2]")).getText();
		System.out.println("Product Name : "+ selectedProdName);
		
		WebElement obj = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[3]/form/input"));
	    String initialQuantity = obj.getAttribute("value").toString();
	    System.out.println("Initial Quantity : "+ initialQuantity);
	    
	    //Updating the quantity
	    driver.findElement(By.name("quantity")).clear();
	    driver.findElement(By.name("quantity")).sendKeys("0");
	    
	    //Clicking on the update button
	    driver.findElement(By.name("submit")).click();
	    
	    Boolean isPresent = driver.findElements(By.cssSelector("form.adjustform.qty > input[value=\"0\"]")).size() > 0;
	    
	    assertEquals("Error, The product has not been removed!", false, isPresent);
		
	    driver.close();
		
	}
		

	@Test
	public void testv() throws InterruptedException {

//e. Verifiera att totalsumman ”Sub-Total” beräknas korrekt
		
		driver.findElement(By.cssSelector(".slide")).click();
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		String selectedProdName = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[2]")).getText();
		System.out.println("First Product Name : "+ selectedProdName);
		
		WebElement obj = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[3]/form/input"));
	    String initialQuantity = obj.getAttribute("value").toString();
	    System.out.println("Initial Quantity : "+ initialQuantity);
	    
	    //Updating the quantity
	    driver.findElement(By.name("quantity")).clear();
	    driver.findElement(By.name("quantity")).sendKeys("5");
	    
	    //Clicking on the update button
	    driver.findElement(By.name("submit")).click();
	    
	    WebElement obj1 = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[3]/form/input"));
	    String updatedQuantity = obj1.getAttribute("value").toString();
	    System.out.println("Updated Quantity : "+ updatedQuantity);
	    
	    String updatedPrice = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[5]/span/span")).getText();
	    System.out.println("Updated Price : "+ updatedPrice);
	    
	    //Adding another product to cart
	    //String secondProdName = driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).getText();
	    //System.out.println("First Product Name : "+ secondProdName);
		driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[3]")).click();
		
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait1 = new WebDriverWait(driver,10);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait1.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(3000);
		
		String secondProdName = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[2]")).getText();
		System.out.println("Second Product Name : "+ secondProdName);
		
		 String priceSecondProduct = driver.findElement(By.xpath("//table[@class='checkout_cart']/tbody/tr[last()]/td[5]/span/span")).getText();
		 System.out.println("Price of the Second Product : "+ priceSecondProduct);
		 
		 double pris = (Double.parseDouble(updatedPrice.substring(1)))+(Double.parseDouble(priceSecondProduct.substring(1)));
		 DecimalFormat formatter = new DecimalFormat("#,###.00");
		 System.out.println("Formatted Price: "+formatter.format(pris));
		 String expectedSubTotal = "$" + String.valueOf(formatter.format(pris));
		 System.out.println("Expected Sub-Total : "+ expectedSubTotal);
		 
		 String actualSubTotal = driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/span/span")).getText();
		 System.out.println("Actual Sub-Total : "+ actualSubTotal);
		 
		 assertTrue("Error, Sub-Total is not calculated correctly!", actualSubTotal.contains(expectedSubTotal));
		 
		 driver.close();
		
		
	}
	
	
	@Test
	public void testvi() throws InterruptedException {

//f.Verifiera att endast texten ”Your Cart” i förloppsindikatorn (progress bar) är svart
		
//g. Verifiera att det går att fortsätta med ”Continue”-knappen
		
	driver.findElement(By.cssSelector(".slide")).click();
	driver.findElement(By.className("wpsc_buy_button")).click();
	WebDriverWait wait = new WebDriverWait(driver,10);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
	wait.pollingEvery(100, TimeUnit.MILLISECONDS);
	driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
	TimeUnit.MILLISECONDS.sleep(3000);
	 
	 WebElement obj2 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[1]"));
	 String actualCartColor = obj2.getCssValue("color");
	 System.out.println("Cart Color : "+ actualCartColor);
	 
	 WebElement obj3 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[2]"));
	 String actualInfoColor = obj3.getCssValue("color");
	 System.out.println("Info Color : "+ actualInfoColor);
	 
	 WebElement obj4 = driver.findElement(By.xpath("//div[@class='progress_wrapper top']/ul/li[3]"));
	 String actualFinalColor = obj4.getCssValue("color");
	 System.out.println("Final Color : "+ actualFinalColor);
	 
	 assertEquals("Error, The text 'Your Cart' is not in the color 'black'!","rgba(0, 0, 0, 1)", actualCartColor );
	 
	 assertNotEquals("Error, The text 'Your Cart' and 'Your Info' is in the same color!",actualCartColor, actualInfoColor);
	 assertNotEquals("Error, The text 'Your Cart' and 'Final' is in the same color!",actualCartColor, actualFinalColor);
	 
	 //Clicking on the Continue Button
	 driver.findElement(By.xpath("//*[@id='checkout_page_container']/div/a/span")).click();
	 TimeUnit.MILLISECONDS.sleep(3000);
	 System.out.println("Text in the Next Page : " + driver.findElement(By.xpath("//*[@id='checkout_page_container']/div[2]/div/h2")).getText());
	 
	 assertTrue("Error, the continue button does not take you further!", driver.findElement(By.xpath("//*[@id='checkout_page_container']/div[2]/div/h2")).isDisplayed()); 
			 
	 driver.close();
	 
	}
		 
		 
	
}
