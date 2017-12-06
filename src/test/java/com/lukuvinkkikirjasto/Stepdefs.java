package com.lukuvinkkikirjasto;

import com.lukuvinkkikirjasto.Main;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

import static org.junit.Assert.*;



public class Stepdefs {

    int port = 8080;

    private static ConfigurableApplicationContext context;


    static {
        context = SpringApplication.run(Main.class, "");
        ChromeDriverManager.getInstance().setup();
    }
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:" + port + "/add";

    @After
    public void after(){

        if(context != null) {
            SpringApplication.exit(context);
        }

        context = SpringApplication.run(Main.class, "");
    }


    @Given("^page is opened")
    public void open_page() throws Throwable {
        driver.get(baseUrl);
    }



    @When("^name \"([^\"]*)\" and tag \"([^\"]*)\" are entered$")
    public void login_enter_credentials(String name, String tag) throws Throwable {
        WebElement element = driver.findElement(By.name("bookName"));
        element.sendKeys(name);
        element = driver.findElement(By.name("tags"));
        element.sendKeys(tag);
        element = driver.findElement(By.id("submit-book"));

        element.submit();
    }


    @Then("^a new book with name \"([^\"]*)\" is added$")
    public void containsName(String name) throws Throwable {
        assertTrue(driver.getPageSource().contains(name));
    }

    @Then("^a new book with name \"([^\"]*)\" is opened$")
    public void a_new_book_with_name_is_opened(String arg1) throws Throwable {
        WebElement element = driver.findElement(By.cssSelector(".book-link"));
        element.click();
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^the edit button is clicked$")
    public void the_edit_button_is_clicked() throws Throwable {
        WebElement element = driver.findElement(By.cssSelector(".edit-button"));
        element.click();
    }

    @Then("^name \"([^\"]*)\" is entered$")
    public void name_is_entered(String name) throws Throwable {
        WebElement element = driver.findElement(By.name("bookName"));
        element.clear();
        element.sendKeys(name);
        element = driver.findElement(By.id("submit-book"));
        element.submit();
    }

    @Then("^the book is saved with name \"([^\"]*)\"$")
    public void the_book_is_saved(String name) throws Throwable {
        assertTrue(driver.getPageSource().contains(name));
    }


    @Then("^the delete button is clicked$")
    public void the_delete_button_is_clicked() throws Throwable {
        WebElement element = driver.findElement(By.cssSelector(".delete-button"));
        element.click();
    }

    @Then("^a book with name \"([^\"]*)\" is not visible$")
    public void a_book_with_name_is_not_visible(String name) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(!driver.getPageSource().contains(name));
    }


}
