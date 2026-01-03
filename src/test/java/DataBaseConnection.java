import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataBaseConnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String host = "localhost"; // By Default Host if Not Set While Installation.
		String port = "3306"; // By Default Port if Not Set While Installation.
		
		//URL Syntax = "jdbc:mysql://"+host+":"+port+"/databasename";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/qaseleniumtest", "root",
				"root");

		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from employeeInfo where name = 'Hetanksh'");
		while (rs.next()) {
		System.out.println(rs.getString("name"));
		System.out.println(rs.getInt("id"));
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/?locale=in");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(rs.getString("name"));
		driver.findElement(By.id("password")).sendKeys(Integer.toString(rs.getInt("id")));
		}
		
		//int Password = rs.getInt("id"); //This 2 Lines of Code is Written in Line no 31 as SendKeys Take String value to insert.
		//Integer.toString(Password);

		
		
	}

}
