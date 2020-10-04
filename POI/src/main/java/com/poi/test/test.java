package com.poi.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {




    @Test
        public void Test() throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook(); //创建03使用的xls对象

        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("test第一个文件");

        FileOutputStream out = new FileOutputStream("E:\\01.xls");
        workbook.write(out);
        out.close();


    }

    @Test
    public void Test2() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(); //创建07使用的xls对象

        XSSFSheet sheet = workbook.createSheet("Test");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("test第一个文件");

        FileOutputStream out = new FileOutputStream("E:\\02.xlsx");
        workbook.write(out);
        out.close();


    }

}
