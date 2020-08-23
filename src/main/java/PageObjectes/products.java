package PageObjectes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class products {
	public WebDriver driver;
	
	private String base = "//h4[contains(text(),'";

	public products(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean finedProduct(String pname) {
		try {
			driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']"));
			return true ;
		} catch (Exception e) {
			return false;
		}
	}
	
	public WebElement getAddToCart(String pname) {
		return driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']//div[@class='product-action']/button"));
	}
	
	public WebElement getDecrementAmount(String pname) {
		return driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']//a[@class='decrement']"));
	}
	
	public WebElement getIncrementAmount(String pname) {
		return driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']//a[@class='increment']"));
	}
	
	public WebElement getQuantity(String pname) {
		return driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']//input[@class='quantity']"));
	}
	
	public WebElement getPrice(String pname) {
		return driver.findElement(By.xpath(base+pname+"')]/ancestor::div[@class='product']//p[@class='product-price']"));
	}

}
