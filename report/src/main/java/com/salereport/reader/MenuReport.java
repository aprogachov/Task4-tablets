package com.salereport.reader;

import com.salereport.mapper.TransactionSaleMapper;
import com.salereport.service.ITransactionSaleReportService;
import com.modelsale.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuReport {

    private String dateFormat = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    @Autowired
    private TransactionSaleMapper transactionSaleMapper;
    @Autowired
    private ITransactionSaleReportService iTransactionSaleReportService;

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n" + "Hey, our MenuReport: " +
                    "\n" + "1. excelReport" +
                    "\n" + "0. Exit");

            System.out.println("\n" + "Your choice: ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    newReport();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    System.out.println("Done");
    }

    void newReport() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Print dateFrom: ");
            String dateStringFrom = input.nextLine();
            Date dateFrom = simpleDateFormat.parse(dateStringFrom);

            System.out.println("Print dateBefore: ");
            String dateStringBefore = input.nextLine();
            Date dateBefore = simpleDateFormat.parse(dateStringBefore);

            List<Transaction> listSaleTransaction = transactionSaleMapper.getTransactions(dateFrom, dateBefore);
            iTransactionSaleReportService.excelReport(listSaleTransaction);
        } catch (ParseException pe) {
            pe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
