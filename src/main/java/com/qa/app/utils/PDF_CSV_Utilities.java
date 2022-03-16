package com.qa.app.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class PDF_CSV_Utilities {
	
	//Excel to csv converter
	public static void writeExcelToCsv(String filePath, String sheetName, String csvPath) throws IOException {
		
		File file = new File(csvPath); //Note - DO NOT use excelfilePath
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter pw = new PrintWriter(fos);
		
		String[][] excelData = ExcelUtilities.readXLData(filePath, sheetName);
		//ExcelUtilities.setExcelFile(filePath, sheetName);
		//ArrayList<HashMap<String,String>> readExcelData = ExcelUtilities.readExcelData();
				
		for(int i = 0; i < excelData.length; i++) {
			
			String rowLine = "";
			
			for(int j = 0; j < excelData[0].length; j++) {
				
				rowLine = rowLine.concat(excelData[i][j]).concat(",");
				
			}
			
			rowLine = rowLine.substring(0,rowLine.length()-1);
			pw.println(rowLine);
			
		}
		
		pw.close();
		
		System.out.println("CSV file is written");
		
	}
	
	//Csv parsing
	public static ArrayList<Map<String, String>> readCsvFile(String fPath) throws IOException {
		
		//Read CSV
		
		ArrayList<Map<String,Integer>> extractData = new ArrayList<Map<String, Integer>>();
		ArrayList<Map<String,String>> csvRecords = new ArrayList<Map<String, String>>(); //hold csv records - 1 csvMap - 1 record
				
		try (
				Reader reader = Files.newBufferedReader(Paths.get(fPath));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
						.withTrim());
		    ) {
			
			//get header names - header name of csv
			Map<String,Integer> headerMap = csvParser.getHeaderMap();
			extractData.add(headerMap);
			
			for (CSVRecord csvRecord : csvParser) {
				//Accessing values by header name
				
				Map<String,String> csvMap = new HashMap<>(); //csvMap will hold csv record (1 record) in key value format
				
				//Iterate each column in csv dile
				for(Map.Entry<String, Integer> header : headerMap.entrySet()) {
					
					csvMap.put(header.getKey(), csvRecord.get(header.getValue()));					
					
				}
				
				if(!csvMap.isEmpty()) {
					csvRecords.add(csvMap);
				}
					
			}
		}
		
		return csvRecords;
		
	}
	
	
	//pdf parse using pdf box
	public static String readPdf_FromLocal(String pdfUrl) throws IOException {
		
		//Read PDF from local system
		URL TestURL = new URL(pdfUrl);
	

		InputStream is = TestURL.openStream();
		BufferedInputStream TestFileParse = new BufferedInputStream(is);
		PDDocument document = null;
		
		document = PDDocument.load(TestFileParse);
		
		PDFTextStripper pdftextstripper = new PDFTextStripper();		
		String pdfContent = pdftextstripper.getText(document);
		
		
		return pdfContent;		
	}


	//pdf parse using tika app
	
	public static String getPdfContent_FromLocal_Tika(String fPdfPath) throws IOException, SAXException, TikaException {
		
		BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    FileInputStream inputstream = new FileInputStream(new File(fPdfPath));
	    ParseContext pcontext = new ParseContext();
	      
	    //parsing the document using PDF parser
	    PDFParser pdfparser = new PDFParser(); 
	    pdfparser.parse(inputstream, handler, metadata,pcontext);
		
		String pdfContent = handler.toString();
		
		return pdfContent;		
		
	}
	
	public static HashMap<String, String> getPdfMetaData_FromLocal_Tika(String fPdfPath) throws IOException, SAXException, TikaException {
		
		BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    FileInputStream inputstream = new FileInputStream(new File(fPdfPath));
	    ParseContext pcontext = new ParseContext();
	      
	    //parsing the document using PDF parser
	    PDFParser pdfparser = new PDFParser(); 
	    pdfparser.parse(inputstream, handler, metadata,pcontext);
				
		//getting metadata of the document
	   
	    String[] metadataNames = metadata.names();
	    
	    HashMap<String, String> metaDataMap = new HashMap<String, String>();
	    
	    for(String name : metadataNames) {
	    	//System.out.println(name+ " : " + metadata.get(name));	    	
	    	metaDataMap.put(name, metadata.get(name));	
	    }
	    return metaDataMap;		
	}
	
	
}
