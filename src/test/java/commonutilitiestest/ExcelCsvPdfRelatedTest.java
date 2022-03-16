package commonutilitiestest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.qa.app.utils.ExcelUtilities;
import com.qa.app.utils.PDF_CSV_Utilities;

public class ExcelCsvPdfRelatedTest {
	
	
	
	//Excel Utilities Validation
	@Test (enabled = false)
	public void readExcelData_readExcelData_UseArrayList() throws IOException {
		
		//ExcelUtilities excelUtils = new ExcelUtilities();
		
		String filePath = ".\\src\\main\\test\\TestData.xlsx";
		String sheetName = "UserData";
		
		
		ExcelUtilities.setExcelFile(filePath, sheetName);
		ArrayList<HashMap<String,String>> readExcelData = ExcelUtilities.readExcelData();
		
		System.out.println(readExcelData.size()); //Total Rows
		
		//Read data
		
		//ArrayList<HashMap<String,String>> excelCompleteData = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> excelRowData = null;
		
		for (int i = 0; i < readExcelData.size(); i++) {
			
			excelRowData = readExcelData.get(i);
			//excelRowData = excelCompleteData.get(i); //Get each row one at a time
			
			String firstName = excelRowData.get("Id");
			String lastName = excelRowData.get("Age");
			String dateOfBirth = excelRowData.get("LastName");
			
			System.out.println(firstName+" "+lastName+" "+dateOfBirth);			
			
		}	
		
	}
	
	@Test (enabled = false)
	public void readExcelDataBasedOnRowCol() throws IOException {
		
		//ExcelUtilities excelUtils = new ExcelUtilities();
		
		String filePath = ".\\src\\main\\test\\TestData.xlsx";
		String sheetName = "UserData";
		
		ExcelUtilities.setExcelFile(filePath, sheetName);
		String cellValue = ExcelUtilities.getCellData(2, 4); //4 - indexno
		
		System.out.println(cellValue);
		
			
	}
	
	@Test (enabled = false)
	public void readExcelData_readXLData_Use2DArray() throws IOException {
		
		//ExcelUtilities excelUtils = new ExcelUtilities();
		
		String filePath = ".\\src\\main\\test\\TestData.xlsx";
		String sheetName = "UserData";
		
		String[][] readXLData = ExcelUtilities.readXLData(filePath, sheetName);
		
		System.out.println(readXLData.length); //Total Rows
		
		//Read data
		
		for (int i = 1; i < readXLData.length; i++) { //read only data so start with 1
			
			String firstName = readXLData[i][1];
			String lastName = readXLData[i][2];
			String dateOfBirth = readXLData[i][6];
			
			System.out.println(firstName+" "+lastName+" "+dateOfBirth);			
			
		}	
		
	}
	
	@Test (enabled=false)
	public void ExcelOperation() throws IOException {
		
		ExcelUtilities excelUtils = new ExcelUtilities();
		
		String filePath = ".\\src\\main\\test\\TestData.xlsx";
		String sheetName = "UserData";
		
		ExcelUtilities.setExcelFile(filePath, sheetName);
		int rowCount = ExcelUtilities.getRowCount();
		
		System.out.println("Row Count"+ " - " +rowCount);
		
		
		String[] sheetNames = excelUtils.getSheetNames(filePath);
		
		for (int i = 0; i < sheetNames.length; i++) {
			
			System.out.println(sheetNames[i]);
			
		}		
		
	}
	
	@Test (enabled = false)
	public void writeExcelData() throws IOException {
		
		ExcelUtilities excelUtils = new ExcelUtilities();
		
		String inputFilePath = ".\\src\\main\\test\\TestData.xlsx";
		String fSheetName = "UserData";
		
		String outputFilePath = ".\\src\\main\\resources\\TestData_Result.xlsx";
		String outSheetName = "UserData_Result4";
		
		//Read
		ExcelUtilities.setExcelFile(inputFilePath, fSheetName);
		ArrayList<HashMap<String, String>> readExcelData = ExcelUtilities.readExcelData();
		HashMap<String, String> rowData = null;
		
		
		//Write
		ArrayList<LinkedHashMap<String, String>> outPutResult = new ArrayList<LinkedHashMap<String,String>>();
		LinkedHashMap<String, String> testResult = null;
		
		testResult = new LinkedHashMap<String, String>();
		testResult.put("UserId", "UserId");
		testResult.put("FirstName", "FirstName");
		testResult.put("LastName", "LastName");
		testResult.put("UserName", "UserName");
		testResult.put("Result", "Result");
		
		outPutResult.add(testResult);
		
		for(int i = 0 ; i < readExcelData.size(); i++) {
			
			if (i == 2) {
				rowData = readExcelData.get(i);
				
				String userId = rowData.get("UserId");
				String firstName = rowData.get("FirstName");
				String lastName = rowData.get("LastName");
				String userName = rowData.get("UserName");
				//String dob = rowData.get("DOB");
						
				
				testResult = new LinkedHashMap<String, String>();
				
				testResult.put("UserId", userId);
				testResult.put("FirstName", firstName);
				testResult.put("LastName", lastName);
				testResult.put("UserName", userName);
				testResult.put("Result", "Fail"); //depends on validation
				
				outPutResult.add(testResult);
				
				continue;
			}
			
			if (i == 4) {
				rowData = readExcelData.get(i);
				
				String userId = rowData.get("UserId");
				String firstName = rowData.get("FirstName");
				String lastName = rowData.get("LastName");
				String userName = rowData.get("UserName");
				//String dob = rowData.get("DOB");
						
				
				testResult = new LinkedHashMap<String, String>();
				
				testResult.put("UserId", userId);
				testResult.put("FirstName", firstName);
				testResult.put("LastName", lastName);
				testResult.put("UserName", userName);
				testResult.put("Result", "Warning"); //depends on validation
				
				outPutResult.add(testResult);
				
				continue;
			}		
			
			//Pass
			rowData = readExcelData.get(i);
			
			String userId = rowData.get("UserId");
			String firstName = rowData.get("FirstName");
			String lastName = rowData.get("LastName");
			String userName = rowData.get("UserName");
			//String dob = rowData.get("DOB");
					
			
			testResult = new LinkedHashMap<String, String>();
			
			testResult.put("UserId", userId);
			testResult.put("FirstName", firstName);
			testResult.put("LastName", lastName);
			testResult.put("UserName", userName);
			testResult.put("Result", "Pass"); //depends on validation
			
			outPutResult.add(testResult);		
			
			
		}		
		
		//excelUtils.writeExcelData(outputFilePath, outSheetName, outPutResult);
		excelUtils.writeExcelData_withStyle(outputFilePath, outSheetName, outPutResult);
		
		System.out.println("Data written to Excel!!!");
		
	}	
	
	
	
	//CSV Utilities Validation
	@Test(enabled=false)
	public void generateCSVFromExcel() throws IOException {
		
		String fileName = "TestData.xlsx";
		
		String filePath = ".\\src\\main\\test\\TestData.xlsx";
		String sheetName = "UserData";
		String[] csvFileName = fileName.split("\\.");
		
		String csvoutPath = ".\\src\\main\\resources\\"+csvFileName[0]+"_"+sheetName+".csv";
		
		PDF_CSV_Utilities.writeExcelToCsv(filePath, sheetName, csvoutPath);
		
		System.out.println("CSV file generated successfully!!!");
		
	}
	
	@Test(enabled = false)
	public void readCSVFile() throws IOException {
		
		String fPath = ".\\src\\main\\test\\TestData_UserData.csv";

		ArrayList<Map<String, String>> csvRecords = PDF_CSV_Utilities.readCsvFile(fPath);
		
		int totalCountInCsv = csvRecords.size();
		
		System.out.println("totalCountInCsv: "+totalCountInCsv);
		
		Map<String,String> csvEachRow = null;
		
		for(int i = 0; i < csvRecords.size(); i++) {
			
			csvEachRow = csvRecords.get(i);
			
			System.out.print(csvEachRow.get("Id")+" ");
			System.out.print(csvEachRow.get("Age")+" ");
			System.out.print(csvEachRow.get("PhoneNumber")+" ");
			System.out.print(csvEachRow.get("LastName")+" ");
			System.out.println(csvEachRow.get("Status"));
			
		}	
	}
	
	//pdfbox
	@Test(enabled = false)
	public void readPdfFile_FromLocal() throws IOException {
		
		String pdfUrl = "file:///C:/Users/Anand/Downloads/UserGuide.pdf";
		
		String pdfContent = PDF_CSV_Utilities.readPdf_FromLocal(pdfUrl);
		
		System.out.println("pdfContent: \n"+pdfContent);
		
		//System.out.println(pdfContent);
		Assert.assertTrue(pdfContent.contains(".pdf"));
		Assert.assertTrue(pdfContent.contains("User Guide"));
		Assert.assertTrue(pdfContent.contains("What is aXmag"));
		Assert.assertTrue(pdfContent.contains("info@axmag.com"));
		Assert.assertTrue(pdfContent.contains("Please refer to the link below for more information"));
		Assert.assertTrue(pdfContent.contains("http://learn.adobe.com/wiki/download/attachments/52658564/acrobat_reader9_flash_security.pdf?version=1"));
		
	}

	
	//tika - Parse different types of documents - pdf, word, xml, image, text, jar,etc 
	
	@Test(enabled=false)
	public void readPdfFile_FromLocal_tika() throws IOException, SAXException, TikaException {
		
		String fPdfPath = "C:\\Users\\Anand\\Downloads\\UserGuide.pdf";
		
		String pdfContent = PDF_CSV_Utilities.getPdfContent_FromLocal_Tika(fPdfPath);
		System.out.println("Contents of the PDF :" + pdfContent);
	      
	    //getting metadata of the document
	    System.out.println("Metadata of the PDF:");
	    
	   HashMap<String, String> metaDataContent = PDF_CSV_Utilities.getPdfMetaData_FromLocal_Tika(fPdfPath);
	   
	   Set<String> keys = metaDataContent.keySet();
	    
	    //String[] metadataNames = metadata.names();
	    
	    for(String name : keys) {
	         System.out.println(name+ " : " + metaDataContent.get(name));
	    }
		
	}
	
	
	
	
	/*Implement when driver used - Pdfbox
	@Test (enabled=false)
	public void PdfValidation_axmag_UserGuides() throws IOException, InterruptedException {		
		
		//Open Website and click on pdf download and validate pdf content and close pdf window.
		
		driver.get("https://www.axmag.com/");
		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[text()='Download']")).click();
		driver.findElement(By.xpath("(//*[text()='Learn more'])[2]")).click(); //Learn More is download link, when we lick, pdf file will open
		
		Set<String> allWindow = driver.getWindowHandles();
		
		for(String eachWindow : allWindow) {
			
			if(eachWindow.equals(parentWindow)) {
				continue;
			}
			
			driver.switchTo().window(eachWindow);
			
			String currentUrl = driver.getCurrentUrl();
			
			URL TestURL = new URL(currentUrl);

			InputStream is = TestURL.openStream();
			BufferedInputStream TestFileParse = new BufferedInputStream(is);
			PDDocument document = null;
			
			document = PDDocument.load(TestFileParse);
			
			PDFTextStripper pdftextstripper = new PDFTextStripper();		
			String pdfContent = pdftextstripper.getText(document);
			
			System.out.println("pdfContent: \n"+pdfContent);
			
			//System.out.println(pdfContent);
			Assert.assertTrue(pdfContent.contains(".pdf"));
			Assert.assertTrue(pdfContent.contains("User Guide"));
			Assert.assertTrue(pdfContent.contains("What is aXmag"));
			Assert.assertTrue(pdfContent.contains("info@axmag.com"));
			Assert.assertTrue(pdfContent.contains("Please refer to the link below for more information"));
			Assert.assertTrue(pdfContent.contains("http://learn.adobe.com/wiki/download/attachments/52658564/acrobat_reader9_flash_security.pdf?version=1"));
			
			driver.close();
			
		}
		
		driver.switchTo().window(parentWindow);
		driver.close();
	}
	*/

}
