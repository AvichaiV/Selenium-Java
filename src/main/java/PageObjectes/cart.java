package PageObjectes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class cart {
	public WebDriver driver;
	
	private By emptyCart = By.xpath("//div[@class='cart']//div[contains(@class,'empty-cart')]");
	private By cartProducts = By.cssSelector("div.cart li.cart-item");
	private By productName = By.cssSelector("div.cart li.cart-item p.product-name");
	private By productQuantity = By.cssSelector("div.cart li.cart-item p.quantity");
	private By singelProductPrice = By.cssSelector("div.cart li.cart-item p.product-price");
	private By quantityProductPrice = By.cssSelector("div.cart li.cart-item p.amount");
	private By removeFromCart = By.cssSelector("div.cart li.cart-item a.product-remove");
	private By proceedToCheckout = By.xpath("//div[@class='cart']//div[@class=\"action-block\"]/button");
	
	public cart(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getEmptyCart() {
		return driver.findElement(emptyCart);
	}
	
	public String getProductQuantity(int location) {
		String tmp = driver.findElements(productQuantity).get(location).getText();
		return tmp;
	}
	
	public String getProductName(int location) {
		String tmp = driver.findElements(productName).get(location).getText();
		return tmp;
	}
	
	public String getSingelProductPrice(int location) {
		String tmp = driver.findElements(singelProductPrice).get(location).getText();
		return tmp;
	}
	
	public String getQuantityProductPrice(int location) {
		String tmp = driver.findElements(quantityProductPrice).get(location).getText();
		return tmp;
	}
	
	public void clickRemoveFromCart(int location) throws Exception {
		driver.findElements(removeFromCart).get(location).click();
		Thread.sleep(1000);
	}
	
	public WebElement getProceedToCheckout() {
		return driver.findElement(proceedToCheckout);
	}
		
	public int findProductByNames (String name) {
		List<WebElement> products = driver.findElements(cartProducts);
		for ( int i=0;i < products.size();i++) {
			String tmp = driver.findElements(productName).get(i).getText();
			if (tmp.contains(name)) {
				return i;
			}
		}
		return -1;
	}
}
