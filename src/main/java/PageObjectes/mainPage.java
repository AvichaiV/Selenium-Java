package PageObjectes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class mainPage {
	public WebDriver driver;
	
	private By searchbox = By.xpath("//input[@placeholder='Search for Vegetables and Fruits']");
	private By searchbtn = By.className("search-button");
	private By itemscount = By.xpath("//div[@class='cart-info']/table/tbody/tr/td[3]/strong");
	private By price = By.xpath("//div[@class='cart-info']/table/tbody/tr[2]/td[3]/strong");
	private By cartbtn = By.xpath("//a[@class='cart-icon']");
	private By noresultsheading = By.xpath("//div[@class='no-results']/h2");
	private By noresultsparagraph = By.xpath("//div[@class='no-results']/p");
	
	
	public mainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getsearchbox() {
		return driver.findElement(searchbox);
	}
	
	public WebElement getsearchbtn() {
		return driver.findElement(searchbtn);
	}
	
	public String getitemscount() {
		return driver.findElement(itemscount).getText();
	}
	
	public String getprice() {
		return driver.findElement(price).getText();
	}
	
	public WebElement getCart() {
		return driver.findElement(cartbtn);
	}
	
	public WebElement getNoResultsHeading() {
		return driver.findElement(noresultsheading);
	}
	
	public WebElement getNoResultsPparagraph() {
		return driver.findElement(noresultsparagraph);
	}
	
	

	public boolean addItem(String name,int num) {
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for ( int i=0;i < products.size();i++){
			if (name.contains(products.get(i).getText().split(" ")[0])){
				for ( int j=0;j < num;j++){
					driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				}
					return true;
			}
		}
		return false;
	}
	
	public int getNumOfProducts() {
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']"));
		return products.size();
	}
	
	
}
