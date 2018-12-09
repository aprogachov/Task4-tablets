package com.salereport.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salereport.mapper.TransactionSaleMapper;
import com.modelsale.model.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class TransactionSaleReportService implements ITransactionSaleReportService {

    @Override
    public void excelReport(List<Transaction> listSaleTransaction) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("SalesTransaction");   //создаем лист

        Row titleRow = sheet.createRow(0);   //создаем строку заголовка
        Cell idCellTitle = titleRow.createCell(0);   //ячейка заголовка для id
        idCellTitle.setCellValue("Id");
        Cell dateCellTitle = titleRow.createCell(1);   //ячейка заголовка для date
        dateCellTitle.setCellValue("Date");
        Cell patientIdCellTitle = titleRow.createCell(2);   //ячейка заголовка для patientId
        patientIdCellTitle.setCellValue("Patient");
        Cell productIdCellTitle = titleRow.createCell(3);   //ячейка заголовка для productId
        productIdCellTitle.setCellValue("Product");

        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.MM.yyyy"));

        for (int i = 0; i < listSaleTransaction.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Transaction transaction = listSaleTransaction.get(i);

            Cell idCell = row.createCell(0);
            idCell.setCellValue(transaction.getId());

            Cell dateCell = row.createCell(1);
            dateCell.setCellStyle(dateStyle);
            dateCell.setCellValue(transaction.getDateTransaction());

            Cell patientCell = row.createCell(2);
            patientCell.setCellValue("Phone: " + transaction.getPatient().getPhone() +
                    "; State: " + transaction.getPatient().getState().getName());

            Cell productCell = row.createCell(3);
            productCell.setCellValue("Product: " + transaction.getProduct().getName() +
                    "; State: " + transaction.getProduct().getState().getName());
        }

        //автоматически менять размер столбцов, кроме Id
        for (int x = 1; x < sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
            sheet.autoSizeColumn(x);
        }

        //записываем в файл
        File file = new File("ExcelReport/TransactionSaleReport.xlsx");
//        File parent = file.getParentFile();
//        if (!parent.exists() && !parent.mkdirs()) {
//            throw new IllegalStateException("Couldn't create dir: " + parent);
//        }
//        parent.mkdirs();   //create parent directories if not exist
        file.getParentFile().mkdirs();   //create parent directories if not exist
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }
}
