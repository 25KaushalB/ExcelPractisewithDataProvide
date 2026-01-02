import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	public static void main(String[] args) throws IOException {
		
	}

	public ArrayList getExcelData(String testCasesName) throws IOException {

		// TODO Auto-generated method stub

		// Step1: Identify TestCases Column (1st Column) by Scanning the Entire Rows of the Sheet.
		// Step2: Once Column is Identified the Scan Entire Row of TestCases to Find  Purchase Row.
		// Step3: After you Grab Purchase TestCase Row = pull all the Data of that Row and Feed it Into Test.

		// Code to Access Excel File.
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\Driven\\Excel.xlsx"); // This will Go to Excel Sheet.

		XSSFWorkbook workbook = new XSSFWorkbook(fis); //This will Open Excel Sheet.
		ArrayList a = new ArrayList();
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Test")) {
				// This will open Excel Sheet and Go to the Required Sheet.
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				// Step1: Identify TestCases Column (1st Column) by Scanning the Entire Rows of the Sheet.
				Iterator<Row> rows = sheet.iterator(); // Sheet is Collection of Rows.
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator(); // Rows is Collections of Cells.
				int k=0;
				int column=0;
				while (cells.hasNext()) {
					Cell cellValue = cells.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) {
						// Step2: Once Column is Identified the Scan Entire Row of TestCases to Find Purchase Row.
						column=k;
										
					}
					k++;
				}
				System.out.println(column);
				
				// Step2: Once Column is Identified the Scan Entire Row of TestCases to find Purchase Row.
				while (rows.hasNext()) {
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCasesName)) {
						
						
						// Step3: After you Grab Purchase TestCase Row = pull all the Data of that Row and Feed it Into Test.
						Iterator<Cell> cr = r.cellIterator();
						while(cr.hasNext()) 
						{
							Cell c = cr.next();
							if (c.getCellType()==CellType.STRING) {
							a.add(c.getStringCellValue());
							}
							else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
					
						
					}
				}
			}
		return a;
	}
}