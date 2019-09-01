package com.hospital.util;

import com.hospital.entity.TableEntity;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2019/7/24.
 */
public class ExcelPoiUtils {

    public  static void read(){

        try {
            InputStream inputStream = new FileInputStream("F:\\大实训again文件\\7.24\\301项目正式导入模板(1).xlsx");

            XSSFWorkbook xssfWorkbook=new XSSFWorkbook(inputStream);

            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

            XSSFRow titleRow = sheetAt.getRow(2);



            for(int i=3;i<sheetAt.getLastRowNum();i++){
                Map<String,List<TableEntity>> mapTabelEntity=new HashMap();

                for(int x=0;x<sheetAt.getRow(i).getPhysicalNumberOfCells();x++){
                    String tabcol=titleRow.getCell(x).toString();

                    String table=tabcol.split(":")[0];

                    String column=tabcol.split(":")[tabcol.split(":").length -1];

                    TableEntity tableEntity=new TableEntity();

                    tableEntity.setColumn(column);

                    tableEntity.setValue(sheetAt.getRow(i).getCell(x).toString());

                    if(mapTabelEntity.get(table)==null){
                        List tableEntityList=new ArrayList();
                        tableEntityList.add(tableEntity);
                        mapTabelEntity.put(table, tableEntityList);
                    }else{
                        mapTabelEntity.get(table).add(tableEntity);
                    }
                }

                Set<String> mapKeySet = mapTabelEntity.keySet();

                Iterator<String> iterator = mapKeySet.iterator();

                while(iterator.hasNext()){
                    String key=iterator.next();

                    List<TableEntity> tableEntityList=mapTabelEntity.get(key);
                    String insertsql="insert into "+key;
                    String column ="(";
                    String value ="(";
                    for(int x=0;x<tableEntityList.size();x++){

                        column+=tableEntityList.get(x).getColumn()+",";

                        value+=tableEntityList.get(x).getValue()+",";
                    }
                    insertsql+=column+")"+" values "+value+")";
                    System.out.println(insertsql);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



    public static void main(String[] args) {
        read();
    }




}
