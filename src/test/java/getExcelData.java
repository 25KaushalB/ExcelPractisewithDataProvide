import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getExcelData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\Driven\\Excel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("Scenario")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				sheet.iterator();
			}
		}
	}

}
