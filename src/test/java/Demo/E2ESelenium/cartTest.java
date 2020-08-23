package Demo.E2ESelenium;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectes.cart;
import PageObjectes.mainPage;
import PageObjectes.products;
import Resources.base;

public class cartTest extends base{
	WebDriver driver;
	private static Logger log = LogManager.getLogger(cartTest.class.getName());
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized HomePage");
	}
	
	@Test()
	public void  testEmptyCart() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		log.info("Testing Empty Cart");
		page.getCart().click();
		cart newcart = new cart(driver);
		log.info(newcart.getEmptyCart().getText());
		Assert.assertEquals(newcart.getEmptyCart().getText(), "You cart is empty!");
		page.getCart().click();
		
		
	}
	@Test(dependsOnMethods={"testEmptyCart"})
	public void  testAddtoCart() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		cart newcart = new cart(driver);
		products pr = new products(driver);
		log.info("Testing Add To Cart");
		pr.getAddToCart("Brocolli").click();
		page.getCart().click();
		int itemLocation = newcart.findProductByNames("Brocolli");
		log.info(itemLocation);
		log.info(newcart.getProductName(itemLocation));
		Assert.assertEquals(newcart.getProductName(itemLocation), "Brocolli - 1 Kg");
		log.info(newcart.getProductQuantity(itemLocation));
		Assert.assertEquals(newcart.getProductQuantity(itemLocation), "1 No.");
		log.info(newcart.getSingelProductPrice(itemLocation));
		Assert.assertEquals(newcart.getSingelProductPrice(itemLocation), "120");
		log.info(newcart.getQuantityProductPrice(itemLocation));
		Assert.assertEquals(newcart.getQuantityProductPrice(itemLocation), "120");
		page.getCart().click();
	}
	
	@Test(dependsOnMethods={"testAddtoCart"})
	public void  testRemovefromCart() throws Exception {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		cart newcart = new cart(driver);
		products pr = new products(driver);
		log.info("Testing Remove from Cart");
		page.getCart().click();
		int itemLocation = newcart.findProductByNames("Brocolli");
		log.info(itemLocation);
		newcart.clickRemoveFromCart(itemLocation);
		log.info(newcart.getEmptyCart().getText());
		Assert.assertEquals(newcart.getEmptyCart().getText(), "You cart is empty!");
		page.getCart().click();
		Assert.assertEquals(page.getitemscount(),"0");
		pr.getAddToCart("Brocolli").click();
		pr.getAddToCart("Brocolli").click();
		page.getCart().click();
		itemLocation = newcart.findProductByNames("Brocolli");
		log.info(itemLocation);
		log.info(newcart.getProductQuantity(itemLocation));
		Assert.assertEquals(newcart.getProductQuantity(itemLocation), "2 Nos.");
		newcart.clickRemoveFromCart(itemLocation);
		log.info(newcart.getEmptyCart().getText());
		Assert.assertEquals(newcart.getEmptyCart().getText(), "You cart is empty!");
		page.getCart().click();
		Assert.assertEquals(page.getitemscount(),"0");
		
	}
	
	@Test(dependsOnMethods={"testRemovefromCart"})
	public void  testMultiProduct() throws Exception {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		cart newcart = new cart(driver);
		products pr = new products(driver);
		log.info("Testing AddToCart Cart");
		pr.getAddToCart("Brocolli").click();
		pr.getAddToCart("Brocolli").click();
		pr.getAddToCart("Cucumber").click();
		page.getCart().click();
		int itemLocation1 = newcart.findProductByNames("Brocolli");
		int itemLocation2 = newcart.findProductByNames("Cucumber");
		log.info(itemLocation2);
		log.info(newcart.getProductName(itemLocation2));
		Assert.assertEquals(newcart.getProductName(itemLocation2), "Cucumber - 1 Kg");
		log.info(newcart.getProductQuantity(itemLocation1));
		Assert.assertEquals(newcart.getProductQuantity(itemLocation1), "2 Nos.");
		log.info(newcart.getSingelProductPrice(itemLocation1));
		Assert.assertEquals(newcart.getSingelProductPrice(itemLocation1), "120");
		log.info(newcart.getQuantityProductPrice(itemLocation1));
		Assert.assertEquals(newcart.getQuantityProductPrice(itemLocation1), "240");
		log.info(newcart.getProductQuantity(itemLocation2));
		Assert.assertEquals(newcart.getProductQuantity(itemLocation2), "1 No.");
		page.getCart().click();
		pr.getAddToCart("Carrot").click();
		page.getCart().click();
		int itemLocation3 = newcart.findProductByNames("Carrot");
		newcart.clickRemoveFromCart(itemLocation3);
		Assert.assertEquals(newcart.findProductByNames("Carrot"),-1);
		page.getCart().click();
		pr.getAddToCart("Carrot").click();
		page.getCart().click();
		itemLocation3 = newcart.findProductByNames("Carrot");
		newcart.clickRemoveFromCart(itemLocation2);
		Assert.assertEquals(newcart.findProductByNames("Cucumber"),-1);
		page.getCart().click();
		
	}
	
	
	@AfterTest
	public void teardown() {
		log.info("close test");
		driver.quit();
	}
}
