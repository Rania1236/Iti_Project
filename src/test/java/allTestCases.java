import com.github.javafaker.Faker;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.Keys;


import javax.swing.plaf.FileChooserUI;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class allTestCases {
    WebDriver driver;
    SoftAssert soft = new SoftAssert();
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String email = faker.internet().emailAddress();
    String Email = faker.internet().emailAddress();
    String passWord = faker.internet().password();

    @BeforeMethod
    public void openBrowser(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://automationexercise.com");
    }


     @Test(priority = 1)
    public void registerUser() throws InterruptedException {

         //Verify that home page is visible successfully
         Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
         //Click on 'Signup / Login' button
         driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
         Thread.sleep(3000);
         //Verify 'New User Signup!' is visible
         String actualResult1 = driver.findElement(By.cssSelector("div[class=\"signup-form\"] h2")).getText();
         Assert.assertEquals(actualResult1,"New User Signup!","Title is wrong");
         Thread.sleep(3000);
         //Enter name and email address
         driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(firstName);
         driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(email);
         //Click 'Signup' button
         driver.findElement(By.cssSelector("button[data-qa=\"signup-button\"]")).click();

         Assert.assertEquals(driver.findElement(By.cssSelector("h2[class=\"title text-center\"]:nth-child(1) b")).getText(),"ENTER ACCOUNT INFORMATION","Title is wrong");
         driver.findElement(By.id("id_gender2")).click();
         driver.findElement(By.id("password")).sendKeys(passWord);
         Select days = new Select(driver.findElement(By.id("days")));
         days.selectByValue("1");
         Select month = new Select(driver.findElement(By.id("months")));
         month.selectByVisibleText("June");
         Select year = new Select(driver.findElement(By.id("years")));
         year.selectByValue("1996");
         driver.findElement(By.id("newsletter")).click();
         driver.findElement(By.id("optin")).click();
         driver.findElement(By.id("first_name")).sendKeys(firstName);
         driver.findElement(By.id("last_name")).sendKeys("Magdy");
         driver.findElement(By.id("company")).sendKeys("semiCorner");
         driver.findElement(By.id("address1")).sendKeys("Al-Haram");
         driver.findElement(By.id("address2")).sendKeys("Dokki");
         Select country = new Select(driver.findElement(By.id("country")));
         country.selectByVisibleText("United States");
         driver.findElement(By.id("state")).sendKeys("egypt");
         driver.findElement(By.id("city")).sendKeys("Giza");
         driver.findElement(By.id("zipcode")).sendKeys("3387722");
         driver.findElement(By.id("mobile_number")).sendKeys("+201155154289");
         driver.findElement(By.cssSelector("button[data-qa=\"create-account\"]")).click();
         driver.navigate().back();
         //String actualResult2 =driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/text()")).getText();
        // Assert.assertEquals(actualResult2," Logged in as Rania","Logged in is wrong");
         //driver.findElement(By.cssSelector("a[href=\"/delete_account\"]")).click();





    }


    @Test (priority = 2)
    public void registerUserWithExistingEmail() throws InterruptedException {
         driver.get("https://automationexercise.com/signup");
         //Verify that home page is visible successfully
         //Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
         //Click on 'Signup / Login' button
         //driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
         //Verify 'New User Signup!' is visible

         String actualResult3 = driver.findElement(By.cssSelector("div[class=\"signup-form\"] h2")).getText();
         Assert.assertEquals(actualResult3,"New User Signup!","New User Signup is wrong");
         //Enter name and already registered email address
         driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(firstName);
         driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(email);
         //Click 'Signup' button
         driver.findElement(By.cssSelector("button[data-qa=\"signup-button\"]")).click();
         //Verify error 'Email Address already exist!' is visible
         String actualResult4 = driver.findElement(By.cssSelector("form[action=\"/signup\"] :nth-child(5)")).getText();
         Assert.assertEquals(actualResult4,"Email Address already exist!","Email Address already exist is wrong");





    }

    @Test (priority = 3)
    public void  loginUserWithCorrectEmailAndPassword(){
        driver.get("https://automationexercise.com/signup");
        // Verify that home page is visible successfully
        //Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
        //Click on 'Signup / Login' button
        //driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
        //Verify 'Login to your account' is visible
        String actualResult5 = driver.findElement(By.cssSelector("div[class=\"login-form\"] h2")).getText();
        Assert.assertEquals(actualResult5,"Login to your account","Login is wrong");
        //Enter correct email address and password
        driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
        driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
        //Click 'login' button
        driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
        //Verify that 'Logged in as username' is visible
        soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
    }
      @Test(priority = 4)
    public void  loginUserWithIncorrectEmailAndPassword() throws InterruptedException {
          //Verify that home page is visible successfully
          Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
          //driver.findElement(By.cssSelector("a[href=\"/logout\"]")).click();
          driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
          //Thread.sleep(3000);
          //Verify 'Login to your account' is visible
          String actualResult6 = driver.findElement(By.cssSelector("div[class=\"login-form\"] h2")).getText();
          Assert.assertEquals(actualResult6,"Login to your account","Login is wrong");
          Thread.sleep(3000);
          // Enter incorrect email address and password
          driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(Email);
          Thread.sleep(3000);
          driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
          //Click 'login' button
          driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
          //Verify error 'Your email or password is incorrect!' is visible
          String actualResult7 =driver.findElement(By.cssSelector("form[action=\"/login\"] p")).getText();
          Assert.assertEquals(actualResult7,"Your email or password is incorrect!","Your email or password is incorrect!");

      }
      @Test(priority = 5)
      public void logoutUser(){
          //Navigate to url 'http://automationexercise.com'
          driver.navigate().to("http://automationexercise.com");
          //Verify that home page is visible successfully
          Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
          //Click on 'Signup / Login' button
          driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
          //Verify 'Login to your account' is visible
          String actualResult8 = driver.findElement(By.cssSelector("div[class=\"login-form\"] h2")).getText();
          Assert.assertEquals(actualResult8,"Login to your account","Login is wrong");
          //Enter correct email address and password
          driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
          driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
          //Click 'login' button
          driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
          //Verify that 'Logged in as username' is visible
          soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
          //Click 'Logout' button
          driver.findElement(By.cssSelector("a[href=\"/logout\"]")).click();
          //Verify that user is navigated to login page
          Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/login","Url is wrong");
      }
       @Test(priority=6)
       public void contactUsForm() throws InterruptedException {
           driver.navigate().back();
           //Navigate to url 'http://automationexercise.com'
           driver.navigate().to("http://automationexercise.com");
           //Verify that home page is visible successfully
           Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
           //Click on 'Contact Us' button
           driver.findElement(By.cssSelector("a[href=\"/contact_us\"]")).click();
           //Verify 'GET IN TOUCH' is visible
           String actualResult9 = driver.findElement(By.xpath("//*[contains(text(),'Touch')]")).getText();
           Assert.assertEquals(actualResult9,"GET IN TOUCH","Get In Touch is wrong");
           //Enter name, email, subject and message
           driver.findElement(By.cssSelector("input[name=\"name\"]")).sendKeys("Rania");
           driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("rania777magdy@gmail.com");
           driver.findElement(By.cssSelector("input[name=\"subject\"]")).sendKeys("My Problem is");
           driver.findElement(By.id("message")).sendKeys("I have a problem in my product");
           Thread.sleep(4000);
           driver.findElement(By.cssSelector("input[name=\"upload_file\"]")).sendKeys("C:\\Users\\Youssif Zion\\Desktop\\rania.txt");
           //Click 'Submit' button
           driver.findElement(By.cssSelector("input[data-qa=\"submit-button\"]")).click();
           //Click OK button
           driver.switchTo().alert().accept();
           // Verify success message 'Success! Your details have been submitted successfully.' is visible
           String actualResult10 = driver.findElement(By.cssSelector("div[class=\"status alert alert-success\"]")).getText();
           Assert.assertEquals(actualResult10,"Success! Your details have been submitted successfully.","Title is wrong");
           //Click 'Home' button and verify that landed to home page successfully
           driver.findElement(By.cssSelector("a[class=\"btn btn-success\"]")).click();
    }

           @Test (priority=7)
           public void verifyTestCasesPage(){
               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               //Click on 'Test Cases' button
               driver.findElement(By.cssSelector("a[href=\"/test_cases\"]")).click();
               //Verify user is navigated to test cases page successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/test_cases","Url is wrong");


           }
           @Test(priority=8)
           public void verifyAllProductsAndProductDetailPage() throws InterruptedException {
               //Navigate to url 'http://automationexercise.com'
               driver.navigate().to("http://automationexercise.com");

               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               //Click on 'Products' button
               driver.findElement(By.cssSelector("a[href=\"/products\"]")).click();
               //Verify user is navigated to ALL PRODUCTS page successfully
               //driver.switchTo().newWindow(WindowType.WINDOW).get("https://automationexercise.com/products");
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products","Url is wrong");
               //The products list is visible
               Assert.assertEquals(driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"col-sm-4\"]:nth-child(3) div[class=\"productinfo text-center\"] p")).getText(), "Blue Top","Product Name is wrong");
               Thread.sleep(3000);
               //Click on 'View Product' of first product
               driver.findElement(By.cssSelector("a[href=\"/product_details/1\"]")).click();
               //User is landed to product detail page
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/product_details/1","Url is wrong");
               //Verify that detail detail is visible: product name, category, price, availability, condition, brand
               //Name
               String name = driver.findElement(By.cssSelector("div[class=\"product-information\"] h2")).getText();
               Assert.assertEquals(name,"Blue Top","Blue Top is wrong");
               System.out.println(name);
               //Category
               String category = driver.findElement(By.cssSelector("div[class=\"col-sm-7\"] div[class=\"product-information\"] > p:nth-of-type(1)")).getText();
               Assert.assertEquals(category,"Category: Women > Tops","Category is wrong");
               System.out.println(category);
               //Price
               String price = driver.findElement(By.cssSelector("div[class=\"product-details\"] > div[class=\"col-sm-7\"] > div > span >span")).getText();
               Assert.assertEquals(price,"Rs. 500","price is wrong");
               System.out.println(price);
    }

           @Test(priority = 9)
           public void searchProduct(){
               //Navigate to url 'http://automationexercise.com'
               driver.navigate().to("http://automationexercise.com");
               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               //Click on 'Products' button
               driver.findElement(By.cssSelector("a[href=\"/products\"]")).click();
               //Verify user is navigated to ALL PRODUCTS page successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products","Url is wrong");
               //Enter product name in search input and click search button
               driver.findElement(By.cssSelector("input[id=\"search_product\"]")).sendKeys("Blue Top");
               driver.findElement(By.cssSelector("button[id=\"submit_search\"]")).click();
               //Verify 'SEARCHED PRODUCTS' is visible
               Assert.assertEquals(driver.findElement(By.cssSelector("div[class=\"features_items\"] h2[class=\"title text-center\"]")).getText(), "SEARCHED PRODUCTS","SEARCHED PRODUCTS is wrong");
           }
           @Test (priority = 10)
           public void  verifySubscriptionInHomePage() throws InterruptedException {
               // Navigate to url 'http://automationexercise.com'
               driver.navigate().to("http://automationexercise.com");
               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               Thread.sleep(3000);
               //Scroll down to footer
               JavascriptExecutor js =(JavascriptExecutor) driver;
               js.executeScript("scrollBy(0,9000)");
               Thread.sleep(3000);
               //Verify text 'SUBSCRIPTION'
               Assert.assertEquals(driver.findElement(By.cssSelector("div[class=\"single-widget\"] h2")).getText(), "SUBSCRIPTION","SUBSCRIPTION is wrong");
               Thread.sleep(1000);
               //Enter email address in input and click arrow button
               driver.findElement(By.id("susbscribe_email")).sendKeys("rania777magdy@gmail.com");
               driver.findElement(By.id("subscribe")).click();
               //Verify success message 'You have been successfully subscribed!' is visible
               String actualResult11 = driver.findElement(By.cssSelector("div[class=\"alert-success alert\"]")).getText();
               Assert.assertEquals(actualResult11,"You have been successfully subscribed!","Subscribed is wrong");

           }
           @Test (priority = 11)
           public void verifySubscriptionInCartPage(){
               // Navigate to url 'http://automationexercise.com'
               driver.navigate().to("http://automationexercise.com");
               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               //Click 'Cart' button
               driver.findElement(By.cssSelector("li a[href=\"/view_cart\"]")).click();
               //Scroll down to footer
               JavascriptExecutor js= (JavascriptExecutor) driver;
               js.executeScript("scrollBy(0,9000)");
               //Verify text 'SUBSCRIPTION'
               Assert.assertEquals(driver.findElement(By.cssSelector("div[class=\"single-widget\"] h2")).getText(), "SUBSCRIPTION","SUBSCRIPTION is wrong");
               // Enter email address in input and click arrow button
               driver.findElement(By.id("susbscribe_email")).sendKeys("rania777magdy@gmail.com");
               driver.findElement(By.id("subscribe")).click();
               //Verify success message 'You have been successfully subscribed!' is visible
               String actualResult11 = driver.findElement(By.cssSelector("div[class=\"alert-success alert\"]")).getText();
               Assert.assertEquals(actualResult11,"You have been successfully subscribed!","Subscribed is wrong");
           }
           @Test (priority = 12)
           public void  addProductsInCart() throws InterruptedException {
               // Navigate to url 'http://automationexercise.com'
               driver.navigate().to("http://automationexercise.com");
               //Verify that home page is visible successfully
               Assert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
               //Click 'Products' button
               Thread.sleep(2000);
               driver.findElement(By.cssSelector("a[href=\"/products\"]")).click();
               //Hover over first product and click 'Add to cart'
               WebElement element =driver.findElement(By.cssSelector("img[src=\"/get_product_picture/1\"]"));
               Actions actions = new Actions(driver);
               actions.moveToElement(element).perform();
               driver.findElement(By.cssSelector("div[class=\"overlay-content\"] a[data-product-id=\"1\"]")).click();
               //Click 'Continue Shopping' button
               driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
               //Hover over second product and click 'Add to cart'
               WebElement element1= driver.findElement(By.cssSelector("img[src=\"/get_product_picture/2\"]"));
               Actions actions1 = new Actions(driver);
               actions1.moveToElement(element1).perform();
               Thread.sleep(2000);
               driver.findElement(By.cssSelector("div[class=\"overlay-content\"] a[data-product-id=\"2\"]")).click();
                Thread.sleep(2000);
               //Click 'View Cart' button
               driver.findElement(By.cssSelector("div[class=\"modal-body\"] a[href=\"/view_cart\"]")).click();
               Thread.sleep(2000);
               //Verify both products are added to Cart
               List<WebElement> cartItems= driver.findElements(By.id("cart_items"));
               System.out.println(cartItems);
               //Verify their prices, quantity and total price
               //Price1
               String price1 = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_price\"] p:nth-of-type(1)")).getText();
               Assert.assertEquals(price1,"Rs. 500","Price 1 is wrong");
               System.out.println(price1);
               String price2 = driver.findElement(By.cssSelector("tr[id=\"product-2\"] td[class=\"cart_price\"] p:nth-of-type(1)")).getText();
               Assert.assertEquals(price2,"Rs. 400","Price 2 is wrong");
               System.out.println(price2);
               //quantity
               String quantity = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_quantity\"] button[class=\"disabled\"]")).getText();
               Assert.assertEquals(quantity,"1","quantity is wrong");
               System.out.println(quantity);
               //total price
               String totalPrice = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_total\"] p[class=\"cart_total_price\"]")).getText();
               Assert.assertEquals(totalPrice,"Rs. 500","Total Price is wrong");
               System.out.println(totalPrice);



    }

               @Test (priority = 13)
               public void verifyProductQuantityInCart() throws InterruptedException {
                   // Navigate to url 'http://automationexercise.com'
                   driver.navigate().to("http://automationexercise.com");
                   //Verify that home page is visible successfully
                   soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
                   //Click 'View Product' for any product on home page
                   driver.findElement(By.cssSelector("a[href=\"/product_details/5\"]")).click();
                   Thread.sleep(500);
                   // Verify product detail is opened
                   soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/product_details/5");
                   // Increase quantity to 4
                   driver.findElement(By.id("quantity")).clear();
                   Thread.sleep(500);
                   driver.findElement(By.id("quantity")).sendKeys("4");
                   Thread.sleep(500);
                   //Click 'Add to cart' button
                   driver.findElement(By.cssSelector("button[class=\"btn btn-default cart\"]")).click();
                   //Click 'View Cart' button
                   driver.findElement(By.cssSelector("div[class=\"modal-body\"] a[href=\"/view_cart\"]")).click();
                   //Verify that product is displayed in cart page with exact quantity
                   String quantity1 = driver.findElement(By.cssSelector("tr[id=\"product-5\"] td[class=\"cart_quantity\"] button[class=\"disabled\"]")).getText();
                   soft.assertEquals(quantity1,"4","quantity is wrong");
                   System.out.println(quantity1);
                   soft.assertAll();

               }



               @Test(priority = 14)
               public void placeOrderRegisterWhileCheckOut() throws InterruptedException {
                   // Navigate to url 'http://automationexercise.com'
                   driver.navigate().to("http://automationexercise.com");
                   //Verify that home page is visible successfully
                   soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
                   //Add products to cart
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"] ")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(2000);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(3000);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(500);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   //Click 'Cart' button
                   driver.findElement(By.cssSelector("li a[href=\"/view_cart\"]")).click();
                   Thread.sleep(3000);
                   //Verify that cart page is displayed
                   soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/view_cart");
                   Thread.sleep(3000);
                   //Click Proceed To Checkout
                   driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                   //Click 'Register / Login' button
                   driver.findElement(By.cssSelector("a[href=\"/login\"] u")).click();
                   Thread.sleep(3000);
                   //Fill all details in Signup and create account
                   driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(firstName);
                   driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(email);
                   driver.findElement(By.cssSelector("button[data-qa=\"signup-button\"]")).click();
                   //Verify 'ACCOUNT CREATED!' and click 'Continue' button
                   String emailIsExist = driver.findElement(By.cssSelector("form[action=\"/signup\"] p:nth-of-type(1)")).getText();
                   soft.assertEquals(emailIsExist,"Email Address already exist!" ,"Email is not exist ");
                   System.out.println(emailIsExist);
                   Thread.sleep(3000);
                   //Verify ' Logged in as username' at top
                   driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
                   driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
                   driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
                   Thread.sleep(3000);
                   soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b")).isDisplayed());
                   Thread.sleep(2000);
                   //Click 'Cart' button
                   driver.findElement(By.cssSelector("li a[href=\"/view_cart\"]")).click();
                   //Click 'Proceed To Checkout' button
                   driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                   Thread.sleep(2000);
                   //Verify Address Details and Review Your Order
                   soft.assertTrue(driver.findElement(By.cssSelector("#cart_items >div > div:nth-child(2) > h2")).isDisplayed());
                   soft.assertTrue(driver.findElement(By.cssSelector("#cart_items > div > div:nth-child(4) > h2")).isDisplayed());
                   Thread.sleep(3000);
                   //Enter description in comment text area and click 'Place Order'
                   driver.findElement(By.cssSelector("textarea[class=\"form-control\"]")).sendKeys("Very good,Thank you");
                   driver.findElement(By.cssSelector("a[href=\"/payment\"]")).click();
                   Thread.sleep(2000);
                   //Enter payment details: Name on Card, Card Number, CVC, Expiration date
                   driver.findElement(By.cssSelector("input[name=\"name_on_card\"]")).sendKeys("rania");
                   driver.findElement(By.cssSelector("input[name=\"card_number\"]")).sendKeys("123");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-cvc\"]")).sendKeys("1234");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-month\"]")).sendKeys("11");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-year\"]")).sendKeys("2024");
                   Thread.sleep(3000);
                   //Click 'Pay and Confirm Order' button
                   driver.findElement(By.cssSelector("button[class=\"form-control btn btn-primary submit-button\"]")).click();
                   //Verify success message 'Your order has been placed successfully!'
                   soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"order-placed\"]")).isDisplayed());
                   //Verify success message 'Your order has been placed successfully!'
                   String confirmOrder =driver.findElement(By.xpath("//*[contains(text(),'Congratulations!')]")).getText();
                   soft.assertEquals(confirmOrder,"Congratulations! Your order has been confirmed!","Congratulations! Your order has been confirmed!");
                   System.out.println(confirmOrder);
                   //Click 'Delete Account' button
                   driver.findElement(By.cssSelector("a[href=\"/delete_account\"]")).click();
                   //Verify 'ACCOUNT DELETED!' and click 'Continue' button
                   soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"account-deleted\"] b")).isDisplayed());
                   soft.assertAll();
               }



               @Test(priority = 15)
               public void placeOrderRegisterBeforeCheckout() throws InterruptedException {
                   // Navigate to url 'http://automationexercise.com'
                   driver.navigate().to("http://automationexercise.com");
                   //Verify that home page is visible successfully
                   soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/","Url is wrong");
                   //Click 'Signup / Login' button
                   driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
                   Thread.sleep(3000);
                   //Fill all details in Signup and create account
                   //Enter name and email address
                   driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(firstName);
                   driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(email);
                   //Click 'Signup' button
                   driver.findElement(By.cssSelector("button[data-qa=\"signup-button\"]")).click();

                   Assert.assertEquals(driver.findElement(By.cssSelector("h2[class=\"title text-center\"]:nth-child(1) b")).getText(),"ENTER ACCOUNT INFORMATION","Title is wrong");
                   driver.findElement(By.id("id_gender2")).click();
                   driver.findElement(By.id("password")).sendKeys(passWord);
                   Select days = new Select(driver.findElement(By.id("days")));
                   days.selectByValue("1");
                   Select month = new Select(driver.findElement(By.id("months")));
                   month.selectByVisibleText("June");
                   Select year = new Select(driver.findElement(By.id("years")));
                   year.selectByValue("1996");
                   driver.findElement(By.id("newsletter")).click();
                   driver.findElement(By.id("optin")).click();
                   driver.findElement(By.id("first_name")).sendKeys(firstName);
                   driver.findElement(By.id("last_name")).sendKeys("Magdy");
                   driver.findElement(By.id("company")).sendKeys("semiCorner");
                   driver.findElement(By.id("address1")).sendKeys("Al-Haram");
                   driver.findElement(By.id("address2")).sendKeys("Dokki");
                   Select country = new Select(driver.findElement(By.id("country")));
                   country.selectByVisibleText("United States");
                   driver.findElement(By.id("state")).sendKeys("egypt");
                   driver.findElement(By.id("city")).sendKeys("Giza");
                   driver.findElement(By.id("zipcode")).sendKeys("3387722");
                   driver.findElement(By.id("mobile_number")).sendKeys("+201155154289");
                   driver.findElement(By.cssSelector("button[data-qa=\"create-account\"]")).click();
                   //Verify ' Logged in as username' at top
                   Thread.sleep(3000);
                   driver.navigate().to("http://automationexercise.com");
                   soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
                   //Add products to cart
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"] ")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(2000);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(3000);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   Thread.sleep(500);
                   driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                   driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                   //Click 'Cart' button
                   driver.findElement(By.cssSelector("li a[href=\"/view_cart\"]")).click();
                   //Verify that cart page is displayed
                   soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/view_cart");
                   Thread.sleep(3000);
                   //Click Proceed To Checkout
                   driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                   //Verify Address Details and Review Your Order
                   soft.assertTrue(driver.findElement(By.cssSelector("#cart_items >div > div:nth-child(2) > h2")).isDisplayed());
                   soft.assertTrue(driver.findElement(By.cssSelector("#cart_items > div > div:nth-child(4) > h2")).isDisplayed());
                   Thread.sleep(3000);
                   //Enter description in comment text area and click 'Place Order'
                   driver.findElement(By.cssSelector("textarea[class=\"form-control\"]")).sendKeys("Very good,Thank you");
                   driver.findElement(By.cssSelector("a[href=\"/payment\"]")).click();
                   //Enter payment details: Name on Card, Card Number, CVC, Expiration date
                   driver.findElement(By.cssSelector("input[name=\"name_on_card\"]")).sendKeys("rania");
                   driver.findElement(By.cssSelector("input[name=\"card_number\"]")).sendKeys("123");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-cvc\"]")).sendKeys("1234");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-month\"]")).sendKeys("11");
                   driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-year\"]")).sendKeys("2024");
                   Thread.sleep(3000);
                   //Click 'Pay and Confirm Order' button
                   driver.findElement(By.cssSelector("button[class=\"form-control btn btn-primary submit-button\"]")).click();
                   //Verify success message 'Your order has been placed successfully!'
                   soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"order-placed\"]")).isDisplayed());
                   //Click 'Delete Account' button
                   //driver.findElement(By.cssSelector("a[href=\"/delete_account\"]")).click();
                   //Verify 'ACCOUNT DELETED!' and click 'Continue' button
                  // soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"account-deleted\"] b")).isDisplayed());
                   soft.assertAll();


               }


                  @Test(priority = 16)
                public void loginBeforeCheckout() throws InterruptedException {
                      // Navigate to url 'http://automationexercise.com'
                      driver.navigate().to("http://automationexercise.com");
                      // Verify that home page is visible successfully
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                      Thread.sleep(1000);
                      // Click 'Signup / Login' button
                      //driver.findElement(By.cssSelector("a[href=\"/logout\"]")).click();
                      driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
                      Thread.sleep(500);
                      //Fill email, password and click 'Login' button
                      driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
                      // Verify ' Logged in as username' at top
                     // soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
                      // Add products to cart
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      //Click 'Cart' button
                      driver.findElement(By.cssSelector("li a[href=\"/view_cart\"] ")).click();
                      Thread.sleep(500);
                      // Verify that cart page is displayed
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
                      Thread.sleep(1000);
                      //Click 'Proceed To Checkout' button
                      driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                      Thread.sleep(2000);
                      //Verify Address Details and Review Your Order
                      soft.assertTrue(driver.findElement(By.cssSelector("#cart_items >div > div:nth-child(2) > h2")).isDisplayed());
                      soft.assertTrue(driver.findElement(By.cssSelector("#cart_items > div > div:nth-child(4) > h2")).isDisplayed());
                      Thread.sleep(3000);
                      //Enter description in comment text area and click 'Place Order'
                      driver.findElement(By.cssSelector("textarea[class=\"form-control\"]")).sendKeys("Very good,Thank you");
                      driver.findElement(By.cssSelector("a[href=\"/payment\"]")).click();
                      Thread.sleep(2000);
                      //Enter payment details: Name on Card, Card Number, CVC, Expiration date
                      driver.findElement(By.cssSelector("input[name=\"name_on_card\"]")).sendKeys("Rania");
                      driver.findElement(By.cssSelector("input[name=\"card_number\"]")).sendKeys("123");
                      driver.findElement(By.cssSelector("input[class=\"form-control card-cvc\"]")).sendKeys("1234");
                      driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-month\"]")).sendKeys("11");
                      driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-year\"]")).sendKeys("2024");
                      Thread.sleep(3000);
                      //Click 'Pay and Confirm Order' button
                      driver.findElement(By.cssSelector("button[class=\"form-control btn btn-primary submit-button\"]")).click();
                      Thread.sleep(3000);
                      //Verify success message 'Your order has been placed successfully!'
                      soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"order-placed\"]")).isDisplayed());
                      soft.assertAll();

                  }
                  @Test(priority = 17)

                  public void  removeProductsFromCart() throws InterruptedException {
                      // Navigate to url 'http://automationexercise.com'
                      driver.navigate().to("http://automationexercise.com");
                      // Verify that home page is visible successfully
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                      Thread.sleep(1000);
                      //Add products to cart
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                      Thread.sleep(500);
                      //Click 'Cart' button
                      driver.findElement(By.cssSelector("li a[href=\"/view_cart\"] ")).click();
                      Thread.sleep(500);
                      // Verify that cart page is displayed
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
                      //Click 'X' button corresponding to particular product
                      driver.findElement(By.cssSelector("a[data-product-id=\"1\"]")).click();
                      Thread.sleep(500);
                      driver.findElement(By.cssSelector("a[data-product-id=\"2\"]")).click();
                      Thread.sleep(500);
                      // Verify that product is removed from the cart
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
                      Thread.sleep(500);
                      soft.assertAll();
                  }
                  @Test(priority = 18)
                  public void viewCategoryProducts() throws InterruptedException {
                      // Navigate to url 'http://automationexercise.com'
                      driver.navigate().to("http://automationexercise.com");
                      // Verify that home page is visible successfully
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                      Thread.sleep(3000);
                      //Verify that categories are visible on left side bar
                      soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"left-sidebar\"]")).isDisplayed());
                      Thread.sleep(500);
                      //Click on 'Women' category
                      driver.findElement(By.cssSelector("a[href=\"#Women\"]")).click();
                      //Click on any category link under 'Women' category, for example: Dress
                      driver.findElement(By.cssSelector("a[href=\"/category_products/1\"]")).click();
                      Thread.sleep(300);
                      //Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
                      driver.switchTo().newWindow(WindowType.WINDOW).get("https://automationexercise.com/category_products/2");
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/category_products/2");
                      Thread.sleep(500);
                      //soft.assertTrue(driver.findElement(By.cssSelector("h2[class=\"title text-center\"]")).getText().equals("WOMEN - TOPS PRODUCTS"));
                      //Thread.sleep(500);
                      //On left side bar, click on any sub-category link of 'Men' category
                      driver.findElement(By.cssSelector("a[href=\"#Men\"]")).click();
                      Thread.sleep(600);
                      driver.findElement(By.cssSelector("a[href=\"/category_products/3\"]")).click();
                      //verify that user is navigated to that category page
                      soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/category_products/3");
                      soft.assertAll();
                  }
                   @Test(priority = 19)
                  public void  viewCartBrandProducts() throws InterruptedException {
                       // Navigate to url 'http://automationexercise.com'
                       driver.navigate().to("http://automationexercise.com");
                       //Click on 'Products' button
                       driver.findElement(By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/products\"]")).click();
                       Thread.sleep(3000);
                       //Verify that Brands are visible on left side bar
                       soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"brands_products\"]")).isDisplayed());
                       Thread.sleep(500);
                       //Click on any brand name
                       driver.findElement(By.cssSelector("a[href=\"/brand_products/Polo\"]")).click();
                       Thread.sleep(3000);
                       //Verify that user is navigated to brand page and brand products are displayed
                       soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/brand_products/Polo");
                       Thread.sleep(3000);
                       soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"features_items\"] h2[class=\"title text-center\"]")).isDisplayed());
                       Thread.sleep(3000);
                       //On left side bar, click on any other brand link
                       driver.findElement(By.cssSelector("a[href=\"/brand_products/H&M\"]")).click();
                       Thread.sleep(3000);
                       //Verify that user is navigated to that brand page and can see products
                       soft.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/brand_products/H&M");
                       Thread.sleep(3000);
                       soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"features_items\"]")).isDisplayed());
                       soft.assertAll();
                   }
                    @Test(priority = 20)
                   public void SearchProductsAndVerifyCartAfterLogin() throws InterruptedException {
                        // Navigate to url 'http://automationexercise.com'
                        driver.navigate().to("http://automationexercise.com");
                        //Click on 'Products' button
                        driver.findElement(By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/products\"]")).click();
                        Thread.sleep(3000);
                        // Verify user is navigated to ALL PRODUCTS page successfully
                        soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
                        Thread.sleep(500);
                        //Enter product name in search input and click search button
                        driver.findElement(By.cssSelector("input[id=\"search_product\"]")).sendKeys("Blue Top");
                        driver.findElement(By.id("submit_search")).click();
                        // Verify 'SEARCHED PRODUCTS' is visible
                        soft.assertTrue(driver.findElement(By.cssSelector("h2[class=\"title text-center\"]")).getText().equals("SEARCHED PRODUCTS"));
                        Thread.sleep(500);
                        //Verify all the products related to search are visible
                        soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"features_items\"]")).isDisplayed());
                        Thread.sleep(500);
                        //Add those products to cart
                        driver.findElement(By.cssSelector("div[class=\"productinfo text-center\"] a[data-product-id=\"1\"]")).click();
                        //Click 'Cart' button and verify that products are visible in cart
                        driver.findElement(By.cssSelector("ul a[href=\"/view_cart\"]")).click();
                        Thread.sleep(500);
                        soft.assertTrue(driver.findElement(By.id("cart_info")).isDisplayed());
                        Thread.sleep(3000);
                        //Click 'Signup / Login' button and submit login details
                        driver.findElement(By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/login\"]")).click();
                        driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
                        driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
                        driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
                        //Again, go to Cart page
                        driver.findElement(By.cssSelector("a[href=\"/view_cart\"]")).click();
                        //Verify that those products are visible in cart after login as well
                        soft.assertTrue(driver.findElement(By.id("cart_info")).isDisplayed());
                        Thread.sleep(500);
                        soft.assertAll();
                    }

                    @Test(priority = 21)
                    public void  addReviewOnProduct() throws InterruptedException {
                        // Navigate to url 'http://automationexercise.com'
                        driver.navigate().to("http://automationexercise.com");
                        //Click on 'Products' button
                        driver.findElement(By.cssSelector("ul[class=\"nav navbar-nav\"] a[href=\"/products\"]")).click();
                        Thread.sleep(3000);
                        // Verify user is navigated to ALL PRODUCTS page successfully
                        soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products");
                        Thread.sleep(500);
                        //Click on 'View Product' button
                        driver.findElement(By.cssSelector("a[href=\"/product_details/2\"]")).click();
                        //Verify 'Write Your Review' is visible
                        soft.assertTrue(driver.findElement(By.cssSelector("a[href=\"#reviews\"]")).isDisplayed());
                        //Enter name, email and review
                        driver.findElement(By.id("name")).sendKeys(firstName);
                        driver.findElement(By.id("email")).sendKeys(email);
                        driver.findElement(By.cssSelector("textarea[id=\"review\"]")).sendKeys("Product is very good");
                        //Click 'Submit' button
                        driver.findElement(By.id("button-review")).click();
                        // Verify success message 'Thank you for your review.'
                        soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"alert-success alert\"] span")).isDisplayed());
                        soft.assertAll();

                  }

                     @Test(priority = 22)
                     public void  addToCartFromRecommendedItems() throws InterruptedException {
                         // Navigate to url 'http://automationexercise.com'
                         driver.navigate().to("http://automationexercise.com");
                         //Scroll to bottom of page
                         JavascriptExecutor js=(JavascriptExecutor) driver;
                         js.executeScript("scrollBy(0,7500)");
                         Thread.sleep(5000);
                         //Verify 'RECOMMENDED ITEMS' are visible
                         soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"recommended_items\"]")).isDisplayed());
                         Thread.sleep(5000);
                         //Click on 'Add To Cart' on Recommended product
                         driver.findElement(By.cssSelector("div[id=\"recommended-item-carousel\"] a[data-product-id=\"5\"]")).click();
                         Thread.sleep(5000);
                         //Click on 'View Cart' button
                         driver.findElement(By.cssSelector("div[class=\"modal-content\"] a[href=\"/view_cart\"]")).click();
                         //Verify that product is displayed in cart page
                         soft.assertTrue(driver.findElement(By.id("cart_info_table")).isDisplayed());
                         soft.assertAll();
                     }

                     @Test(priority = 23)
                     public void verifyAddressDetailsInCheckoutPage() throws InterruptedException {
                         // Navigate to url 'http://automationexercise.com'
                         driver.navigate().to("http://automationexercise.com");
                         // Verify that home page is visible successfully
                         soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                         Thread.sleep(3000);
                         //Click 'Signup / Login' button
                         driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();
                         //Fill all details in Signup and create account
                         driver.findElement(By.cssSelector("input[data-qa=\"login-email\"]")).sendKeys(email);
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("input[data-qa=\"login-password\"]")).sendKeys(passWord);
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[data-qa=\"login-button\"]")).click();
                         //Verify ' Logged in as username' at top
                         // Verify ' Logged in as username' at top
                         soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
                         // Add products to cart
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         //Click 'Cart' button
                         driver.findElement(By.cssSelector("li a[href=\"/view_cart\"] ")).click();
                         Thread.sleep(500);
                         // Verify that cart page is displayed
                         soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
                         Thread.sleep(1000);
                         //Click 'Proceed To Checkout' button
                         driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                         Thread.sleep(2000);
                         //Verify that the delivery address is same address filled at the time registration of account
                         soft.assertTrue(driver.findElement(By.cssSelector("ul[id=\"address_delivery\"] li:nth-of-type(4)")).getText().equals("Al-Haram"));
                         soft.assertTrue(driver.findElement(By.cssSelector("ul[id=\"address_delivery\"] li:nth-of-type(5)")).getText().equals("Dokki"));
                         //Verify that the billing address is same address filled at the time registration of account
                         soft.assertTrue(driver.findElement(By.cssSelector("ul[id=\"address_invoice\"] li:nth-child(4)")).getText().equals("Al-Haram"));
                         soft.assertTrue(driver.findElement(By.cssSelector("ul[id=\"address_invoice\"] li:nth-child(5)")).getText().equals("Dokki"));
                         //Click 'Delete Account' button
                         driver.findElement(By.cssSelector("a[href=\"/delete_account\"]")).click();
                         //Verify 'ACCOUNT DELETED!' and click 'Continue' button
                         soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"account-deleted\"] b")).isDisplayed());
                         soft.assertAll();
                     }
                     @Test(priority = 24)
                     public void downloadInvoiceAfterPurchaseOrder() throws InterruptedException {
                         // Navigate to url 'http://automationexercise.com'
                         driver.navigate().to("http://automationexercise.com");
                         // Verify that home page is visible successfully
                         soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                         Thread.sleep(3000);
                         // Add products to cart
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"1\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"2\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"3\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("div[class=\"features_items\"] div[class=\"productinfo text-center\"] a[data-product-id=\"4\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]")).click();
                         Thread.sleep(500);

                         //Click 'Cart' button
                         driver.findElement(By.cssSelector("li a[href=\"/view_cart\"] ")).click();
                         Thread.sleep(500);
                         // Verify that cart page is displayed
                         soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
                         Thread.sleep(5000);
                         //Click 'Proceed To Checkout' button
                         driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                         Thread.sleep(3000);
                         //Click 'Register / Login' button
                         driver.findElement(By.cssSelector("div[class=\"modal-content\"] a[href=\"/login\"]")).click();
                         Thread.sleep(3000);
                         //Fill all details in Signup and create account
                         driver.findElement(By.cssSelector("input[data-qa=\"signup-name\"]")).sendKeys(firstName);
                         driver.findElement(By.cssSelector("input[data-qa=\"signup-email\"]")).sendKeys(email);
                         //Click 'Signup' button
                         driver.findElement(By.cssSelector("button[data-qa=\"signup-button\"]")).click();

                         Assert.assertEquals(driver.findElement(By.cssSelector("h2[class=\"title text-center\"]:nth-child(1) b")).getText(),"ENTER ACCOUNT INFORMATION","Title is wrong");
                         driver.findElement(By.id("id_gender2")).click();
                         driver.findElement(By.id("password")).sendKeys(passWord);
                         Select days = new Select(driver.findElement(By.id("days")));
                         days.selectByValue("1");
                         Select month = new Select(driver.findElement(By.id("months")));
                         month.selectByVisibleText("June");
                         Select year = new Select(driver.findElement(By.id("years")));
                         year.selectByValue("1996");
                         driver.findElement(By.id("newsletter")).click();
                         driver.findElement(By.id("optin")).click();
                         driver.findElement(By.id("first_name")).sendKeys(firstName);
                         driver.findElement(By.id("last_name")).sendKeys("Magdy");
                         driver.findElement(By.id("company")).sendKeys("semiCorner");
                         driver.findElement(By.id("address1")).sendKeys("Al-Haram");
                         driver.findElement(By.id("address2")).sendKeys("Dokki");
                         Select country = new Select(driver.findElement(By.id("country")));
                         country.selectByVisibleText("United States");
                         driver.findElement(By.id("state")).sendKeys("egypt");
                         driver.findElement(By.id("city")).sendKeys("Giza");
                         driver.findElement(By.id("zipcode")).sendKeys("3387722");
                         driver.findElement(By.id("mobile_number")).sendKeys("3387722");
                         driver.findElement(By.cssSelector("button[data-qa=\"create-account\"]")).click();
                         Thread.sleep(500);
                         driver.findElement(By.cssSelector("a[href=\"/view_cart\"]")).click();
                         //Verify ' Logged in as username' at top
                         Thread.sleep(3000);
                         soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed());
                         //Click 'Proceed To Checkout' button
                         driver.findElement(By.cssSelector("a[class=\"btn btn-default check_out\"]")).click();
                         Thread.sleep(2000);
                         //Verify Address Details and Review Your Order
                         soft.assertTrue(driver.findElement(By.cssSelector("#cart_items >div > div:nth-child(2) > h2")).isDisplayed());
                         soft.assertTrue(driver.findElement(By.cssSelector("#cart_items > div > div:nth-child(4) > h2")).isDisplayed());
                         Thread.sleep(3000);
                         //Enter description in comment text area and click 'Place Order'
                         driver.findElement(By.cssSelector("textarea[class=\"form-control\"]")).sendKeys("Very good,Thank you");
                         driver.findElement(By.cssSelector("a[href=\"/payment\"]")).click();
                         Thread.sleep(2000);
                         //Enter payment details: Name on Card, Card Number, CVC, Expiration date
                         driver.findElement(By.cssSelector("input[name=\"name_on_card\"]")).sendKeys("rania");
                         driver.findElement(By.cssSelector("input[name=\"card_number\"]")).sendKeys("123");
                         driver.findElement(By.cssSelector("input[class=\"form-control card-cvc\"]")).sendKeys("1234");
                         driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-month\"]")).sendKeys("11");
                         driver.findElement(By.cssSelector("input[class=\"form-control card-expiry-year\"]")).sendKeys("2024");
                         Thread.sleep(3000);
                         //Click 'Pay and Confirm Order' button
                         driver.findElement(By.cssSelector("button[class=\"form-control btn btn-primary submit-button\"]")).click();
                         Thread.sleep(3000);
                         //Verify success message 'Your order has been placed successfully!'
                         soft.assertTrue(driver.findElement(By.cssSelector("h2[data-qa=\"order-placed\"]")).isDisplayed());
                         Thread.sleep(5000);
                         //Click 'Download Invoice' button and verify invoice is downloaded successfully
                         ChromeOptions options =new ChromeOptions();
                         HashMap<String, Object> chromePrefs =new HashMap <String,Object>();
                         chromePrefs.put("profile.default_content_setting.popups",0);
                         String downloadFilePath=System.getProperty("user.dir");
                         chromePrefs.put("download.default_directory",downloadFilePath);
                         options.setExperimentalOption("Prefs",chromePrefs);
                         driver.findElement(By.cssSelector("a[href=\"/download_invoice/3400\"]")).click();
                         File file=new File(downloadFilePath+"\\voice.txt");
                         if (file.exists()) {
                             System.out.println("File got successfully downloaded");
                         }else{
                             System.out.println("File is not downloaded");
                         }
                         // Click 'Continue' button
                         driver.findElement(By.cssSelector("a[data-qa=\"continue-button\"]")).click();
                         Thread.sleep(500);
                         soft.assertAll();
                     }
                     @Test(priority = 25,description = "verify Scroll UpUsing")
                     public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality() throws InterruptedException {
                         // Navigate to url 'http://automationexercise.com'
                         driver.navigate().to("http://automationexercise.com");
                         // Verify that home page is visible successfully
                         soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                         Thread.sleep(3000);
                         //Scroll down page to bottom
                         JavascriptExecutor js=(JavascriptExecutor) driver;
                         js.executeScript("window.scrollBy(0,8000);");
                         //Thread.sleep(5000);
                         // Verify 'SUBSCRIPTION' is visible
                         soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"single-widget\"] h2")).isDisplayed());
                         Thread.sleep(500);
                         // Click on arrow at bottom right side to move upward
                         driver.findElement(By.id("scrollUp")).click();
                         Thread.sleep(500);
                         Thread.sleep(500);
                         // Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
                         soft.assertTrue(driver.findElement(By.cssSelector("#slider-carousel > div > div.item.active.left > div:nth-child(1) > h2")).isDisplayed());
                         soft.assertAll();
                     }
                        @Test(priority = 26)
                        public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctionality() throws InterruptedException {
                            // Verify that home page is visible successfully
                            soft.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
                            Thread.sleep(1000);
                            // Scroll down page to bottom
                            JavascriptExecutor js=(JavascriptExecutor) driver;
                            js.executeScript("window.scrollBy(0,8000);");
                            // Verify 'SUBSCRIPTION' is visible
                            soft.assertTrue(driver.findElement(By.cssSelector("div[class=\"single-widget\"] h2")).isDisplayed());
                            Thread.sleep(500);
                            // Scroll up page to top
                            JavascriptExecutor js1=(JavascriptExecutor) driver;
                            js1.executeScript("window.scrollBy(0,-8000);");
                            Thread.sleep(500);
                            // Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
                            soft.assertTrue(driver.findElement(By.xpath("//*[@id=\"slider-carousel\"]/div/div[1]/div[1]/h2")).isDisplayed());
                            soft.assertAll();
                        }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();

    }
}
