import java.sql.DriverManager

import org.apache.poi.ss.usermodel.WorkbookFactory

// Access database credentials from environment variables (replace with actual variable names)
def url = 'jdbc:mysql://sql6.freesqldatabase.com:3306/sql6694477'
def username = 'sql6694477'
def password = 'uEFfiBLXkH'

String host = 'sql6.freesqldatabase.com'

// Replace with the path to your Excel file (ensure it's accessible by Katalon)
def excelFilePath = 'DATABASE.xlsx'  // Example path, replace with your actual file..

def conn = null

try {
   //Connect to the database
  Class.forName('com.mysql.cj.jdbc.Driver')
  
  conn = DriverManager.getConnection(url, username, password)

  // Prepare the call to the stored procedure
  def insertQuery = "INSERT INTO Employee (NAME, AGE, ADDRESS, STATUS) VALUES (?, ?, ?, ?)"
  def callableStatement = conn.prepareCall(insertQuery)

  // Open the Excel file // the Poi Apache is used for the WorkbookFactory

  def workbook = WorkbookFactory.create(new File(excelFilePath))
  def sheet = workbook.getSheetAt(0)  // Assuming data is in the first sheet (index 0)

  // Loop through rows (skip the header row)
  for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	def row = sheet.getRow(i)

	// Extract data from cells (assuming first two columns contain data)

def data1 = row.getCell(0)?.getStringCellValue()
println data1
def data2 = row.getCell(1)?.getNumericCellValue()
println data2
def data3 = row.getCell(2)?.getStringCellValue()
def data4 = row.getCell(3)?.getNumericCellValue()

	if (data1 != null && data2 != null) {  // Skip rows with missing data
	  callableStatement.setString(1, data1)
	  callableStatement.setDouble(2, data2)
	  callableStatement.setString(3, data3)
	  callableStatement.setDouble(4, data4)
	  callableStatement.execute()
	}
  }

  println "Data inserted successfully from Excel!"

} catch (Exception e) {
  println "Error inserting data: ${e.message}"

}
