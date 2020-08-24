package PageObjectes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class checkout {
	public WebDriver driver;
	
	private By productsLine = By.xpath("//table[@class='cartTable']/tr");
	private By productsTitle = By.xpath("//table[@class='cartTable']/tr[1]/td");
	private By promoCodeTextBox = By.cssSelector("input.promoCode");
	private By promoCodeBtn = By.cssSelector("button.promoBtn");
	private By numOfItems = By.xpath("//div[@class='products']/div");
	private By totalAmount = By.cssSelector("span.totAmt");
	private By discount = By.cssSelector("span.discountPerc");
	private By afterDiscount = By.cssSelector("span.discountAmt");
	private By placeOrderBtn = By.xpath("//button[contains(text(),'Place Order')]");
	
	

	public checkout(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean checkTitle(String[] columns) {
		List<WebElement> products = driver.findElements(productsTitle);
		int i = 0;
		for (WebElement tmp : products) {
			if (!tmp.getText().equalsIgnoreCase(columns[i])) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	public boolean checkproducts(String[][] productstext) {
		List<WebElement> products;
		int i;
		for (int j=0;j<getNumOfProducts();j++) {
			products = driver.findElements(By.xpath("//table[@class='cartTable']/tr["+(j+2)+"]/td"));
			i= 0;
			for (WebElement tmp : products.subList(1, products.size())) {
				if (!tmp.getText().equalsIgnoreCase(productstext[j][i])) {
					return false;
				}
				i++;
			}
		}
		return true;
	}
	
	public int getNumOfProducts() {
		
		List<WebElement> products = driver.findElements(productsLine);
		return products.size()-1;
	}
	
	public WebElement getPromoCodeTextBox() {
		return driver.findElement(promoCodeTextBox);
	}
	
	public WebElement getPromoCodeBtn() {
		return driver.findElement(promoCodeBtn);
	}
	
	public WebElement getTotalAmount() {
		return driver.findElement(totalAmount);
	}
	
	public String getNumOfItems() {
		String st = driver.findElement(numOfItems).getText();
		String[] result = st.split("\n");
		for (int i=0;i<result.length;i++) {
			if (result[i].contains("No. of Items")) {
				return result[i].substring(result[i].indexOf(":")+2);
			}
		}
		return null;
	}
	
	public WebElement getDiscount() {
		return driver.findElement(discount);
	}
	
	public WebElement getAfterDiscount() {
		return driver.findElement(afterDiscount);
	}
	
	public WebElement getPlaceOrderBtn() {
		return driver.findElement(placeOrderBtn);
	}
	
}
