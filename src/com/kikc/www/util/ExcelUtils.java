package com.kikc.www.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static POIFSFileSystem fs;
	private static Workbook wb;
	private static Sheet sheet;
	private static Row row;

	public static void createExcelTitle(Workbook wb){
		
		
		//创建第一个sheet   
		HSSFSheet sheet= (HSSFSheet) wb.getSheetAt(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		//生成第一行   
		HSSFRow row = sheet.createRow(1);
		Cell orderIdCell = row.createCell(0); 
		Cell orderReceiverCell = row.createCell(1);
		Cell orderReceiverLocationCell = row.createCell(2);
		Cell orderReceiverMobileNoCell = row.createCell(3);
		Cell orderProductIdCell = row.createCell(4);
		Cell orderProductAmountCell = row.createCell(5);
		Cell orderRepertoryNameCell = row.createCell(6);
		Cell orderRepertoryDirectorCell = row.createCell(7);
		Cell orderRepertoryDirectorMobileCell = row.createCell(8);
		Cell orderRepertoryTelNoCell = row.createCell(9);

		CellStyle style = getCellStyle(wb);
		style.setFillForegroundColor((short) 13);// 设置背景色   
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		orderIdCell.setCellStyle(style);
		orderReceiverCell.setCellStyle(style);
		orderReceiverLocationCell.setCellStyle(style);
		orderReceiverMobileNoCell.setCellStyle(style);
		orderProductIdCell.setCellStyle(style);
		orderProductAmountCell.setCellStyle(style);
		orderRepertoryNameCell.setCellStyle(style);
		orderRepertoryDirectorCell.setCellStyle(style);
		orderRepertoryDirectorMobileCell.setCellStyle(style);
		orderRepertoryTelNoCell.setCellStyle(style);

		orderIdCell.setCellValue("订单号");
		orderReceiverCell.setCellValue("订单收货人");
		orderReceiverLocationCell.setCellValue("订单收货人地址");
		orderReceiverMobileNoCell.setCellValue("订单收货人手机号码");
		//row.createCell(4).setCellValue("商品名称");
		//row.createCell(5).setCellValue("商品货号");
		orderProductIdCell.setCellValue("商品条码");
		//row.createCell(7).setCellValue("商品颜色");
		//row.createCell(8).setCellValue("商品尺码");
		orderProductAmountCell.setCellValue("商品数量");
		orderRepertoryNameCell.setCellValue("店铺地址");
		orderRepertoryDirectorCell.setCellValue("店铺负责人");
		orderRepertoryDirectorMobileCell.setCellValue("店铺负责人手机号码");
		orderRepertoryTelNoCell.setCellValue("店铺座机号码");
	}
	
	 public static CellStyle getCellStyle(Workbook wb){
		HSSFSheet sheet= (HSSFSheet) wb.getSheetAt(0);
		CellStyle style = wb.createCellStyle();
		
		style.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		
		style.setBorderBottom(CellStyle.BORDER_THIN); //下边框   
		style.setBorderLeft(CellStyle.BORDER_THIN);//左边框   
		style.setBorderTop(CellStyle.BORDER_THIN);//上边框   
		style.setBorderRight(CellStyle.BORDER_THIN);//右边框 
		
		return style;
	}
	
	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public static String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	public static boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);

					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {

		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf((long) cell.getNumericCellValue());

		}

		return "";
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public static Map<Integer, String> readExcelContent(File file, int lineNumber) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		InputStream is;
		try {
			is = new FileInputStream(file);
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (OfficeXmlFileException e) {
			try {
				wb = new XSSFWorkbook(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InvalidFormatException e1) {
				e1.printStackTrace();
			}
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(lineNumber);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = lineNumber; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				if(ExcelUtils.isMergedRegion(sheet,i,j)){
					str += ExcelUtils.getMergedRegionValue(sheet,i,j).trim().replace("'", "")
							+ "|";
				}else{
					str += getCellValue(row.getCell((short) j)).trim().replace("'", "")
							+ "|";
				}
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
	public static void setCellValue(Cell cell,Object value){
		if(value instanceof String){
			cell.setCellValue(String.valueOf(value));
		}
	}
	public static void main(String[] args) {
		try {
			// 对读取Excel表格标题测试
			InputStream is = new FileInputStream(
					"G:\\WorkspaceForEHMS\\Order Repertory Management\\缺货订单列表.xls");
			String[] title = ExcelUtils.readExcelTitle(is);
			System.out.println("获得Excel表格的标题:");
			for (String s : title) {
				System.out.print(s + " ");
			}

			// 对读取Excel表格内容测试
			File is2 = new File(
					"G:\\WorkspaceForEHMS\\Order Repertory Management\\缺货订单列表.xls");
			Map<Integer, String> map = ExcelUtils.readExcelContent(is2,1);
			System.out.println("获得Excel表格的内容:");
			for (int i = 1; i <= map.size(); i++) {
				System.out.println(map.get(i));
			}

		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}
	}
}
