package com.hospital.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lola on 2019/7/16 20:02.
 */
public class ReadExcelUtils {
    private static Workbook wb;

    static {
        try {
            wb  =  new XSSFWorkbook(new FileInputStream("F:\\大实训again文件\\7.24\\301项目正式导入模板(1).xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DecimalFormat df = new DecimalFormat("0");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DecimalFormat nf = new DecimalFormat("0.00");
    public static List<String> readExcel() throws IOException {
        Integer t=null;
        List<List<Object>> list = new LinkedList<List<Object>>();
        Sheet sheet = wb.getSheetAt(0);//为第一页
        Object value = null;
        Row row = null;
        Cell cell = null;
        int counter = 0;
        int cellNum = 0;
        int f = 1;
        if(t != null && t == 1){
            f = 0;
        }
        for (int i = sheet.getFirstRowNum(),e = sheet.getPhysicalNumberOfRows(); counter < e; i++) {
            row = sheet.getRow(i);
            int cellNo = 0;
            if (row != null){
                cellNo = row.getPhysicalNumberOfCells();
            }
            if (row == null) {
                continue;
            } else if(cellNo <= f){
                counter++;
                continue;
            }else {
                counter++;
            }
            if(i == 0){
                cellNum = row.getLastCellNum();
            }

            List<Object> linked = new LinkedList<Object>();
            for (int j = 0; j < cellNum; j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    linked.add(null);
                    continue;
                }

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        /*if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = nf.format(cell.getNumericCellValue());
                        } else {
                            value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                        }*/
                        if(DateUtil.isCellDateFormatted(cell)){
                            value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                        }else{
                            double val=cell.getNumericCellValue();
                            long intVal=(long) val;
                            value=val-intVal==0?String.valueOf(intVal):String.valueOf(val);
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                }
                if (j==0&& StringUtils.isEmpty(value==null?null:value.toString())) {
                    break;
                }
                linked.add(value);
            }
            if(linked.size()==0) break;
            list.add(linked);
        }
        if(list == null || list.size()<=0){
            return null;
        }
        list.remove(0);
        List<String> list1 = new ArrayList<>();
        for (List<Object> l:list) {
            String remove = l.remove(1).toString();
            list1.add(remove);
        }
        return list1;

    }
}
