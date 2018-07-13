package com.elon.excel;

public class TestExcel {
    public static void main(String[] args) {
        try {
            ExcelReaderUtil excel = new ExcelReaderUtil();
            excel.readOneSheet("E:/temp/test.xlsx", 1);
            System.out.println(excel.getDataList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
