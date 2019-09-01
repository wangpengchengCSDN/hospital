package com.hospital.excel;

import com.hospital.util.ReadExcelUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/7/24.
 */
public class ImportExcelForSql {

    public static void main(String[] args) throws IOException {

        List<String> strings =  ReadExcelUtils.readExcel();
        System.out.println(strings);
    }
}
