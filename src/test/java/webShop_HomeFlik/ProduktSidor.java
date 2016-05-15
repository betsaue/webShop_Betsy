package webShop_HomeFlik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProduktSidor {
	
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
	
//Verifiera att produktsidan innehåller Titel Produktbeskrivning och Pris
		
		driver.findElement(By.cssSelector(".slide")).click();
		
		Boolean prodTitleIsDisplayed = driver.findElement(By.className("prodtitle")).isDisplayed();
		Boolean prodDespIsDisplayed = driver.findElement(By.className("product_description")).isDisplayed();
		Boolean prodPriceIsDisplayed = driver.findElement(By.xpath("//form[@class='product_form_ajax']/div/p[2]/span")).isDisplayed();
		
		assertEquals(true,prodTitleIsDisplayed);
		assertEquals(true,prodDespIsDisplayed);
		assertEquals(true,prodPriceIsDisplayed);
		
		driver.close();
		
		
		
	}
	
	
	@Test
	public void testii() throws InterruptedException {
	
//Verifiera att det går att lägga produkten i varukorgen
		
		driver.findElement(By.cssSelector(".slide")).click();
		
		String initialCount = driver.findElement(By.xpath("//*[@id='header_cart']/a/em")).getText();
		System.out.println("Initial Count :" +initialCount);
		
		driver.findElement(By.className("wpsc_buy_button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fancy_notification_content']/a[1]")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//*[@id='fancy_notification_content']/a[1]")).click();
		
		TimeUnit.MILLISECONDS.sleep(1000);
		
		String updatedCount = driver.findElement(By.xpath("//*[@id='header_cart']/a/em")).getText();
		System.out.println("Updated Count :" +updatedCount);
		
		assertNotEquals("Error, the item was not added into the cart!",initialCount,updatedCount);
		
		driver.close();
	}
	
	@Ignore //This testcase logic works is correct, but if the same product appears again the test case fails.
	@Test
	public void testiii() throws InterruptedException {
	
//Varje produkt ska bara kunna betygsättas en gång och då ska texten ”Thanks for rating!” visas
		
		String firstRating = "Thanks for rating!";
		String secondRating = "Sorry, you already rated!";
		
		driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).click();
		TimeUnit.MILLISECONDS.sleep(1000);
		
		for(int count=1;count<3;count++)
		{
			if(count==1)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(firstRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			else if(count>1)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[4]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			
			count++;
			TimeUnit.MILLISECONDS.sleep(500);
		
		}
		
		driver.close();
			
	}
	
	@Ignore
	@Test
	public void testiv() throws InterruptedException {
		
//Verifiera att samtliga betyg (1-5) kan väljas
		
		String firstRating = "Thanks for rating!";
		String secondRating = "Sorry, you already rated!";
		
		driver.findElement(By.xpath("//*[@id='footer']/section[2]/ul/li[1]/a[1]")).click();
		TimeUnit.MILLISECONDS.sleep(1000);
		
		
		for(int count=0;count<5;count++)
		{
			if(count==0)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(firstRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			else if(count==1)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			else if(count==2)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			else if(count==3)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
			else if(count==4)
			{
				driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
				TimeUnit.MILLISECONDS.sleep(4000);
				assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
			}
		}
		driver.close();
	}
	
	@Test
	public void testv() throws InterruptedException {

//Efter betygsättningen ska det inte gå att göra en betygsättning på samma produkt igen
		
		
		driver.findElement(By.cssSelector(".slide")).click();
		TimeUnit.MILLISECONDS.sleep(1000);
		
		//Click on the star rating
		driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
		TimeUnit.MILLISECONDS.sleep(4000);
		
		WebElement obj = driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]"));
		String str1 = obj.getAttribute("class");
		System.out.println("Second time click on the star rating :" +str1);
		//wpec-star-rating rater-0 star star-rating-applied star-rating-live star-rating-on
		//wpec-star-rating rater-0 star star-rating-applied star-rating-readonly star-rating-on
		
		assertTrue("Error, review can be made on the same product again!",str1.contains("readonly"));
	
		driver.close();
	
		
	}
	
	@Ignore
	@Test
	public void testvi() throws InterruptedException {

//Öppnar man en produkt som man redan har betygsatt på nytt och försöker att betygsätta den så ska texten ”Sorry, you already rated!” visas
		
		String secondRating = "Sorry, you already rated!";
		
		driver.findElement(By.cssSelector(".slide")).click();
		TimeUnit.MILLISECONDS.sleep(4000);
		
		//Click on the star rating
		driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
		TimeUnit.MILLISECONDS.sleep(4000);
		assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
	
		driver.close();
			
	}
	
	@Test
	public void testvii() throws InterruptedException {

//Öppnar man en produkt som man redan har betygsatt på nytt och försöker att betygsätta den så ska texten ”Sorry, you already rated!” visas
		
		String secondRating = "Sorry, you already rated!";
		
		driver.findElement(By.cssSelector(".slide")).click();
		TimeUnit.MILLISECONDS.sleep(1000);
		
		//Click on the star rating
		driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]")).click();
		TimeUnit.MILLISECONDS.sleep(4000);
		
		WebElement obj = driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/span/div[3]"));
		String str1 = obj.getAttribute("class");
		System.out.println("Second time click on the star rating :" +str1);
		//wpec-star-rating rater-0 star star-rating-applied star-rating-live star-rating-on
		//wpec-star-rating rater-0 star star-rating-applied star-rating-readonly star-rating-on
		
		assertTrue("Error, review can be made on the same product again!",str1.contains("readonly"));
	
		assertEquals(secondRating,driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
		System.out.println("Message Displayed : " + driver.findElement(By.xpath("//div[(@class='productcol')]/div[2]/p")).getText());
		
		driver.close();
			
	}
	
}
