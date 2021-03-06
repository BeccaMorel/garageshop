package ca.mcgill.ecse.carshop.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ca.mcgill.ecse.carshop.application.*;
import ca.mcgill.ecse.carshop.model.*;

public class CucumberStepDefinitions {
	
	static CarShop carShop;
	static String error;
	
	@After
	public void teardown() {
	    // Write code here that turns the phrase above into concrete actions
	    carShop.delete();
	    error = "";
	}
	
	@Given("a Carshop system exists")
	public void a_carshop_system_exists() {
	    // Write code here that turns the phrase above into concrete actions
	    carShop = CarShopApplication.getCarshop();
	}
	
	/****LOGIN****/
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    List<String> custNamePws = dataTable.asList();
	    List<Customer> custs = carShop.getCustomers();
	    
	    boolean custExists = false;
	    for(int i = 2; i < custNamePws.size(); i+=2)
	    {
		    for(Customer cust : custs)
		    {
		    	if(cust.getUsername() == custNamePws.get(i))
		    	{
		    		if(cust.getPassword() == custNamePws.get(i+1))
		    		{
		    			custExists = true;
		    		}
		    	}
		    }
		    if(!custExists)
		    {
		    	carShop.addCustomer(custNamePws.get(i), custNamePws.get(i+1));
		    }
		    custExists = false;
	    }
	}
	
	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		error = CarShopApplication.login(string, string2);
	}
	
	/***SUCCESSFUL***/
	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(CarShopApplication.isLoggedIn());
	}
	
	/***UNSUCCESSFUL***/
	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(CarShopApplication.isLoggedIn());
	}
	
	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(error, string);
	}
	
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the following technicians exist in the system:")
	public void the_following_technicians_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("each technician has their own garage")
	public void each_technician_has_their_own_garage() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the Owner with username {string} is logged in")
	public void the_owner_with_username_is_logged_in(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("{string} initiates the addition of the service {string} with duration {string} belonging to the garage of {string} technician")
	public void initiates_the_addition_of_the_service_with_duration_belonging_to_the_garage_of_technician(String string, String string2, String string3, String string4) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the service {string} shall belong to the garage of {string} technician")
	public void the_service_shall_belong_to_the_garage_of_technician(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}