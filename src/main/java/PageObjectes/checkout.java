package PageObjectes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class checkout {
	public WebDriver driver;
	
	private By productsLine = By.xpath("//table[@class='cartTable']/tr");
	private By productsTitle = By.xpath("//table[@class='cartTable']/tr[1]/td");

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
}
