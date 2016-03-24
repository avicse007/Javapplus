package com.avinash;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.WebRowSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyClass implements Runnable{
	
	final String URL; 
	public MyClass(String url){
		URL=url;
	}
	public List<String> openBrandUrl(String url){
		WebDriver driver=new FirefoxDriver();
		driver.get(url);
		try{
		//Thread.sleep(2000);
		System.out.println("The page title for the Url "+url +" is " +driver.getTitle());
		WebElement dialogBox=(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pcs-modal-box-dialog")));
		WebElement pincodeInput = driver.findElement(By.cssSelector("#pincode-salience-check"));
		pincodeInput.clear();
		pincodeInput.sendKeys("110020");
		WebElement pinCodeSubmit = driver.findElement(By.cssSelector("[class^='col-xs-5 btn']"));
		pinCodeSubmit.click();
		}
		catch(Exception e){
			System.out.println("Dilog not found");
		}
		List<WebElement> list =driver.findElements(By.cssSelector(".brand-title-link"));
		int size=list.size();
		List<String>urls=new ArrayList<String>();
		for(int i=0;i<size;i++){
			urls.add(list.get(i).getAttribute("href"));
		}
		driver.quit();
		return urls;
		
	}
	
	
	public void openUrl(String url){
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(url);
			System.out.println("The value inner url is :"+url);
			System.out.println("The title of inner page url is : "+driver.getTitle());
			driver.close();
		}
		
	public void run() {
		// TODO Auto-generated method stub
		openUrl(URL);
	}

}
