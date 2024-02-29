package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
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

	public static void javaScriptClick(WebElement webelement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webelement);

	}

	public static void javaScriptScroll(WebElement webelement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", webelement);

	}

	public static void scrollDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("windows.scrollBy(0,150)");

	}

	public static void CapturescreenShot(String methodname) throws Throwable {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File tgt = new File("./shots/" + methodname + ".png");

		FileUtils.copyFile(src, tgt);

	}

	public static void writeInToNewExcel(String ORDERID) {

		// Create a workbook
		try (Workbook workbook = new XSSFWorkbook()) {
			// Create a sheet
			Sheet sheet = workbook.createSheet("Order IDs");

			// Create a row
			Row row = sheet.createRow(0);

			// Create a cell and set its value
			Cell cell = row.createCell(0);
			cell.setCellValue("Order ID");

			// Create another row for data
			Row dataRow = sheet.createRow(1);

			// Create a cell for data and set its value
			Cell dataCell = dataRow.createCell(0);
			dataCell.setCellValue(ORDERID);

			// Write the workbook to a file
			try (FileOutputStream outputStream = new FileOutputStream("./src/main/java/com/testdata/demo.xlsx")) {
				workbook.write(outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void WriteInExistingExcel(String ORDERID, String sheetName, int cellNum) {

		String filePath = "./src/main/java/com/testdata/TestData.xlsx";
		// Sheet name
		// String sheetName = "Sheet1";
		// Order ID obtained from Selenium
		// String orderId = "Your_Order_ID_Goes_Here";

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(filePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheet(sheetName);

			// Find the next empty row in the sheet
			// int rowCount = sheet.getLastRowNum() + 1;
			Row row = sheet.getRow(1);

			// Write the order ID to the given cell of the row
			Cell cell = row.createCell(cellNum);
			cell.setCellValue(ORDERID);

			// Write the updated workbook back to the file
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			workbook.close();

			System.out.println("Order ID added successfully to the Excel file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
