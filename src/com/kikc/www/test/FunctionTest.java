package com.kikc.www.test;

import java.io.FileInputStream;
import java.net.URLEncoder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.util.DistanceUtils;
import com.kikc.www.util.ExcelUtils;
import com.kikc.www.util.HttpUtils;
import com.kikc.www.util.JSONUtils;

public class FunctionTest {
	@Test
	public void distanceOfTwoPoint(){
		String order_address = URLEncoder.encode("广东省 深圳市 宝安区 龙华街道龙华镇民治向南三区36栋1402室");
		String responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ order_address +"&output=json");
		MapPoint order_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
		System.out.println(order_mp.getResult().getLocation());
		
		String repertory_address = URLEncoder.encode("福建福州东街口大洋百货专柜");
		responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ repertory_address +"&output=json");
		MapPoint repertory_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
		System.out.println(repertory_mp.getResult().getLocation());
		
		double distance = DistanceUtils.DistanceOfTwoPoints(repertory_mp.getResult().getLocation().getLat(), 
				repertory_mp.getResult().getLocation().getLng(), 
				order_mp.getResult().getLocation().getLat(), order_mp.getResult().getLocation().getLng());
		System.out.println(distance);
	}
	@Test
	public void readExcel(){
		/** Excel文件的存放位置。注意是反斜线*/
	    String fileToBeRead = "G:\\WorkspaceForEHMS\\Order Repertory Management\\缺货订单列表.xls";
	        try {
	            // 创建对Excel工作簿文件的引用
	            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
	            // 创建对工作表的引用。
	            // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            // 也可用getSheetAt(int index)按索引引用，
	            // 在Excel文档中，第一张工作表的缺省索引是0，
	            // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
	            // 读取左上端单元
	            HSSFRow row = sheet.getRow(2);
	            HSSFCell cell = row.getCell((short)10);
	            // 输出单元内容，cell.getStringCellValue()就是取所在单元的值
	            System.out.println("左上端单元是： " + cell.getStringCellValue());
	            System.out.println(ExcelUtils.isMergedRegion(sheet, 3, 0));
	            System.out.println(ExcelUtils.getMergedRegionValue(sheet,3,0));
	        } catch (Exception e) {
	            System.out.println("已运行xlRead() : " + e);
	        }
    }
	@Test
	public void compareString(){
		System.out.println("A".compareTo("B"));
	}
}
