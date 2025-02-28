package stepdefinations;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.globalDriver;
import pages.sauceCommonPage;
import pages.sauceMainPage;

public class sauceDemoStepdef extends globalDriver {
	WebDriver driver = getDriver();
	WebDriverWait wait = getWait();
	sauceCommonPage common;
	sauceMainPage main;
	
	@Given("User is on Saucedemo Login Page")
	public void user_is_on_saucedemo_login_page() throws InterruptedException{
		main = new sauceMainPage(driver, wait);
		//common = new sauceCommonPage(driver, wait);
		main.openSauceDemo();
	}

	@When("User enters Username and Password")
	public void user_enters_user_and_password(DataTable dataTable) throws UnsupportedEncodingException, InterruptedException {
		main.enterUserPwd(dataTable);
	}
	
	@When("User Add Single Goods to the Cart {string}")
	public void user_add_all_goods_to_the_cart(String item) {
		main.addOneGood(item);
		main.verifyAddCart(item);
	}
	
	@When("User Remove Single Good from the Cart {string}")
	public void user_remove_good_from_the_cart(String item) {
		main.removeOneGood(item);
		//login.verifyremovedcart();
	}
	
	@When("User Click to View the Cart")
	public void user_click_to_view_the_cart() {
		main.viewCart();
	}
	
	@When("User Add the informations")
	public void user_add_the_infomations(DataTable dataTable) throws UnsupportedEncodingException, InterruptedException {
		main.enterInfo(dataTable);
	}
	
	@When("User are Checking Total Price")
	public void user_are_checking_total_price() {
		main.calculateTotalPrice();
	}
	
	@Then("User should see the catalog")
    public void user_should_see_the_catalog() {
        main.validateLogin();
    }
	
	@Then("User has ordered successful and back to home page")
	public void user_has_ordered_successful() {
		main.orderedSuccessful();
		driver.quit();
	}
}
