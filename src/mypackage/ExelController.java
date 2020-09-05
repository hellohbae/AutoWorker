package mypackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ExelController {
	private String file_name;
	
	ExelController(String file_name){
		this.file_name = file_name;
	}
	
	public XSSFWorkbook getAllExel() {
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(file_name);
			workbook = new XSSFWorkbook(fis);
		}catch (IOException e) {
            e.printStackTrace();
		}
		/*
		int sheets = workbook.getNumberOfSheets();
        for(int sheetindex=0; sheetindex<sheets; sheetindex++) {
        	XSSFSheet sheet = workbook.getSheetAt(sheetindex);
        	int rows = sheet.getPhysicalNumberOfRows();
	        for(int rowindex=0;rowindex<rows;rowindex++){           
	            XSSFRow row=sheet.getRow(rowindex);
	            if(row !=null){               
	                int cells=row.getPhysicalNumberOfCells();
	                for(int columnindex=0; columnindex<=cells; columnindex++){                   
	                    XSSFCell cell=row.getCell(columnindex);
	                    String value="";                    
	                    if(cell==null){
	                        continue;
	                    }else{                        
	                        switch (cell.getCellType()){
	                        case FORMULA:
	                            value=cell.getCellFormula();
	                            break;
	                        case NUMERIC:
	                            value=cell.getNumericCellValue()+"";
	                            break;
	                        case STRING:
	                            value=cell.getStringCellValue()+"";
	                            break;
	                        case BLANK:
	                            value=cell.getBooleanCellValue()+"";
	                            break;
	                        case ERROR:
	                            value=cell.getErrorCellValue()+"";
	                            break;
	                        }
	                    }
	                    System.out.println(value);
	                }
	
	            }
			}
        }*/
        
        return workbook;
	}
	
	public void setExelWithJson(String sheet_name) {
		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheet_name);
			XSSFRow curRow = sheet.createRow(0);
			Cell cell = curRow.createCell(0);
			cell.setCellValue("아무거나");
			
			workbook.write(fos);
			fos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	public void updateExelWithJson(String sheet_name, ArrayList<ArrayList<JSONObject>> jsonarray, String row_field_key, String column_field_key, String content_key) {
		XSSFWorkbook workbook = this.getAllExel();
		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			XSSFSheet sheet = workbook.createSheet((workbook.getNumberOfSheets()+1) + "." + sheet_name);
			
			XSSFRow curRow = sheet.createRow(0);
			Cell cell = curRow.createCell(0);
			String tbl_title = (String) jsonarray.get(0).get(0).get("PRD_DE") + " - " + (String) jsonarray.get(0).get(0).get("C1_NM");
			cell.setCellValue(tbl_title);
			
			String target_key = column_field_key;
			curRow = sheet.createRow(1);
			for(int col_idx=0; col_idx<jsonarray.size(); col_idx++) {
				cell = curRow.createCell(col_idx+1);
				cell.setCellValue((String) jsonarray.get(col_idx).get(0).get(target_key));
			}
			
			target_key = row_field_key;
			for(int row_idx=0; row_idx<jsonarray.get(0).size(); row_idx++) {
				curRow = sheet.createRow(row_idx+2);
				cell = curRow.createCell(0);
				cell.setCellValue((String) jsonarray.get(0).get(row_idx).get(target_key));
			}
			
			for(int col_idx=0; col_idx<jsonarray.size(); col_idx++) {
				for(int row_idx=0; row_idx<jsonarray.get(col_idx).size(); row_idx++) {
					curRow = sheet.getRow(row_idx+2);
					JSONObject cell_object = jsonarray.get(col_idx).get(row_idx);
					cell = curRow.createCell(col_idx+1);
					cell.setCellValue((String)cell_object.get(content_key));
				}
			}
			
			workbook.write(fos);
			fos.close();
		}catch (IOException e) {
            e.printStackTrace();
		}
	}
}
