package com.qa.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DbUtilities {
	
	/**
	 * 
	 * The Java.sql package that comes with JDK, contains various classes with their behaviors defined 
	 * and their actual implementations are done in third-party drivers. 
	 * Third party vendors implements the java.sql.Driver interface in their database driver.
	 * 
	 * Type 4: 100% Pure Java � Thin Driver
		In a Type 4 driver, a pure Java-based driver communicates directly with the vendor's database 
		through socket connection. 
		This is the highest performance driver available for the database and is usually provided by the vendor itself.
		This kind of driver is extremely flexible, we don't need to install special software on the client or server. 
		Further, these drivers can be downloaded dynamically.

	 * 
	 * Because of the proprietary nature of their network protocols, database vendors usually supply type 4 drivers.
	 * 
	 * The thin driver converts JDBC calls directly into the vendor-specific database protocol. 
	 * That is why it is known as thin driver. It is fully written in Java language.
	 * 
	 * 
	 * For MYSQL - MySQL's Connector/J driver is a Type 4 driver. 
	 * 
	 * Read page 9-12 for detailed steps
	 * 1 - Import package java.sql.*
	 * 2 - Load the JDBC driver - Class.forName is used to load JDBC driver
	 * 3 - Establish connection to database using DriverManager.getConnection() - provide database/connection url,un and pwd
	 * 4 - Create the Statement object (createStatement()) - This is used to create statements and submits sql query with database
	 * 5 - Execute query (executeQuery()) to the database and stores result
	 * 6 - close the connection object
	 * 7 - Get the jar/dependencies from vendor used to connect java application with vendor database	 * 
	 * Note � To connect java application with the MySQL database, mysql-connector-java-8.0.15.jar file is required to be loaded. 
	 * Similarly jar/dependencies is required for other database (DB2, Oracle, SQL Server, etc) 
	 */
	
	//JDBC Driver 
	//DB2 - com.ibm.db2.jcc.DB2Driver
	//MySql - com.mysql.cj.jdbc.Driver
	//Oracle - oracle.jdbc.driver.OracleDriver
			
	//Connection url (DB2) / Database url -Format -> jdbc:mysql://hostname:portnumber/ databaseName
	//DB2 --> jdbc:db2://xmpsdb2w1q:60000/DMPSQ003  --> used in Client project - Hostname and Database name will change
	//MySQL --> jdbc:mysql://localhost:3306/qa2qe --> used in Personal project - Hostname and Database name will change
	//Oracle --> jdbc:oracle:thin:@hostname:port Number:databaseName	
			
	//Username and password
	
	
	/**
	 * This method is used to load JDBC driver and establish connection with database.
	 * @param strConnectionUrl
	 * @param strUserName
	 * @param strPassword
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection(String jdbcDriver, String strConnectionUrl, String strUserName, String strPassword) throws SQLException {
		
		//Load driver - Loading driver is not required its optional
		//because The JDBC driver manager ensures that the correct driver is used to access each data.
		//This class manages a list of database drivers. 
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Class.forName(jdbcDriver).newInstance();
		} catch (Exception e) {
			System.out.println("Error in loading jdbc driver: "+e.toString());
			e.printStackTrace();
		} 
		
		Connection con = null; //import from java.sql
		
		//Create connection with Database
		try {
			//DriverManager class - This class manages a list of database drivers
			con = DriverManager.getConnection(strConnectionUrl, strUserName, strPassword);
			return con;
					
		} catch (Exception e) {
			System.out.println("Error in getConnection: "+e.toString());
			e.printStackTrace();
			return con;
		} 
		
	}
	

	/**
	 * This method is used execute sql query and stores result
	 * @param con
	 * @param sql
	 * @return ResultSet object rs which stores database result based on sql query
	 */
	public static ResultSet getDataFromDB(Connection con, String sql) {
		
		ResultSet rs = null;
		
		PreparedStatement pstmt = null;
		
		//Submit sql query to database, execute query and store results
		try {
			
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	/**
	 * This method will update database - insert, update, delete, etc sql queries can be executed
	 * @param con
	 * @param sql
	 */
	public static void updateDB(Connection con, String sql) {
		
		PreparedStatement pstmt = null;
		
		//Submit sql query to database and execute update queryto update database - insert, modify, delete as per sql
		try {
			
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.executeUpdate(); //This will update database
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}	
	
	public static int getTotalRowCountFromDb(ResultSet rs) throws SQLException {
		
		//boolean b = rs.last(); 
		int count = rs.getRow(); 
		rs.beforeFirst();
		
		return count;
		
	}
	
	
	public static int getColumnCountFromTable(ResultSet rs) throws SQLException {
		
		ResultSetMetaData rsmd = rs.getMetaData();		
		int totalColumn = rsmd.getColumnCount();		
		//System.out.println("totalColumn: "+totalColumn);		
		return totalColumn;
	}
	
	public static ArrayList<Object> getColumnName(ResultSet rs) throws SQLException {
		
		ResultSetMetaData rsmd = rs.getMetaData();		
		int totalColumn = rsmd.getColumnCount();

		//Store column name in a ArrayList and return Array list
		ArrayList<Object> columnNames = new ArrayList<Object>();
		
		for (int i = 1 ; i <= totalColumn; i++) {
				//System.out.println(rsmd.getColumnLabel(i));
				//System.out.println(rsmd.getColumnName(i));
				//System.out.println(rsmd.getColumnType(i));
				//System.out.println(rsmd.getTableName(i));	
			
			columnNames.add(rsmd.getColumnName(i));
		}	
		
		return columnNames;		
	}
	
	public static String getTableName(ResultSet rs) throws SQLException {
		
		ResultSetMetaData rsmd = rs.getMetaData();		
		
		return rsmd.getTableName(1); //useful in Join query result set, pass column index and get table name
				
	}
	
	
	
	public static void executeSqlScript(String jdbcDriver, String connectionUrl, String userName, String password, 
			String scriptFile) {
		
		
		Connection con = null;  
		
		/*Read DatabaseScript.sql and split with ; and store all sql's in a 
        array of type String and use for loop to execute one statement at a time.
        String s is used to read all lines form script and Stringbuffer sb gets all lines.
        After that split with ; and stores in sqlArray String array and execute one by one*/
		
		String str = new String();
        StringBuffer sb = new StringBuffer();
		
        try
        {
            FileReader fr = new FileReader(new File(scriptFile));
            // be sure to not have line starting with "--" or "/*" or any other non alphabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((str = br.readLine()) != null)
            {
                sb.append(str); //Each line in script (.sql file) is ending with ;
            }
            
            br.close();
 
            // here We will use ";" as a delimiter for each request
            String[] sqlArray = sb.toString().split(";");
 
            
            //Establish database connection             
            con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
            con.setAutoCommit(false);
            
           /*Execute each sql query from sql Array - Requires using an object of type Statement for 
            building and submitting an SQL statement to do sql activity in a selected database.
            Here Get statement instance and Execute a script with multiple sql like Create Database if not exist,
            Create Table if not exist and insert data into it and so on.*/
           
            for(int i = 0; i<sqlArray.length; i++)
            {
                // ******we ensure that there is no spaces before or after the request string*********
                // in order to not execute empty statements
                if(!sqlArray[i].trim().equals(""))
                {
                    String sql=sqlArray[i];
                  //System.out.println(">>"+sql);
                    DbUtilities.updateDB(con, sql);    
                }
            }
            con.commit(); //Commit all changes if successful. To do this 1st set setAutoCommit false
            System.out.println("Database updated");
         } catch(Exception e) {
            System.out.println("*** Error : "+e.toString());
            e.printStackTrace();
         } finally {
      	      try {
      	         if(con!=null)
      	            con.close();
      	      } catch(SQLException se){
      	         se.printStackTrace();
      	      }
        } 		
		
	}
	



	//Export entire DB data based on table name - Just pass table name and extract data for any table - Below 5 methods used
	public static void exportDBDataToExcel(String tableName, String excelFilePath, String sql) throws ClassNotFoundException, SQLException {
		        
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
				
		//Get data from database
		Connection con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);        
	    ResultSet rs = DbUtilities.getDataFromDB(con, sql);
		         
	    try {
	       	XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet(tableName); //Making sheet name as table name
		 
	        writeHeaderLineAdvanced(rs, sheet); 
	        writeDataLinesAdvanced(rs, workbook, sheet);
		 
	        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	        workbook.write(outputStream);
	        outputStream.flush();
	        outputStream.close();
	        workbook.close();
		 
	    } catch (IOException e) {
		    System.out.println("File IO error:");
		    e.printStackTrace();
		}
		        
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeHeaderLineAdvanced(ResultSet rs, XSSFSheet sheet) throws SQLException {
		
		// write header line containing column names
        ResultSetMetaData metaData = rs.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
 
        Row headerRow = sheet.createRow(0);
 
        // Start from first column therefore i=1
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(columnName);
        }
	}
	
	private static void writeDataLinesAdvanced(ResultSet rs, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
		
		ResultSetMetaData metaData = rs.getMetaData();
        int numberOfColumns = metaData.getColumnCount();
 
        int rowCount = 1;
 
        while (rs.next()) {
            Row row = sheet.createRow(rowCount++);
 
            for (int i = 1; i <= numberOfColumns; i++) {
                Object valueObject = rs.getObject(i); //get data
 
                Cell cell = row.createCell(i - 1);
                
                if (valueObject instanceof Integer)
                    cell.setCellValue((Integer) valueObject);
                else if (valueObject instanceof Boolean)
                    cell.setCellValue((Boolean) valueObject);
                else if (valueObject instanceof Double)
                    cell.setCellValue((double) valueObject);
                else if (valueObject instanceof Float)
                    cell.setCellValue((float) valueObject);
                else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatDateCell(workbook, cell);
                } else cell.setCellValue((String) valueObject);
 
            }
 
        }
	}

	private static void formatDateCell(XSSFWorkbook workbook, Cell cell) {
		CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
	}


	


}
