package extentreports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.Utils;

public class ExtentReporter implements IReporter {
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator + "Extent.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				String description = result.getMethod().getDescription();
				if (description != null && !description.isEmpty()) {
					test.setDescription(description);
				}

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				if (status == LogStatus.FAIL) {
					String methodName = result.getMethod().getMethodName();
					String screenshotPath = "./shots/" + methodName + ".png";
					File screenshot = new File(screenshotPath);

					if (screenshot.exists()) {
						String encodedImage = encodeImageToBase64(screenshot);
						test.log(LogStatus.FAIL, "Screenshot below: " + test.addBase64ScreenShot(encodedImage));
					}
				}

				extent.endTest(test);
			}
		}
	}

	// Method to encode image to Base64
	private String encodeImageToBase64(File imageFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(imageFile);
			byte[] bytes = new byte[(int) imageFile.length()];
			fileInputStream.read(bytes);
			return "data:image/png;base64," + Base64.encodeBase64String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}