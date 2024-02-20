package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.base.TestBase;

public class Utils extends TestBase {

	static File file;
	static FileInputStream stream;
	static XSSFWorkbook workbook;

	public static String[][] FetchData(String sheetname) throws Throwable {

		file = new File("./src/main/java/com/testdata/TestData.xlsx");
		stream = new FileInputStream(file);
		workbook = new XSSFWorkbook(stream);

		XSSFSheet sheet = workbook.getSheet("logincred");
		int rows = sheet.getPhysicalNumberOfRows();

		int cells = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rows - 1][cells];

		for (int i = 0; i < rows - 1; i++) {
			for (int j = 0; j < cells; j++) {

				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
				System.out.println(data[i][j]);

			}
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
}
