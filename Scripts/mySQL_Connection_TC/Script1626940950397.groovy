import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.sql.Connection as Connection
import java.sql.ResultSet as ResultSet
import java.sql.ResultSetMetaData as ResultSetMetaData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

String host = 'sql6.freesqldatabase.com'

String usrname = 'sql6692690'

String port = '3306'

String pwd = 'rH9JPqXRIh'

String dbname = 'sql6692690'

Connection conn = CustomKeywords.'DB_Connection.db_connection'(host, dbname, port, usrname, pwd) 

//println "${NAME} - ${AGE} - ${ADDRESS} - ${STATUS}"

def a = GlobalVariable.NAME

String queryInsert = "INSERT INTO Employee (NAME, AGE, ADDRESS, STATUS) VALUES (nhi, 3, abc, 3)"


CustomKeywords.'DB_Connection.executeUpdate'(queryInsert)


String query = 'SELECT * FROM Employee'

ResultSet rs = CustomKeywords.'DB_Connection.executeQuery'(query)

ResultSetMetaData rsm = rs.getMetaData()

int count = rsm.getColumnCount()

while(rs.next()) {
	for (int i = 1; i <= count; i++) {
		System.out.println(rs.getString(i) + ' ')
	}
	System.out.println()
}

//CustomKeywords.'DB_Connection.closeDatabaseConnection'()

