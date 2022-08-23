package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import helpers.UiHelper;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class BookingHomePage extends Hooks {

    public static Logger log = Logger.getLogger(String.valueOf(BookingHomePage.class));

    @FindBy(how = How.ID, using = "firstName")
    public static WebElement first_name;
    
    @FindBy(how = How.ID, using = "lastName")
    public static WebElement sure_name;
    
    @FindBy(how = How.ID, using = "defaultPhoneNumber")
    public static WebElement phone_number;
    
    @FindBy(how = How.ID, using = "defaultEmail")
    public static WebElement email_address;
    
    @FindBy(how = How.ID, using = "billingaddress1")
    public static WebElement address_details;
    
    @FindBy(how = How.ID, using = "billingcity")
    public static WebElement enter_city;
    
    
    @FindBy(how = How.ID, using = "billingpostcode")
    public static WebElement post_code;
    
    
    @FindBy(how = How.ID, using = "payment[name_on_card]")
    public static WebElement card_name;
    
    @FindBy(how = How.ID, using = "payment[number]")
    public static WebElement card_number;
    
    
    @FindBy(how = How.ID, using = "payment[expiry_date][date]")
    public static WebElement expiry_date;
    
    @FindBy(how = How.ID, using = "payment[cvv]")
    public static WebElement enter_cvv;
   
    @FindBy(how = How.ID, using = "react-s_query")
    public static WebElement searchButton;
    
    @FindBy(how = How.LINK_TEXT, using = "Berns Hotel")
    public static WebElement chooseHotel;
    
    
    @FindBy(how = How.LINK_TEXT, using = "s_search")
    public static WebElement clickSearch;
    
    
    @FindBy(how = How.XPATH, using = "//a[@class='js-bookNowButton book-now']")
    public static WebElement clickBook;
  
    @FindBy(how = How.XPATH, using = " //a[@class='j-confirmButton button-secondary']")
    public static WebElement confirmBooking;

    
    @FindBy(how = How.LINK_TEXT, using = "confirmOrder")
    public static WebElement confirmPayment;
    
    
    @FindBy(how = How.ID, using = "depositpaid")
    public WebElement deposit;

    @FindBy(how = How.CLASS_NAME, using = "date-from")
    public static WebElement check_in;

    @FindBy(how = How.ID, using = "date-to")
    public static WebElement check_out;

    @FindBy(how = How.CSS, using = "input[onclick='createBooking()'] ")
    public static WebElement save_button;

    @FindBy(how = How.CSS, using = "input[value='Delete']")
    public static WebElement delete_button;

    @FindBy(how = How.CSS, using = "table[@class=‘calendarMonth__table’]")
    public static WebElement datepicker_table;

    @FindBy(how = How.CSS, using = "div[class='ui-datepicker-title']")
    public static WebElement datepicker_title;

    @FindBy(how = How.CSS, using = "a[data-handler='prev']")
    public static WebElement datepicker_previous;

    @FindBy(how = How.CSS, using = "a[data-handler='next']")
    public static WebElement datepicker_next;

    public BookingHomePage() throws IOException {
        super();
    }

 
    public void enterDeposit(String bookingDeposit) {
        Select dropDown = new Select(deposit);
        dropDown.selectByVisibleText(bookingDeposit);
    }

    
    public void enterDetails(String firstname, String surename, String phonenumber, String email, String address, String city, String postcode, String cardnumber, String expirydate, String cvv) {
        first_name.sendKeys(firstname);
        sure_name.sendKeys(surename);        
        phone_number.sendKeys(phonenumber);
        email_address.sendKeys(email);
        address_details.sendKeys(address);
        enter_city.sendKeys(city); 
        post_code.sendKeys(postcode);     
       
        card_name.sendKeys(firstname);
        card_number.sendKeys(cardnumber);
        expiry_date.sendKeys(expirydate);
        enter_cvv.sendKeys(cvv);
        
        confirmPayment.click();
    }
    
    public void searchHotel(String hotelName, String checkinDate, String checkoutDate) {
    	searchButton.sendKeys(hotelName);
   
    	chooseHotel.click();
    	
    	  checkInDate(checkinDate);
          checkOutDate(checkoutDate);
          
          clickSearch.click();
            
    }
    
    

    public void checkDetailsIsDisplayed(String firstname, String surename, String checkinDate, String checkoutDate) {
        clicksavedCheckInDateIsDisplayed(checkinDate, 5);
        clicksavedCheckOutDateIsDisplayed(checkoutDate, 6);
    }

  
    public void clicksavedCheckInDateIsDisplayed(String textValue, int divIndex) {
        datesDisplayed(textValue, divIndex);
        log.info("CheckIn date is displayed");
    }

  
    public void clicksavedCheckOutDateIsDisplayed(String textValue, int divIndex) {
        datesDisplayed(textValue, divIndex);
        log.info("CheckOut date is displayed");
    }

  

    public static void datesDisplayed(String textValue, int divIndex) {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div[" + divIndex + "]/p"));
        for (WebElement eachElement : elements) {
            String formatDate = eachElement.getText();
            //split year, month and days from the date using StringBuffer.
            StringBuffer sBuffer = new StringBuffer(formatDate);
            String year = sBuffer.substring(2, 4);
            String mon = sBuffer.substring(5, 7);
            String dd = sBuffer.substring(8, 10);
            if (dd.equals(textValue)) {
                log.info("Date is displayed");
            }
        }
    }

   
    public void checkInDate(String checkinDate) {
        check_in.click();
        check_in.click();
        List<WebElement> columns = datepicker_table.findElements(By.tagName("td"));

        for (WebElement cell : columns) {

            if (cell.getText().equals(checkinDate)) {
                cell.findElement(By.linkText(checkinDate)).click();
                break;
            }
        }
    }

  
    public void checkOutDate(String checkoutDate) {
        check_out.click();
        check_out.click();
        WebElement date = datepicker_table.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='" + checkoutDate + "']"));
        date.click();
    }



    public List<WebElement> retryingListElement(int divIndex) {
        List<WebElement> elements = null;
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                elements = driver.findElements(By.xpath("//div[@id='bookings']/div[@class='row']/div[" + divIndex + "]/p"));
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return elements;
    }


}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	