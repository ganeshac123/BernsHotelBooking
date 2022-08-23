package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.support.PageFactory;
import pageobjects.BookingHomePage;

public class StepDefinitions {

    private BookingHomePage page = PageFactory.initElements(BookingHomePage.driver, BookingHomePage.class);

    
    @Given("^I search hotel for trip :  (.*), (.*), (.*)$")
    public void searchHotel(String hotelName, String checkinDate, String checkoutDate) throws Throwable {
        page.searchHotel(hotelName, checkinDate, checkoutDate);
    }

    @When("^I click on the book now button$")
    public void clickOnTheBookNowButton() throws Throwable {
    	page.clickBook.click();
        page.confirmBooking.click();
      
    }

    @Then("^I should be able to enter details : (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void saveBooking(String firstname, String surename, String phonenumber, String email, String address, String city, String postcode, String cardnumber, String expirydate, String cvv) throws Throwable {
        page.enterDetails(firstname, surename, phonenumber, email, address, city, postcode, cardnumber, expirydate, cvv);
    }


}