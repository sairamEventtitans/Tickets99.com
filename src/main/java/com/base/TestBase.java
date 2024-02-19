package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	FileInputStream file;
	public Properties prop;
	public WebDriver driver;

	public TestBase() {

		prop = new Properties();
		try {
			file = new FileInputStream("./src/main/java/configprop/Propertiesfile");
		} catch (FileNotFoundException e) {

			System.out.println("File error check");
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Initialization() {

		String browsername = prop.getProperty("browser");

		if (browsername.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./target/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else {

			System.out.println("Enter proper browser name");
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

}
