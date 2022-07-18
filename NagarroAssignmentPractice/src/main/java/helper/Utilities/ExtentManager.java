package helper.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {

	private static ExtentReports extent;
	private static String reportFileName = "Automation-Test-Result" + ".html";
	//private static String fileSeperator = System.getProperty("file.separator");
	private static String dir_report = System.getProperty("user.dir") + System.getProperty("file.separator") + "TestResultReport";
	private static String testReportPath = dir_report + System.getProperty("file.separator") + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	// Create an extent report instance
	public static ExtentReports createInstance() {
		String fileName = getReportPath(dir_report);

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		//htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(reportFileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Product", "MobileApp and RestAPIs and WebApp");

		return extent;
	}

	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
				return testReportPath;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return testReportPath;
	}

}
