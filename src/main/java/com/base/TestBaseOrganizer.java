package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBaseOrganizer {

	FileInputStream file;
	public Properties prop;
	public static WebDriver driver;

	public TestBaseOrganizer() {

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

		if (browsername.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browsername.equalsIgnoreCase("Edge")) {

			System.setProperty("webdriver.edge.driver", "./src/main/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		else if (browsername.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.driver", "./src/main/resources/drivers/geckodriver.exe");
			driver = new EdgeDriver();
		}

		else {

			System.out.println("Enter proper browser name");
		}
		driver.manage().window().maximize();
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("OrganizerUrl"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

}
