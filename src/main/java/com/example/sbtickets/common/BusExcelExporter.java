package com.example.sbtickets.common;
import com.example.sbtickets.entity.Bus;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
public class BusExcelExporter {
    // khai bao cac class xu ly voi excel
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Bus> listBus;

    public BusExcelExporter(List<Bus> listDriver) {
        this.listBus = listBus;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Bus");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Bus ID", style);
        createCell(row, 1, "Car Number", style);
        createCell(row, 2, "Color", style);
        createCell(row, 3, "Manufacturer", style);
        createCell(row, 5, "Life Car", style);
        createCell(row, 6, "Number Seats", style);
        createCell(row, 7, "Year Use", style);
        createCell(row, 8, "Date Mantain", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if(value instanceof Date){
            cell.setCellValue((Date) value);
        }
        else {
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

        for (Bus bus : listBus) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, bus.getId(), style);
            createCell(row, columnCount++, bus.getCarNumber(), style);
            createCell(row, columnCount++, bus.getColor(), style);
            createCell(row, columnCount++, bus.getManufacturer(), style);
            createCell(row, columnCount++, bus.getLifeCar(), style);
            createCell(row, columnCount++, bus.getNumberSeats(), style);
            createCell(row, columnCount++, bus.getYearUse(), style);
            createCell(row, columnCount++, bus.getDateMantain(), style);
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
