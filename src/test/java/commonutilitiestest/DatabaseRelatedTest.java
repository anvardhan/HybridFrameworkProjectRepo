package commonutilitiestest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.qa.app.utils.CommonUtilities;
import com.qa.app.utils.DbUtilities;


public class DatabaseRelatedTest {
	
	
	//JBDC driver --> com.mysql.cj.jdbc.Driver
	//connection url/ Database url --> dbc:mysql://localhost:3306/qa2qe
	
	//If we dont use mysql-connector-java-8.0.21.jar as external lib or dependencies then below error is expected
	//java.sql.SQLException: No suitable driver found for dbc:mysql://localhost:3306/qa2qe at java.sql.DriverManager.getConnection(Unknown Source)
	
	
	//Below exception is coming even if above jar provided
	//Error: java.lang.ClassNotFoundException: jdbc:mysql://localhost:3306/qa2qe
	//java.lang.ClassNotFoundException: jdbc:mysql://localhost:3306/qa2qe
	
	//The java.lang.ClassNotFoundException is a checked exception in Java that occurs when the JVM tries to load a particular class but does not find it in the classpath.
	//Since the ClassNotFoundException is a checked exception, 
	//it must be explicitly handled in methods which can throw this exception 
	//- either by using a try-catch block or by throwing it using the throws clause.
	
	@Test (enabled=false)
	public void readDataFromDatabase() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
		
		//Build sql to be executed to retrieve data from database.
		String sql = "select * from student";
		
		Connection con = null;	
		ResultSet rs = null;
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
			
			//Execute query and get data from database - Create preparedstatement and execute sql query
			rs = DbUtilities.getDataFromDB(con, sql);
		
			//Validation:
			System.out.println(rs.absolute(1)); 
			//absolute - Moves the cursor to the given row number in this ResultSet object. 
			//If the row number is positive, the cursor moves to the given row number with respect to the beginning of the result set. 
			//The first row is row 1, the second is row 2, and so on. 
	
			//Get the total records in the table		
			/*boolean b = rs.last(); 
			int count = rs.getRow(); 
			rs.beforeFirst(); */			
			int count1 = DbUtilities.getTotalRowCountFromDb(rs);					
			System.out.println("Total records in the table: "+count1);
			
			//getting the record from 3rd row  
			/*rs.absolute(3);  //set cursor to row number 3
			System.out.println(rs.getInt("idstudent"));
			System.out.println(rs.getString("StudentFirstName"));
			System.out.println(rs.getString("StudentLastName"));
			System.out.println(rs.getString("StudentDOB")); */
			
			//get records of all row
			while(rs.next()) {
				//System.out.println(rs.getInt(1));
				System.out.println(rs.getInt("idstudent"));
				int id = rs.getInt("idstudent");
				System.out.println(id);
				System.out.println(rs.getString("StudentFirstName"));
				System.out.println(rs.getString("StudentLastName"));
				System.out.println(rs.getString("StudentDOB"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}
		
		
	}
	
	@Test (enabled=false)
	public void readDataFromDatabase_WithComplexSQLAndParameter() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
		
		//Build sql to be executed to retrieve data from database.
		
		int studentId = 12; //This can come from Test Data
		
		String sql = "select idStudent, StudentFirstName, StudentAge, StudentDOB, StudentUserName " + 
				"from student where idStudent >= "+studentId+";";
		
		Connection con = null;	
		ResultSet rs = null;
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
			
			//Execute query and get data from database - Create preparedstatement and execute sql query
			rs = DbUtilities.getDataFromDB(con, sql);
		
			//Validation:			
			//Get the total records in the table						
			int count = DbUtilities.getTotalRowCountFromDb(rs);					
			System.out.println("Total records in the table: "+count);
			
			//get records of all row
			while(rs.next()) {
				//System.out.println(rs.getInt(1));
				System.out.print(rs.getInt("idstudent")+" ");
				System.out.print(rs.getString("StudentFirstName")+" ");
				System.out.print(rs.getInt("StudentAge")+" ");
				System.out.print(rs.getString("StudentDOB")+" ");
				System.out.print(rs.getString("StudentUserName"));
				System.out.println();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}
		
	}
	
	@Test (enabled=false)
	public void workingWithResultSetMetadata() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
				
		//Build sql to be executed to retrieve data from database.
				
		int studentId = 12; //This can come from Test Data
				
		String sql = "select * from student where idStudent >= "+studentId+";";
				
		Connection con = null;	
		ResultSet rs = null;
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
					
			//Execute query and get data from database - Create preparedstatement and execute sql query
			rs = DbUtilities.getDataFromDB(con, sql);
			
			//Get Column count, Column Names, Table name			
			int columnCnt = DbUtilities.getColumnCountFromTable(rs);
			ArrayList<Object> columnNames = DbUtilities.getColumnName(rs);
			String tableName = DbUtilities.getTableName(rs);
			
			
			//Validation:		
			
			System.out.println("tableName: "+tableName);
			System.out.println("columnCnt: "+columnCnt);
			
			
			for(Object colName : columnNames) {
				System.out.println("colName: "+colName);
			}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				rs.close();
			}	
		
		
	}
	
	
	
	@Test (enabled=false)
	public void updateDataIntoDatabase() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
		
		//update query
			
		String sql = "update student set StudentUserName = 'TestUser12' where idStudent = 12";
		
		Connection con = null;	
	
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
			
			//Execute query and update database - Create preparedstatement and execute sql update query
			DbUtilities.updateDB(con, sql);
		
			//Validation:			

		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}		
	}
	
	@Test (enabled=false , description = "Run this with delete query")
	public void insertDataIntoDatabase() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
				
		//Below data can come from Test data sheet
		int stuId = 24;
		String stuFName = "Sameer";
		String stuLName = "Roy";
		int stuAge = 31;
		String stuUName = "Sameer31";
			
		//insert query
		//String sql = "insert into Student(idStudent,StudentFirstName,StudentLastName," + 
		//		"StudentAge, StudentUserName) values (20,'Srikanth','Roy', 30, 'Srikanth30');";
		
		String sql = "insert into Student(idStudent,StudentFirstName,StudentLastName," + 
				"StudentAge, StudentUserName) values ("+stuId+",'"+stuFName+"','"+stuLName+"', "+stuAge+", '"+stuUName+"');";
		
		Connection con = null;	
		ResultSet rs = null;
		
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
			
			//Execute query and update database - Create preparedstatement and execute sql update query
			DbUtilities.updateDB(con, sql);
		
			//Validation:			
			//Data inserted or not - Fetch data from DB and Assert
			
			sql = "select * from Student";
			rs = DbUtilities.getDataFromDB(con, sql);
			
			boolean flag= false;
			while(rs.next()) {
				int actualId = rs.getInt("idStudent");
				if(actualId == stuId) {
					flag = true;
					break;
				}
				
			}
			
			if(flag) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}		
	} 
	
	@Test (enabled=false, description = "Run this with insert query")
	public void deleteDataFromDatabase() throws SQLException {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
				
		//Below data can come from Test data sheet
		int stuId = 24;
					
		//delete query
		String sql = "delete from Student where idStudent = "+stuId;
		
		Connection con = null;	
		ResultSet rs = null;
		
		try {
			//Establish connection with database - Create connection obj - load driver and establish connection;
			con = DbUtilities.getConnection(jdbcDriver, connectionUrl, userName, password);
			
			//Execute query and update database - Create preparedstatement and execute sql update query
			DbUtilities.updateDB(con, sql);
		
			//Validation:			
			//Data deleted or not - Fetch data from DB and Assert
			
			sql = "select * from Student";
			rs = DbUtilities.getDataFromDB(con, sql);
			
			boolean flag= false; //data deleted
			while(rs.next()) {
				int actualId = rs.getInt("idStudent");
				if(actualId == stuId) {
					flag = true; //data found means not deleted
					break;
				}
				
			}
			
			if(!flag) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}		
	}
	
	@Test(enabled = false, description = "Excute .script file having multiple sql query")
	public void executeSqlScriptTest() {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
		
		Connection con = null;  
        
		/*Read DatabaseScript.sql and split with ; and store all sql's in a 
        array of type String and use for loop to execute one statement at a time.
        String s is used to read all lines form script and Stringbuffer sb gets all lines.
        After that split with ; and stores in sqlArray String array and execute one by one*/
		
		String str = new String();
        StringBuffer sb = new StringBuffer();
		
        try
        {
            FileReader fr = new FileReader(new File(".\\src\\main\\resources\\DatabaseScript.sql"));
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
	
	@Test(enabled = false, description = "Excute .script file having multiple sql query")
	public void executeSqlScriptTest_UsingReusableUtility() {
		
		//Get below info from config.properties file
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String connectionUrl = "jdbc:mysql://localhost:3306/qa2qe";
		String userName = "root";
		String password = "rootpassword";
		String scriptFile = ".\\src\\main\\resources\\DatabaseScript.sql";
		
		DbUtilities.executeSqlScript(jdbcDriver, connectionUrl, userName, password, scriptFile);
		
	}
	
	@Test(enabled=false)
	public void exportDataFromDB_ForAnyTable() {
		
		//String tableName = "Student"; //Its under Qa2QE schema
		String tableName = "Address";
		
		
		String excelFilePath = ".\\outputResult\\ExportFromDb\\"+CommonUtilities.getFileName(tableName.concat("_Export"))+".xlsx";
		String sql = "SELECT * FROM ".concat(tableName);
		
		try {
			DbUtilities.exportDBDataToExcel(tableName, excelFilePath, sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Content of Table '"+tableName+"' is exported to Excel");
	}




}
