package Demo.E2ESelenium;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.inject.Key;

import PageObjectes.mainPage;
import PageObjectes.products;
import Resources.base;

public class basePage extends base{
	WebDriver driver;
	private static Logger log = LogManager.getLogger(basePage.class.getName());
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized HomePage");
	}
	
	@Test()
	public void  testEmptySerch() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		log.info("Testing Serch Product that not exists");
		page.getsearchbox().sendKeys("@@");
		log.info(page.getNoResultsHeading().getText());
		Assert.assertEquals(page.getNoResultsHeading().getText(), "Sorry, no products matched your search!");
		log.info(page.getNoResultsPparagraph().getText());
		Assert.assertEquals(page.getNoResultsPparagraph().getText(), "Enter a different keyword and try.");
		page.getsearchbox().clear();
		page.getsearchbox().sendKeys(" ");

	}
	
	@Test()
	public void  testSerchProduct() throws Exception {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		log.info("Testing Serch Product");
		page.getsearchbox().sendKeys("Cucumber");
		products pr = new products(driver);
		log.info(pr.finedProduct("Cucumber"));
		Assert.assertFalse(pr.finedProduct("Beetroot"));
		Assert.assertTrue(pr.finedProduct("Cucumber"));
		page.getsearchbox().clear();
		page.getsearchbox().sendKeys(" ");
		Assert.assertTrue(pr.finedProduct("Beetroot"));
		page.getsearchbox().clear();
		page.getsearchbox().sendKeys("ca");
		log.info(page.getNumOfProducts());
		Assert.assertEquals(page.getNumOfProducts(),4);
		page.getsearchbox().sendKeys(Keys.BACK_SPACE);
		log.info(page.getNumOfProducts());
		Assert.assertEquals(page.getNumOfProducts(),7);
		page.getsearchbox().clear();
		page.getsearchbox().sendKeys(" ");
	}
	
	@Test(dependsOnMethods={"testSerchProduct"})
	public void  testProductQuantity() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		products pr = new products(driver);
		log.info("Testing Product");
		Assert.assertTrue(pr.finedProduct("Cauliflower"));
		log.info(pr.getQuantity("Cauliflower").getAttribute("value"));
		Assert.assertEquals(pr.getQuantity("Cauliflower").getAttribute("value"), "1");
		pr.getDecrementAmount("Cauliflower").click();
		log.info(pr.getQuantity("Cauliflower").getAttribute("value"));
		Assert.assertEquals(pr.getQuantity("Cauliflower").getAttribute("value"), "1");
		pr.getIncrementAmount("Cauliflower").click();
		log.info(pr.getQuantity("Cauliflower").getAttribute("value"));
		Assert.assertEquals(pr.getQuantity("Cauliflower").getAttribute("value"), "2");
		pr.getDecrementAmount("Cauliflower").click();
		log.info(pr.getQuantity("Cauliflower").getAttribute("value"));
		Assert.assertEquals(pr.getQuantity("Cauliflower").getAttribute("value"), "1");
	}
	
	@Test(dependsOnMethods={"testProductQuantity"})
	public void  testProductAddToCart() {
		driver.get(prop.getProperty("url"));
		log.info("navigate to home page");
		mainPage page = new mainPage(driver);
		products pr = new products(driver);
		log.info("Testing Product Add To Cart");
		pr.getAddToCart("Beetroot").click();
		log.info(page.getitemscount());
		Assert.assertEquals(page.getitemscount(),"1");
		log.info(page.getprice());
		Assert.assertEquals(page.getprice(),"32");
		pr.getAddToCart("Beetroot").click();
		log.info(page.getitemscount());
		Assert.assertEquals(page.getitemscount(),"1");
		log.info(page.getprice());
		Assert.assertEquals(page.getprice(),"64");
		pr.getAddToCart("Brinjal").click();
		log.info(page.getitemscount());
		Assert.assertEquals(page.getitemscount(),"2");
		log.info(page.getprice());
		Assert.assertEquals(page.getprice(),"99");
	}
	
	
	@AfterTest
	public void teardown() {
		log.info("close test");
		driver.quit();
	}
	
}