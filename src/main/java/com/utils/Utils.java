package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.base.TestBase;

public class Utils extends TestBase {

	static File file;
	static FileInputStream stream;
	static XSSFWorkbook workbook;

	public static String[][] FetchData(String sheetname) {

		file = new File("./src/main/java/com/testdata/TestData.xlsx");
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("Check file");
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getPhysicalNumberOfRows();

		int cells = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rows - 1][cells];

		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cells; j++) {

				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));

			}

		}

		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	public static void Windowhandless(String window) {
		Set<String> handles = driver.getWindowHandles();

		for (String hand : handles) {
			if (!window.equals(hand)) {
				driver.switchTo().window(hand);
				driver.manage().window().maximize();

			}
		}

	}
	
	
	
	

	public static void CapturescreenShot(String methodname) throws Throwable {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File tgt = new File("./shots/" + methodname + ".png");

		FileUtils.copyFile(src, tgt);

	}

}
