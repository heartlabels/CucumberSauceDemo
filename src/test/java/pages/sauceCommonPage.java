package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class sauceCommonPage {
	WebDriver driver;
	WebDriverWait wait;
	sauceCommonPage common;
	sauceMainPage main;
	
	
	String fill_placeholder_default = "//input[@placeholder='_FIELD_NAME']";
	String select_item = "//div[text()='_OPTION']//ancestor::div/following-sibling::div/button[text()='Add to cart']";
	String remove_item = "//div[text()='_OPTION']//ancestor::div/following-sibling::div/button[text()='Remove']";
	String item_in_cart = "//div[text()='_OPTION']";
	
	
	public sauceCommonPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;

	}
	
	public void fill_placeholder_box(String field,String value) {
		String temp_xpath = fill_placeholder_default.replace("_FIELD_NAME", field);
		WebElement field_textbox = driver.findElement(By.xpath(temp_xpath));
		field_textbox.clear();
		field_textbox.sendKeys(value);
	}
	
	public void wait_until_id(String id) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	
	public void wait_until_class(String Class) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Class)));
	}
	
	public void wait_until_xpath(String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public void click_element_by_id(String id) {
		wait_until_id(id);
		WebElement field = driver.findElement(By.id(id));
		field.click();
	}
	
	public void click_element_by_xpath(String xpath) {
		wait_until_xpath(xpath);
        WebElement field = driver.findElement(By.xpath(xpath));
        field.click();
	}
	
	public void select_item_from_catalog(String item) {
		String temp_xpath=select_item.replace("_OPTION", item);
		click_element_by_xpath(temp_xpath);
		String temp_xpath2=remove_item.replace("_OPTION", item);
		wait_until_xpath(temp_xpath2);
		
	}
	
	public void remove_item_from_catalog(String item) {
		String temp_xpath=remove_item.replace("_OPTION", item);
		click_element_by_xpath(temp_xpath);
		String temp_xpath2=select_item.replace("_OPTION", item);
		wait_until_xpath(temp_xpath2);
		
	}

	public void check_item_from_cart(String item) {
		String temp_xpath=item_in_cart.replace("_OPTION", item);
		wait_until_xpath(temp_xpath);
	}
	
	public void verify_amount(String xpath, double expectedValue, String label) {
	    WebElement element = driver.findElement(By.xpath(xpath));
	    double actualValue = Double.parseDouble(element.getText().replaceAll("[^0-9.]", ""));
	    assertEquals(expectedValue, actualValue, 0.01);
	    System.out.println("Test Passed: " + label + " are equal");
	}
	
	public void verify_login() {
		WebElement catalog = driver.findElement(By.id("inventory_container"));
        assertTrue("--Login was failed!--",catalog.isDisplayed());
        
        System.out.println("--Login was successful!--");
	}

}
