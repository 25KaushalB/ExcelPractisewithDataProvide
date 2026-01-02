import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String fruitName = "Mango";
		String fileName = "C:\\Users\\HP\\Downloads\\download.xlsx";
		String updatedValue = "599";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/upload-download-test/");

		// Download
		driver.findElement(By.id("downloadButton")).click();
		String PriceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");

		String beforeUploadPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + PriceColumn + "-undefined']")).getText();
		System.out.println(beforeUploadPrice);
		Thread.sleep(5000l);
		// Edit Downloaded Item i.e.e Excel

		int column = getColumnNumber(fileName, "price");
		int Row = getRowNumber(fileName, fruitName);
		updateCell(fileName, Row, column, updatedValue);

		// Upload
		WebElement upload = driver.findElement(By.id("fileinput"));
		upload.sendKeys(fileName);

		// Verify Success Pop-up and

		By ToastMessage = By.xpath("//div[@class='Toastify__toast-body']/div[2]");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ToastMessage));
		String actualToastMessage = driver.findElement(ToastMessage).getText();
		Assert.assertEquals("Updated Excel Data Successfully.", actualToastMessage);
		System.out.println(actualToastMessage);
		// Wait for Pop-up Disappear.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ToastMessage));

		// Verify Edited changes in Web.
		String afterUploadPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + PriceColumn + "-undefined']")).getText();
		Assert.assertEquals(updatedValue, afterUploadPrice);
		System.out.println(afterUploadPrice);
		driver.quit();
	}

	private static boolean updateCell(String fileName, int row, int column, String updatedValue) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowField = sheet.getRow(row-1);
		Cell cellField = rowField.getCell(column-1);
		cellField.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;

	}

	private static int getRowNumber(String fileName, String rowName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowIndex = -1;
		while (rows.hasNext()) {
			Row r = rows.next();
			Iterator<Cell> ce = r.cellIterator();
			while (ce.hasNext()) {
				Cell cell = ce.next();
				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(rowName)) {
					rowIndex = k;
				}
			}
			k++;
		}
		return rowIndex;
	}

	private static int getColumnNumber(String fileName, String columnName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cell = firstRow.cellIterator();
		int k = 1;
		int column = 0;

		while (cell.hasNext()) {
			Cell Value = cell.next();
			if (Value.getStringCellValue().equalsIgnoreCase(columnName)) {
				column = k;
			}
			k++;
		}

		System.out.println(column);
		return column;
	}

}
