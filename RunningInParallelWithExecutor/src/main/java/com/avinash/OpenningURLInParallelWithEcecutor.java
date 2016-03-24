package com.avinash;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenningURLInParallelWithEcecutor {
	private static final int MYTHREADS = 30;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
			ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
			//WebDriver driver1=new FirefoxDriver();
			String[] host = { "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			for(int ii=0;ii<host.length;ii++){
			List<String> hostList=new MyClass("").openBrandUrl("http://www.snapdeal.com/brands/"+host[ii]);
			int size=hostList.size();
			for (int i = 0; i < size; i++) {
				String url=hostList.get(i);
				//WebDriver driver=new FirefoxDriver();
				Runnable runnable=new MyClass(url);
				executor.execute(runnable);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
				 
			}
			System.out.println("\nFinished all threads");
			Thread.sleep(9000);
			System.out.println("After wailting for 9 seconds closing all the windows ");
			WebDriver driver=new FirefoxDriver();
			driver.quit();
			
		}
	}
	}


