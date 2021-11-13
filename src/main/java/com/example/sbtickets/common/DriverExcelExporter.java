package com.example.sbtickets.common;

import com.example.sbtickets.entity.Driver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DriverExcelExporter {
    // khai bao cac class xu ly voi excel
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Driver> listDriver;

    public DriverExcelExporter(List<Driver> listDriver) {
        this.listDriver = listDriver;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Driver");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Driver ID", style);
        createCell(row, 1, "CMT", style);
        createCell(row, 2, "Full Name", style);
        createCell(row, 3, "Code License", style);
        createCell(row, 5, "Type License", style);
        createCell(row, 6, "Address", style);
        createCell(row, 7, "BirthDay", style);
        createCell(row, 8, "Senority", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Driver driver : listDriver) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, driver.getId(), style);
            createCell(row, columnCount++, driver.getNationalId(), style);
            createCell(row, columnCount++, driver.getName(), style);
            createCell(row, columnCount++, driver.getCodeLicense(), style);
            createCell(row, columnCount++, driver.getTypeLicense(), style);
            createCell(row, columnCount++, driver.getAddress(), style);
            createCell(row, columnCount++, driver.getDob(), style);
            createCell(row, columnCount++, driver.getSeniority(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
