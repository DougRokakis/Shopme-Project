package com.shopme.admin.user.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shopme.admin.AbstractExporter;
import com.shopme.common.entity.User;

//CLASS/METHODS FOR BUILDING EXCEL FILE (API'S UTILIZED FROM APACHE POI EXCEL LIBRARY: XSSFWORKBOOK, XSSFSHEET, CELL)
public class UserExcelExporter extends AbstractExporter{
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	//CONSTRUCTOR
	public UserExcelExporter() {
		workbook = new XSSFWorkbook();
	}
	//METHOD FOR WRITING HEADER LINE OF EXCEL FILE
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");
		XSSFRow row = sheet.createRow(0);
		
		//CREATES CELL STYLE FONT TO BOLD AND A HEIGHT OF 16
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);
		
		//CREATES ROW HEADERS
		createCell(row, 0, "User Id", cellStyle);
		createCell(row, 1, "E-Mail", cellStyle);
		createCell(row, 2, "First Name", cellStyle);
		createCell(row, 3, "Last Name", cellStyle);
		createCell(row, 4, "Roles", cellStyle);
		createCell(row, 5, "Enabled", cellStyle);

	}
	
	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);
		
		//CONDITIONAL STATEMENTS CASTING VALUES ACCORDING TO TYPE
		if(value instanceof Integer) {
		cell.setCellValue((Integer) value);
		}else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);			
		}else {
			cell.setCellValue((String) value);
		}
		
		cell.setCellStyle(style);
	}
	
	//METHOD FOR EXPORTING EXCEL FILE
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		//FROM ABSTRACT EXPORTER. WITH PARAMETERS PROVIDED, METHOD BUILDS THE NAME OF FILE, SETS THE CONTENT TYPE AND HEADER
		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "users_");

		writeHeaderLine();
		writeDataLines(listUsers);
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	//METHOD FOR FILLING IN INFORMATION FOR COLUMNS
	private void writeDataLines(List<User> listUsers) {
		int rowIndex = 1;
		
		//ESTABLISHING FONT STYLE FOR COLUMNS
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(14);
		cellStyle.setFont(font);
		
		//LOOPING THROUGH listUsers AND MAPPING DATA TO CELL ROWS
		for(User user : listUsers) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			
			createCell(row, columnIndex++, user.getId(), cellStyle);
			createCell(row, columnIndex++, user.getEmail(), cellStyle);
			createCell(row, columnIndex++, user.getFirstName(), cellStyle);
			createCell(row, columnIndex++, user.getLastName(), cellStyle);
			createCell(row, columnIndex++, user.getRoles().toString(), cellStyle);
			createCell(row, columnIndex++, user.isEnabled(), cellStyle);
		}
	}
}
	