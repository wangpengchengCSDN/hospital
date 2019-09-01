package com.hospital.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * 
 * @author csh
 * 
 */
public class ExcelUtil {
	
	private static DecimalFormat df = new DecimalFormat("0");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DecimalFormat nf = new DecimalFormat("0.00");

	public static List<List<String>> readExcel(String filePath){
		
		Workbook wb=null;
		InputStream is=null;
		List<List<String>> list=new ArrayList<List<String>>();
		try{
			// 通过流，将文件读取进来
			is=new FileInputStream(filePath);
			// 将文件的后缀名切割出来
			String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
			// 将文件后缀名转化为大写和字符串进行对比
	        if("XLSX".equals(fileType.toUpperCase())){
	        	// 将文件流放进 XSSFWorkbook 中
	        	wb = new XSSFWorkbook(is);
	        }else if("XLS".equals(fileType.toUpperCase())){
	            wb = new HSSFWorkbook(is);
	        }else{
	        	//return new Result(0, "文件类型错误，导入失败");
	        }
	        // 获取第一页Excel
	        Sheet sheet=wb.getSheetAt(0);
	        // 第一行
	        int start=sheet.getFirstRowNum();
	        // 总行数
	        int last=sheet.getLastRowNum();

	        int rowStart=0;
	        int rowEnd=0;
	        for(int i=start;i<last;i++){
	        	// 获取当前行
	        	Row row=sheet.getRow(i);
	        	if(i==start){
	        		//拿到第一行的第一个 和最后一个
	        		rowStart=row.getFirstCellNum();
	        		rowEnd=row.getLastCellNum();
	        		continue;
	        	}
	        	List<String> rowList=new ArrayList<String>();
	        	int count=0;
	        	for(int j=rowStart;j<rowEnd;j++){
	        		// 拿到单元格
	        		Cell cell=row.getCell(j);
	        		// 拿到单元格中的值
	        		String val=getStringVal(cell);
	        		if(val!=null&&!"".equals(val)) count++;
	        		// 将当前行的每一个单元格中的值放到集合中
	        		rowList.add(val);
	        	}
	        	if(count==0) continue;
				// 一行一行遍历，将每行的结果放进这个集合中并返回
	        	list.add(rowList);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(is!=null){
					is.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static List<Map<String,Object>> readExcelMap(String filePath,ExcelExportModel model){

		Workbook wb=null;
		InputStream is=null;
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try{
			is=new FileInputStream(filePath);
			String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
	        if("XLSX".equals(fileType.toUpperCase())){
	        	wb = new XSSFWorkbook(is);
	        }else if("XLS".equals(fileType.toUpperCase())){
	            wb = new HSSFWorkbook(is);
	        }else{
	        	//return new Result(0, "文件类型错误，导入失败");
	        }

	        Sheet sheet=wb.getSheetAt(0);
	        int last=sheet.getLastRowNum();
	        int start=sheet.getFirstRowNum();
	        int rowStart=0;
	        int rowEnd=0;
	        String []key=model.getExPro().split(",");
	        for(int i=start;i<last;i++){
	        	Row row=sheet.getRow(i);
	        	if(i==start){
	        		rowStart=row.getFirstCellNum();
	        		rowEnd=row.getLastCellNum();
	        		continue;
	        	}
	        	int count=0;
	        	Map<String,Object> rowMap=new HashMap<String,Object>();
	        	for(int j=0;j<key.length;j++){
	        		Cell cell=row.getCell(j);
	        		String val=getStringVal(cell);
	        		if(val!=null&&!"".equals(val)) count++;
	        		rowMap.put(key[j-rowStart], val);
	        	}
	        	if(count==0) continue;
	        	list.add(rowMap);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(is!=null){
					is.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	public static String getStringVal(Cell cell){
		String value="";
		if(cell==null) return "";
		switch (cell.getCellType()) {

	        case Cell.CELL_TYPE_NUMERIC:
	        	if(DateUtil.isCellDateFormatted(cell)){
	        		value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
	        	}else{
	        		double val=cell.getNumericCellValue();
	        		long intVal=(long) val;
	        		value=val-intVal==0?String.valueOf(intVal):String.valueOf(val);
	        	}
	            break;
	        case Cell.CELL_TYPE_FORMULA:
	        	/*try{
	        		value=String.valueOf(cell.getNumericCellValue());
	        	}catch(IllegalStateException e){
	        		value=String.valueOf(cell.getRichStringCellValue());
	        	}*/
	        	switch (cell.getCachedFormulaResultType()) {
					case Cell.CELL_TYPE_NUMERIC:
						value=String.valueOf(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
					case Cell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
					case Cell.CELL_TYPE_ERROR:
						value ="";
				default:
					break;
				}
	        case Cell.CELL_TYPE_STRING:
	            value = cell.getStringCellValue();
	            break;
	        case Cell.CELL_TYPE_BOOLEAN:
	            value = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case Cell.CELL_TYPE_BLANK:
	            value = "";
	            break;
	        case Cell.CELL_TYPE_ERROR:
	        	value = "";
	        	break;
	        default:
	            value = cell.toString().trim();
	    }
		return value;
	}




	
	public static void main(String []args){
		String filePath="F:\\大实训again文件\\7.24\\301项目正式导入模板(1).xlsx";
		List<List<String>> list=readExcel(filePath);
		/*for( List<String> ll: list){
			System.out.println(ll);
		}*/
		List<List<String>> list1 = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			//System.out.println(list.get(i));
			if(i==1){
				list1.add(list.get(i));
			}
		}

		for( List<String> ll: list1){
			for (String lll : ll){
				//System.out.println(lll);
				String[] tablename = lll.split(":");
				for(int i=0;i<tablename.length;i++){
                    System.out.println(tablename[i]);
                }
			}
		}

		/*ExcelExportModel model=new ExcelExportModel();
		model.setExPro("name,phoneNo,sex");
		readExcelMap(filePath,model);
		String str="name,phoneNo,sex,";
		System.out.println(str.substring(0, str.length()-1));*/
	}

}