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
	private By SingelProductPrice = By.cssSelector("div.cart li.cart-item p.product-price");
	private By QuantityProductPrice = By.cssSelector("div.cart li.cart-item p.amount");
	private By RemoveFromCart = By.cssSelector("div.cart li.cart-item a.product-remove");
	
	

	

	
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
		String tmp = driver.findElements(SingelProductPrice).get(location).getText();
		return tmp;
	}
	
	public String getQuantityProductPrice(int location) {
		String tmp = driver.findElements(QuantityProductPrice).get(location).getText();
		return tmp;
	}
	
	public void clickRemoveFromCart(int location) throws Exception {
		driver.findElements(RemoveFromCart).get(location).click();
		Thread.sleep(1000);


		
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
	
	
	
	
	
	/*
	public static boolean test (WebDriver driver,String[] names) {
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		List<String> namesList =  Arrays.asList(names);
		int itemNum = 0; 

		for ( int i=0;i < products.size();i++){
			if (namesList.contains(products.get(i).getText().split(" ")[0])){
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				itemNum ++;
				if (namesList.size() == itemNum){
					return true;
					}
				}
		}
		return false;
	}
	*/
	
	
}
