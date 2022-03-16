package com.qa.app.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	private static Workbook ExcelWbook;
	private static Sheet ExcelWsheet;
	
	
	//Set excel file to read data based on row and col
	public static void setExcelFile(String filePath, String sheetName) throws IOException {
			
		File file = null;
		FileInputStream fis = null;
		BufferedInputStream excelFile = null;
			
		try {
				
			file = new File(filePath);
			fis = new FileInputStream(file);
			excelFile = new BufferedInputStream(fis);
				
			ExcelWbook = WorkbookFactory.create(excelFile); //WorkbookFactory - Auto detects .xls and .xlsx
			ExcelWsheet = ExcelWbook.getSheet(sheetName);
				
		} catch(FileNotFoundException e) {
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
				
		} catch(IOException e) {
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
				
		} catch(Exception e) {
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
				
		} finally {
			excelFile.close();
		} 
			
	}
	
	/**
	 * This method is used to read excel based on column name. -> hashmapobj.get("colName")
	 * @param filePath
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	
	public static ArrayList<HashMap<String,String>> readExcelData() throws IOException {
		
		//Workbook ExcelWbook;
		//Sheet ExcelWsheet;
		Row row = null;
		Cell cell = null;
		String cellValue = null;
		
		//Each Excel Row is stored in one hashmap and then each hashmap is stored in Arraylist
		ArrayList<HashMap<String,String>> excelCompleteData = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> excelRowData = null;
		
		//File file = null;
		//FileInputStream fis = null;
		//BufferedInputStream excelFile = null;
		
		try {
			
			//file = new File(filePath);
			//fis = new FileInputStream(file);
			//excelFile = new BufferedInputStream(fis);							
			
			//ExcelWbook = WorkbookFactory.create(excelFile); //WorkbookFactory - Auto detects .xls and .xlsx
			//ExcelWsheet = ExcelWbook.getSheet(sheetName);
			
			int noOfRows = ExcelWsheet.getPhysicalNumberOfRows();
			int noOfCols = ExcelWsheet.getRow(0).getLastCellNum();
			
			
			
			for(int i = 1; i < noOfRows; i++) {
				
				row = ExcelWsheet.getRow(i);
				
				if (row == null)
					break;
				
				//Initialize Hashmap to store row data
				excelRowData = new HashMap<String, String>();
				
				for(int j = 0; j < noOfCols; j++) {
					
					//String colName = getCellData(0,j).toLowerCase();
					//String colName = getCellData(0,j); //This is header and using row number as 0 for each iteration in order to get the colName as key.
					
					cell = ExcelWsheet.getRow(0).getCell(j);
					String colName = cellToString(cell); //This is header and using row number as 0 for each iteration in order to get the colName as key.
					
					
					if(colName == null || colName.equals(""))
						break;
					
					//Add any condition in case colName is appended to or appended by something else
					
					cell = ExcelWsheet.getRow(i).getCell(j);
					
					if (cell != null) {
						//get cell value
						//cellValue = getCellData(i, j);
						//cellValue = cellToString(cell);
						cellValue = cellToString_New(cell);
						
					} else {
						cellValue = "";
					}
					
					//For each col - Add col name and cell value of one row to hash map (j = 0 to end of col)
					excelRowData.put(colName, cellValue);
					
				}
				
				//If excel is properlly formatted then directly we can add hashmap to array list
				//excelCompleteData.add(excelRowData);
				
				//In case excel is not properly formatted - Then this method may read data from row after last valid row - which we need to skip adding in ArrayList.
				boolean flag= false;
				
				for(String key : excelRowData.keySet()) 				{
					
					if(excelRowData.get(key) != "") {
						flag = true; //means data is found for row - not blank row
						break;
					}
						
				}
				
				if(flag) {
					excelCompleteData.add(excelRowData);
				}
				
				
			}			
			
		} catch(Exception e) {
			System.out.println("Could not read the excel sheet");
			e.printStackTrace();
			return excelCompleteData;
			
		} finally {
			//excelFile.close();
		}		
		
		return excelCompleteData;			
	}
	
	//Old way of implementing.. getCellType() is deprecated in newer version of POI
	public static String cellToString(Cell cell) { 
			
			// This function will convert an object of type excel cell to a string value
			
			int type = cell.getCellType();   
			
			Object result;
			
			switch (type) 
			{			
			case Cell.CELL_TYPE_NUMERIC: //0 
				
				if(DateUtil.isCellDateFormatted(cell)) {
					//converting date obj to string obj
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					result = date.format(cell.getDateCellValue()); 
				} else {
					result = (int)cell.getNumericCellValue();
				}				           
				break;                       
			case Cell.CELL_TYPE_STRING: //1 
				result = cell.getStringCellValue();                                
				break;    
			case Cell.CELL_TYPE_FORMULA: //2 
				//System.out.println(cell);
				result = cell.getStringCellValue(); 
				//throw new RuntimeException("We can't evaluate formulas in Java");  	
			case Cell.CELL_TYPE_BLANK: //3                                		
				result = "";                                		
				break;                            	
			case Cell.CELL_TYPE_BOOLEAN: //4     		
				result = cell.getBooleanCellValue();       		
				break;                            	
			case Cell.CELL_TYPE_ERROR: //5       		
				throw new RuntimeException ("This cell has an error");    	
			default:                  		
				throw new RuntimeException("We don't support this cell type: " + type); 
			}                        
			
			return result.toString();      
			
	}//end of cellToString
	
	//New way of implementing.. getCellTypeEnum() is used in newer version of POI
	public static String cellToString_New(Cell cell) { 
		
		// This function will check the type of excel and return corresponding value in String form
		
		CellType type = cell.getCellTypeEnum();   
		
		Object result;
		
		switch (type) 
		{			
		case NUMERIC:
			//result = (int)cell.getNumericCellValue();  
			
			if (DateUtil.isCellDateFormatted(cell)) {
				//Converting date into string  //yyyy-MM-dd --> 1986-10-14, MM/dd/yyyy --> 10/14/1986
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
	            //SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
	            result = dateFormat.format(cell.getDateCellValue());
	            //System.out.print(dateFormat.format(cell.getDateCellValue()) + "\t\t");
				} else {
	        	result = (int)cell.getNumericCellValue();  
	        	//System.out.print(cell.getNumericCellValue() + "\t\t");
        	}			
			break;                       
		case STRING: //1 
			result = cell.getStringCellValue();                                
			break;    
		case FORMULA: //2 
			result = cell.getStringCellValue();
			//throw new RuntimeException("We can't evaluate formulas in Java");  	
		case BLANK: //3                                		
			result = "";                                		
			break;                            	
		case BOOLEAN: //4     		
			result = cell.getBooleanCellValue();       		
			break;                            	
		case ERROR: //5       		
			throw new RuntimeException ("This cell has an error");    	
		default:                  		
			throw new RuntimeException("We don't support this cell type: " + type); 
		}                        
		
		return result.toString();      
		
	}
	
	
	/**
	 * This method is read the test data from the Excel cell
	 * @param rowNo
	 * @param cellNo
	 * @return cellData
	 */
	public static String getCellData(int rowNo, int cellNo) {
		
		try {
			
			Cell cell = ExcelWsheet.getRow(rowNo).getCell(cellNo);
			
			String cellData = cell.getStringCellValue();
			
			return cellData;
			
		}catch (Exception e) {
			
			return "";
			
		}			
		
	}

	//Read Excel in Array - index based	
	public static String[][] readXLData(String filePath, String sheetName) throws IOException {

		//initialize variables to create instances for an excel file
		//Workbook ExcelWBook = null;
		//Sheet ExcelWSheet = null;
		
		Row row = null;			
		Cell cell = null;
		String[][] vXLData = null;
		int noOfRows;
		int noOfCols;	
		String cellValue="";
		
		File file = null;		
		FileInputStream fis = null;
		BufferedInputStream excelFile = null;
		
		try {
			
			file = new File(filePath);		
			fis = new FileInputStream(file);
			excelFile = new BufferedInputStream(fis);
			
			ExcelWbook = WorkbookFactory.create(excelFile); //WorkbookFactory - Auto detects .xls and .xlsx
			ExcelWsheet = ExcelWbook.getSheet(sheetName);
		
			/*String fileExtension = filePath.substring(filePath.indexOf(".x"));
		
			if(fileExtension.equals(".xlsx")) {
			
				ExcelWbook = new XSSFWorkbook(excelFile);			
			
			} else if(fileExtension.equals(".xls")){
			
				ExcelWbook = new HSSFWorkbook(excelFile);
			
			} */
		
					//sheet = workbook.getSheet(fSheetName);	
		
			//Get the row and column counts of a work sheet
			//int noOfRows = ExcelWsheet.getLastRowNum()+1;
			noOfRows = ExcelWsheet.getPhysicalNumberOfRows();
			noOfCols = ExcelWsheet.getRow(0).getLastCellNum();
			
			//System.out.println("Total Rows: "+noOfRows+" "+" Total Column: "+noOfCols);
		
		
			//Initialize 2D array of type string with row and col counts -this will be used to store excel data in it.		
			vXLData = new String[noOfRows][noOfCols];
	
			//Use for loop to to read the excel data and store in vXLData array.
			for (int i=0; i< noOfRows; i++)
			{			
				row = ExcelWsheet.getRow(i);
				
				for(int j = 0;j < noOfCols; j++)
				{
					cell = row.getCell(j);
					
					if (cell!=null)
					{
						cellValue = cellToString_New(cell);
						//cellValue=cell.getStringCellValue().toString();
						
					}
					
					vXLData[i][j] = cellValue;
					//System.out.println(cellValue);
				}
			}	
		
		} catch (Exception e) {
			
		} finally {
			//workbook.close();
		}
		
		
		return vXLData; 		
		
	}

	public static int getRowCount() {
		
		int rowCount = 0;
		
		try {
			rowCount = ExcelWsheet.getPhysicalNumberOfRows();
			//rowCount = ExcelWsheet.getLastRowNum()+1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
		
	}

	public String[] getSheetNames(String filePath) throws IOException {
		
		Workbook workbook = null;		
		String[] sheetNames = null;
		
		File outFile = new File(filePath);
		
		//if file/workbook exist
		
		if(outFile.exists() == true && outFile.isFile() == true) {
			
			BufferedInputStream excelFile = new BufferedInputStream(new FileInputStream(outFile));
			workbook = new XSSFWorkbook(excelFile);
			sheetNames = new String[workbook.getNumberOfSheets()];
			
			for (int i = 0; i < sheetNames.length; i++) {
				sheetNames[i] = workbook.getSheetName(i); //using the workbook object-> get the sheet name based on index
			}
			
		}else {
			System.out.println("Excel file doesnot exist!!");
		}
		
		return sheetNames;	
		
	}
	
	
	public void writeExcelData(String filePath, String fSheetName, ArrayList<LinkedHashMap<String,String>> outPutResult) throws IOException {
		
		Workbook workbook = null;
		Sheet osheet = null;
		Row row = null;
		Cell cell = null;
		
		File outFile = new File(filePath);
		
		FileInputStream fis = null;
		
		//Step1: Create Workbook and sheet
		
		//If File/Workbook exist
		if(outFile.exists() == true && outFile.isFile() == true) {
			
			fis = new FileInputStream(outFile);
			workbook = new XSSFWorkbook(fis);
			
			boolean existSheet = false;
			
			//If sheet exist then remove sheet 1st and then create new sheet at the same index.
			for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
				
				String sheetName = workbook.getSheetName(i);
				
				if(sheetName.equalsIgnoreCase(fSheetName)) {
					workbook.removeSheetAt(i);
					osheet = workbook.createSheet(fSheetName);
					workbook.setSheetOrder(fSheetName, i);
					existSheet = true;
					break;
				}				
			}
			
			//If sheet doesnot exist then create new sheet at the end
			
			if(!existSheet) {
				osheet = workbook.createSheet(fSheetName); //creating sheet
				//workbook.setSheetOrder(fSheetName, 0); //Newly created sheet will be set at 1st tab
				workbook.setSheetOrder(fSheetName, workbook.getNumberOfSheets()-1); // if newly created sheet is 5th sheet then set it at 4th index
			}			
		
		// If File/Workbook doesnot exist then create new workbook and create new sheet
		} else if(outFile.exists() == false || outFile.isFile() == false) {
			
			workbook = new XSSFWorkbook();
			osheet = workbook.createSheet(fSheetName);
			
		}
		
		
		//Step2: set data to sheet with styling if needed
		//1-Styling
		
		
		
		//2-Write data and use styling as required
		
		int xRowCnt = outPutResult.size();
		HashMap<String, String> rowData = null;
		
		//iterate each row
		for(int i = 0; i < xRowCnt; i++) {
			
			row = osheet.createRow(i);
			rowData = outPutResult.get(i);
			
			//Iterate each column
			int colIndex = 0;
			for(String key : rowData.keySet()) {
				
				cell = row.createCell(colIndex);
				cell.setCellValue(rowData.get(key));
				
				colIndex = colIndex + 1;
			}
			
		}
		
		
		//Step3: Write/Flush data
		
		FileOutputStream fout = new FileOutputStream(outFile);
		workbook.write(fout);
		fout.flush();
		fout.close();
		
		
	}
	
	
	public void writeExcelData_withStyle(String filePath, String fSheetName, ArrayList<LinkedHashMap<String,String>> outPutResult) throws IOException {
		
		Workbook workbook = null;
		Sheet osheet = null;
		Row row = null;
		Cell cell = null;
		
		File outFile = new File(filePath);
		
		FileInputStream fis = null;
		
		//Step1: Create Workbook and sheet
		
		//If File/Workbook exist
		if(outFile.exists() == true && outFile.isFile() == true) {
			
			fis = new FileInputStream(outFile);
			workbook = new XSSFWorkbook(fis);
			
			boolean existSheet = false;
			
			//If sheet exist then remove sheet 1st and then create new sheet at the same index.
			for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
				
				String sheetName = workbook.getSheetName(i);
				
				if(sheetName.equalsIgnoreCase(fSheetName)) {
					workbook.removeSheetAt(i);
					osheet = workbook.createSheet(fSheetName);
					workbook.setSheetOrder(fSheetName, i);
					existSheet = true;
					break;
				}				
			}
			
			//If sheet doesnot exist then create new sheet at the end
			
			if(!existSheet) {
				osheet = workbook.createSheet(fSheetName); //creating sheet
				//workbook.setSheetOrder(fSheetName, 0); //Newly created sheet will be set at 1st tab
				workbook.setSheetOrder(fSheetName, workbook.getNumberOfSheets()-1); // if newly created sheet is 5th sheet then set it at 4th index
			}			
		
		// If File/Workbook doesnot exist then create new workbook and create new sheet
		} else if(outFile.exists() == false || outFile.isFile() == false) {
			
			workbook = new XSSFWorkbook();
			osheet = workbook.createSheet(fSheetName);
			
		}
		
		
		//Step2: set data to sheet with styling if needed
		//1-Styling
		CellStyle headerCellStyle = headerStyle(osheet);
		CellStyle bodyCellStyle = bodyStyle(osheet);
		CellStyle resultFailCellType = resultStyle_Fail(osheet);
		CellStyle resultWarningCellType = resultStyle_warning(osheet);
		
		//2-Write data and use styling as required
		
		int xRowCnt = outPutResult.size();
		HashMap<String, String> rowData = null;
		
		//iterate each row
		for(int i = 0; i < xRowCnt; i++) {
			
			row = osheet.createRow(i);
			rowData = outPutResult.get(i);
			
			//Iterate each column
			int colIndex = 0;
			for(String key : rowData.keySet()) {
				
				//Create cell and setcelldata with rowdata from hashmap
				cell = row.createCell(colIndex);
				cell.setCellValue(rowData.get(key));
				
				//Set column width for All project
				//osheet.setColumnWidth(colIndex, 6000);
				
				//Set column width if needed - Specific fields related to Test Result documents
				if(key.equalsIgnoreCase("FirstName") || key.equalsIgnoreCase("LastName")) {
					osheet.setColumnWidth(colIndex, 4000);
				} else if(key.equalsIgnoreCase("UserName")) {
					osheet.setColumnWidth(colIndex, 6000);
				} else {
					osheet.setColumnWidth(colIndex, 2000);
				}
				
				//Apply style to cell
				if(i == 0) { //header row
					cell.setCellStyle(headerCellStyle);
				} else {
					cell.setCellStyle(bodyCellStyle);
				}
				
				//Fail Field - Apply style to specific rows based on result
				if(key.equalsIgnoreCase("Result")) {
					if(rowData.get(key).equalsIgnoreCase("Fail")) {
						
						cell.setCellStyle(resultFailCellType);
						
					}
				}
				
				//Warning Field - Apply style to specific rows based on Warning
				if(key.equalsIgnoreCase("Result")) {
					if(rowData.get(key).equalsIgnoreCase("Warning")) {
						
						cell.setCellStyle(resultWarningCellType);
						
					}
				}
				
				colIndex = colIndex + 1;
			}
			
		}
		
		
		//Step3: Write/Flush data
		
		FileOutputStream fout = new FileOutputStream(outFile);
		workbook.write(fout);
		fout.flush();
		fout.close();
		
		
	}
	
	
	
	//Cell Style Methods
	
	public static CellStyle headerStyle(Sheet osheet) {
		
		CellStyle headerCellStyle = osheet.getWorkbook().createCellStyle();
		
		Font headerFont = osheet.getWorkbook().createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 10);
		
		//Headre Font setup with cellstyle object
		headerCellStyle.setFont(headerFont);
		
		//Header color
		headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//Header Border
		headerCellStyle.setBorderBottom(BorderStyle.DOUBLE);
		headerCellStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
		headerCellStyle.setBorderRight(BorderStyle.THIN);
		
		//Header Alignment
		headerCellStyle.setAlignment(HorizontalAlignment.LEFT);
		headerCellStyle.setWrapText(true);		
		
		return headerCellStyle;	
		
	}
	
	public static CellStyle bodyStyle(Sheet osheet) {
		
		CellStyle bodyCellStyle = osheet.getWorkbook().createCellStyle();
		
		Font bodyFont = osheet.getWorkbook().createFont();
		bodyFont.setBold(false);
		bodyFont.setFontHeightInPoints((short) 10);
		bodyFont.setFontName("Calibri");
		bodyFont.setColor(IndexedColors.BLUE.getIndex());
		
		//Body Font setup with cellstyle object
		bodyCellStyle.setFont(bodyFont);
		
		//Body color
		bodyCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		bodyCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//Body Border
		bodyCellStyle.setBorderBottom(BorderStyle.THIN);
		bodyCellStyle.setBorderTop(BorderStyle.THIN);
		bodyCellStyle.setBorderRight(BorderStyle.THIN);
		bodyCellStyle.setBorderLeft(BorderStyle.THIN);
		
		//Body Alignment
		bodyCellStyle.setAlignment(HorizontalAlignment.LEFT);
		bodyCellStyle.setWrapText(true);		
		
		return bodyCellStyle;	
		
	}
	
	public static CellStyle resultStyle_Fail(Sheet osheet) {
		
		CellStyle resultCellStyle = osheet.getWorkbook().createCellStyle();
		
		Font bodyFont = osheet.getWorkbook().createFont();
		bodyFont.setBold(false);
		bodyFont.setColor(IndexedColors.WHITE.getIndex());
				
		//Result Font setup with cellstyle object
		resultCellStyle.setFont(bodyFont);
		
		//Result color
		resultCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		resultCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//Body Border
		resultCellStyle.setBorderBottom(BorderStyle.THICK);
		resultCellStyle.setBorderTop(BorderStyle.THICK);
		resultCellStyle.setBorderRight(BorderStyle.THICK);
		resultCellStyle.setBorderLeft(BorderStyle.THICK);
		
		//Body Alignment
		resultCellStyle.setAlignment(HorizontalAlignment.LEFT);
		resultCellStyle.setWrapText(true);		
		
		return resultCellStyle;	
		
	}
	
	public static CellStyle resultStyle_warning(Sheet osheet) {
		
		CellStyle resultCellStyle = osheet.getWorkbook().createCellStyle();
		
		Font bodyFont = osheet.getWorkbook().createFont();
		bodyFont.setBold(false);
		//bodyFont.setColor(IndexedColors.WHITE.getIndex());
				
		//Result Font setup with cellstyle object
		resultCellStyle.setFont(bodyFont);
		
		//Result color
		resultCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		resultCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//Body Border
		resultCellStyle.setBorderBottom(BorderStyle.THIN);
		resultCellStyle.setBorderTop(BorderStyle.THIN);
		resultCellStyle.setBorderRight(BorderStyle.THIN);
		resultCellStyle.setBorderLeft(BorderStyle.THIN);
		
		//Body Alignment
		resultCellStyle.setAlignment(HorizontalAlignment.LEFT);
		resultCellStyle.setWrapText(true);		
		
		return resultCellStyle;	
		
	}
	
	
	//Find column Index
	
	public int findColunIndex(String[][] data, String colName) {
		
		int colIndex = -1;
		int colSize = data[0].length; //Column size of header which contains column name
		
		for(int i = 0; i < colSize; i++) {
			
			if(data[i][0].equalsIgnoreCase(colName)) {
				colIndex = i;
				break;
			}
				
		}
		
		return colIndex;
	}
	

	//Get Unique excel records - In case duplicate records present in excel
	
	public static ArrayList<HashMap<String,String>> getUniqueExcelRecords(ArrayList<HashMap<String,String>> readExcelRecords) {
		
		ArrayList<HashMap<String,String>> uniqueExcelRecords = null;
		
		//Set holds all hashmap and bring unique hashmaps
		Set<HashMap<String,String>> uniqueRecords = new HashSet<HashMap<String,String>>(readExcelRecords);
		
		//Converting set to Arraylist
		uniqueExcelRecords = new ArrayList<HashMap<String,String>>(uniqueRecords.size());
		
		for(HashMap<String,String> excelUniqueRecord : uniqueRecords) {
			uniqueExcelRecords.add(excelUniqueRecord);
		}		
		
		return uniqueExcelRecords;
	}



	
	
	private static void formatDateCell(XSSFWorkbook workbook, Cell cell) {
		CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
	}
	
	

}
