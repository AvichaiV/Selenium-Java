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
import PageObjectes.checkout;
import PageObjectes.mainPage;
import PageObjectes.products;
import Resources.base;

public class checkoutTest extends base{
	WebDriver driver;
	private static Logger log = LogManager.getLogger(cartTest.class.getName());
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized HomePage");
	}
	
	@Test()
	public void  testCheckoutProductslist() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		cart newcart = new cart(driver);
		products pr = new products(driver);
		log.info("Testing Empty Cart");
		log.info("Add products to cart");
		pr.getAddToCart("Brocolli").click();
		pr.getAddToCart("Cucumber").click();
		log.info("Open Cart");
		page.getCart().click();
		newcart.getProceedToCheckout().click();
		checkout co = new checkout(driver);
		int numOfProducts = co.getNumOfProducts();
		log.info(numOfProducts);
		Assert.assertEquals(co.getNumOfProducts(), numOfProducts);
		String[] checkTitle = {"#","Product Name","Quantiry","Price","Total"};
		Assert.assertTrue(co.checkTitle(checkTitle));
		String[][] products = {{"Brocolli - 1 Kg","1","120","120"},{"Cucumber - 1 Kg","1","48","48"}};
		Assert.assertTrue(co.checkproducts(products));
		
	}
	
	@AfterTest
	public void teardown() {
		log.info("close test");
		driver.quit();
	}

}
