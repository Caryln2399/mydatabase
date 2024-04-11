import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.Statement
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class DB_Connection {

	private static Connection connection = null

	@Keyword
	def db_connection(String url, String dbname, String port, String username, String pwd) {

		String con = "jdbc:mysql://" + url + ":" + port + "/" + dbname

		if(connection != null && !connection.isClosed()) {
			connection.closed()
		}

		connection = DriverManager.getConnection(con, username, pwd)

		return connection
	}

	@Keyword
	def executeQuery(String query) {
		Statement stm = connection.createStatement()
		ResultSet resultset = stm.executeQuery(query)
		return resultset
	}

	@Keyword
	def closeDatabaseConnection() {
		if(connection != null && !connection.isClosed()) {
			connection.close()
		}
		connection = null
	}

	@Keyword
	def execute(String queryString) {
		Statement stm = connection.createStatement()
		boolean result = stm.execute(queryString)
		return result
	}

	@Keyword
	def executeUpdate(String queryString) {
		Statement stm = connection.createStatement()
		stm.executeUpdate(queryString)
	}
}

