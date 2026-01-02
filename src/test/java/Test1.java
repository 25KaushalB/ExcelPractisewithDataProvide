import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 {

	DataFormatter format = new DataFormatter();
	@Test(dataProvider="Example")
	public void kaushal(String userName, String password, String id) {
		System.out.println(userName+password+id);
	}
	
	@DataProvider(name="Example")
	public Object[][] getData() throws IOException {
		//Object[][] data = {{"Kaushal","KaushalPass","159"},{"Hemali","HemaliPass","357"},{"Hetanksh","HetankshPass","852"}};
		//Object data[][] = new Object[3][3];
		//data[0][0] = "kaushal";
		//data[0][0] = "kaushalPass";
		//data[0][0] = "123";
		
		
		//To get this Same from Excel.
		//To Get Path / Location of Excel WorkBook
		FileInputStream fis = new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\KaushalExcelPractise\\Book1.xlsx"); 
		
		//To Open Excel Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//To Open Sheet of Excel Workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//To Get Number of Rows of the Opened Sheet.
		int rowCount = sheet.getPhysicalNumberOfRows();
		//This will get all Rows of Sheet.
		XSSFRow row= sheet.getRow(0);
		
		//This will get Last Column of Data Present in Row. 
		int colCount = sheet.getLastRowNum();
		
		
		//Create a Multi Dimensional ARRAY.
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i=0; i<rowCount-1; i++) {
			row = sheet.getRow(i+1);
			
			for (int j=0; j<colCount;j++) {
				XSSFCell cell = row.getCell(j);
				
				//This Format Object of Class DataFormatter of POI 
				//Apache Class will Convert Cell value into String DataType. 
				//out of Irrespective of DataType in it Present i.e. String, Int, Boolean etc etc.
				data[i][j] = format.formatCellValue(cell); 
			}
		}
		
		return data;
	}
}
