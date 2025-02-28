package pages;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import pages.globalDriver;
import pages.sauceCommonPage;
import pages.sauceMainPage;



public class sauceMainPage {
	WebDriver driver;
	WebDriverWait wait;
	sauceCommonPage common;
	sauceMainPage main;
	
	String txt_email = "userInput";
	String txt_password = "password";
	String cart_button = "//a[@class='shopping_cart_link']";
	String continue_shopping_button = "//button[@class='btn btn_secondary back btn_medium']";
	String checkout_button = "//button[@class='btn btn_action btn_medium checkout_button ']";
	String item_lists = "//div[@class='cart_item']//div[@class='inventory_item_price']";
	String summary_subtotal = "//div[@class='summary_subtotal_label']";
	String summary_tax = "//div[@class='summary_tax_label']";
	String summary_total = "//div[@class='summary_total_label']";
	
	
	public sauceMainPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;

	}
	
	public void openSauceDemo() throws InterruptedException {
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

	}
	
	public void enterUserPwd(DataTable dataTable) throws InterruptedException, UnsupportedEncodingException {
		Map<String,String> data = dataTable.asMap(String.class,String.class);
		common = new sauceCommonPage(driver, wait);

		common.fill_placeholder_box("Username", data.get("Username"));
		common.fill_placeholder_box("Password", data.get("Password"));
		common.click_element_by_id("login-button");
		
		
	}
	
	public void validateLogin() {
		common.verify_login();
		//common.wait_until_id("inventory_container");
	
	}
	
	
	public void addOneGood(String item) {
		common.select_item_from_catalog(item);
	
	}
	
	public void removeOneGood(String item) {
		common.remove_item_from_catalog(item);
	
	}
	
	public void viewCart() {
		common.click_element_by_xpath(cart_button);
		common.click_element_by_xpath(checkout_button);
	
	}
	
	public void verifyAddCart(String item) {
		common.click_element_by_xpath(cart_button);
		common.wait_until_class("cart_item");
		common.check_item_from_cart(item);
		common.click_element_by_xpath(continue_shopping_button);
	
	}
	
	public void enterInfo(DataTable dataTable) throws InterruptedException, UnsupportedEncodingException {
		Map<String,String> data = dataTable.asMap(String.class,String.class);
		//common = new sauceCommonPage(driver, wait);

		common.fill_placeholder_box("First Name", data.get("First Name"));
		common.fill_placeholder_box("Last Name", data.get("Last Name"));
		common.fill_placeholder_box("Zip/Postal Code", data.get("Zip/Postal Code"));
		common.click_element_by_id("continue");
		
	}
	
	
	public void calculateTotalPrice() {
		List<WebElement> elements = driver.findElements(By.xpath(item_lists));
		double sum = 0;
			
		int count = elements.size();
		System.out.println("Total records found: " + count);
		
		for (int i = 1; i <= count; i++) {
			 String dynamicXpath = "//div[@class='cart_item'][" + i + "]//div[@class='inventory_item_price']";
			 WebElement element = driver.findElement(By.xpath(dynamicXpath));
			 String text = element.getText().replaceAll("[^0-9.]", ""); 
			 System.out.println("Item" + i + ": "+" $ "+ text);
			 if (!text.isEmpty()) {
			        sum += Double.parseDouble(text);
			    }
		}
		System.out.println("Item Total: " + "$" + sum);
		double tax = sum * 0.08;
		double roundedTax = Math.round(tax * 100.0) / 100.0;
		System.out.println("Calculated Tax (8%): "+ "$" + roundedTax);
		double totalprice = sum + roundedTax;
		System.out.println("Total: " + "$" + totalprice);
		
		/*WebElement sumsubtotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
		double actualsubtotal = Double.parseDouble(sumsubtotal.getText().replaceAll("[^0-9.]", ""));
		assertEquals(sum, actualsubtotal, 0.01);
		System.out.println("Test Passed: Subtotal are equal");
		
		WebElement sumtax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
		double actualtax = Double.parseDouble(sumtax.getText().replaceAll("[^0-9.]", ""));
		assertEquals(roundedTax, actualtax, 0.01);
		System.out.println("Test Passed: Tax are equal");
		
		WebElement sumtotal = driver.findElement(By.xpath("//div[@class='summary_total_label']"));
		double actualtotal = Double.parseDouble(sumtotal.getText().replaceAll("[^0-9.]", ""));
		assertEquals(totalprice, actualtotal, 0.01);
		System.out.println("Test Passed: Total Price are equal");
		*/
		
		common.verify_amount(summary_subtotal, sum, "Subtotal");
		common.verify_amount(summary_tax, roundedTax, "Tax");
		common.verify_amount(summary_total, totalprice, "Total Price");
		
		common.click_element_by_id("finish");
			
	}
	
	public void orderedSuccessful() {
		System.out.println("---Order was successful!!!---");
		common.wait_until_id("checkout_complete_container");
		common.click_element_by_id("back-to-products");
		
	}
	
}
