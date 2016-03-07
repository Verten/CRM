package com.kikc.www.thread;

import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.orderbean.Product;
import com.kikc.www.bean.repertorybean.Repertory;
import com.kikc.www.bean.repertorybean.RepertoryProduct;
import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.*;
import java.util.*;

public class GenerateResultFileThread extends BaseThread implements Runnable {

	public GenerateResultFileThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerateResultFileThread(MainFrame mf, File file, Data data)
			throws FileNotFoundException {
		super(mf, file, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		List<Thread> allDistanceThreads = new ArrayList<Thread>();
		// TODO Auto-generated method stub
		Set<Order> order_list = data.getOrder_list();
		Set<Repertory> repertory_list = data.getRepertory_list();
		List<Repertory> foundRepertory_list = new ArrayList<Repertory>();
		Map<String,List<Repertory>> order_Repertory_result = new HashMap<String, List<Repertory>>();
		Map<String,Map<String,List<Product>>> repertory_product_result = new HashMap<String, Map<String,List<Product>>>();
		
		for (Order order : order_list) {
			mf.getResultTextarea().append("\n---------------------------查找订单："+ order.getOrderId() +" ！----------------------------");
			List<Product> orderProduct = order.getProducts();
			Map<String,List<Product>> product_Repertory_Mapping = new HashMap<String, List<Product>>();
			for (Product product : orderProduct) {
				foundRepertory_list = new ArrayList<Repertory>();
				// Product -> name number color size count
				// RepertoryProduct -> productcatagory productNumber
				// productcolor productsize productAmount
				for (Repertory repertory : repertory_list) {
					boolean flag = false;
					//mf.getResultTextarea().append("\n正在查找店铺，"
									//+ repertory.getRepertoryName() + ",请等待！");
					List<RepertoryProduct> repertoryProduct = repertory
							.getRepertoryProducts();
					for (RepertoryProduct repertoryproduct : repertoryProduct) {
						//if (product.getName().equals(
								//repertoryproduct.getProductcatagory())) {
							if(product.getNumber().equals(
									repertoryproduct.getProductNumber()) && product.getColor().equals(
											repertoryproduct.getProductcolor()) && product.getSize().equals(
													repertoryproduct.getProductsize()) && product.getCount() <= repertoryproduct
															.getProductAmount()){
								//if(product.getColor().equals(
										//repertoryproduct.getProductcolor())){
									//if( product.getSize().equals(
											//repertoryproduct.getProductsize())){
										//if(product.getCount() <= repertoryproduct
												//.getProductAmount()){
											mf.getResultTextarea().append("\n订单:"+ order.getOrderId() +",找到货号相等:"
													+ product.getNumber() + ",找到颜色相等:"
													+ product.getColor()+ ",尺码相等:"
													+ product.getSize() + ",数量符合:"
													+ repertoryproduct.getProductAmount());
											flag = true;
											repertory.setMatchProductAmount(repertoryproduct.getProductAmount());
											break;
										//}
									//}
								//}
							} else {
								flag = false;
							}
						//}
					}
					if (flag) {
						mf.getResultTextarea().append("\n找到店铺，"
								+ repertory.getRepertoryName() + "！");
						List<Product> tmpProducts = null;
						tmpProducts = product_Repertory_Mapping.get(repertory.getRepertoryName());
						if(tmpProducts == null){
							tmpProducts = new ArrayList<Product>();
						}
						tmpProducts.add(product);
						product_Repertory_Mapping.put(repertory.getRepertoryName(), tmpProducts);
						foundRepertory_list.add(repertory);
						flag = false;
					}
				}
				/*if(!found){
						mf.getResultTextarea().append("\n商品：" + product + " 没有店铺！");
						Repertory rep = new Repertory("","没有店铺！");
						foundRepertory_list.add(rep);
				}*/
				repertory_product_result.put(order.getOrderId(), product_Repertory_Mapping);
				boolean integralOrder = false;
				/*for (Product tmpOrderProduct : orderProduct) {
					if(tmpOrderProduct.getMinDistanceRepertory() != null && !tmpOrderProduct.equals(product)){
						if(foundRepertory_list.contains(tmpOrderProduct.getMinDistanceRepertory())){
							product.setMinDistanceRepertory(tmpOrderProduct.getMinDistanceRepertory());
							integralOrder = true;
							break;
						}
					}
				}*/
				if(!integralOrder){
					synchronized(this){
						try {
							this.wait(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Thread t = new Thread(new SortDistanceOfRepertoryThread(foundRepertory_list, product, order));
						allDistanceThreads.add(t);
						t.start();
					}
				}
			}
			order_Repertory_result.put(order.getOrderId(), foundRepertory_list);
		}
		
		synchronized(this){
			boolean canBreak = false;
			while (!canBreak) {
				try {
					this.wait(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (Thread thread : allDistanceThreads) {
					if(thread.isAlive()){
						canBreak = false;
						break;
					}else{
						canBreak = true;
					}
				}
			}
		}

		boolean generate_Flag = false;
		try {
			generate_Flag = generateExcelFile(order_Repertory_result,repertory_product_result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mf.getResultTextarea().append("\n" + e.getMessage());
		} catch (IOException e) {
			mf.getResultTextarea().append("\n" + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			mf.getResultTextarea().append("\n" + e.getMessage());
			e.printStackTrace();
		}
		if(!generate_Flag){
			mf.getResultTextarea().append("\n生成失败！请将错误内容发送给管理员。");
		}
	}

	//写入excel
	@SuppressWarnings("deprecation")
	private boolean generateExcelFile(Map<String,List<Repertory>> order_Repertory_result,Map<String,Map<String,List<Product>>> repertory_product_result) throws IOException{
		OutputStream os = new FileOutputStream(file);  
		HSSFWorkbook wb = new HSSFWorkbook();
		//创建第一个sheet   
		HSSFSheet sheet= wb.createSheet("result");
		sheet.setColumnWidth(2, 50 * 256);
		//生成第一行   
		HSSFRow row = sheet.createRow(0);
		ExcelUtils.createExcelTitle(wb);
		Cell titleCell = row.createCell(0); 
		titleCell.setCellValue("订单库存对应表");
		CellStyle style = ExcelUtils.getCellStyle(wb);
		titleCell.setCellStyle(style);
		
		Set<String> orderid = order_Repertory_result.keySet();
		Set<Order> order_list = data.getOrder_list();
		int rowNum = 2;
		int regionFirstRowNum = 2;
		for (String index : orderid) {
			for (Order order : order_list) {
				if(order.getOrderId().equals(index)){
					int products_amount = order.getProducts().size() - 1;
					CellRangeAddress cra1=new CellRangeAddress(regionFirstRowNum, (short) (regionFirstRowNum + products_amount), 0, (short) 0);
					CellRangeAddress cra2=new CellRangeAddress(regionFirstRowNum, (short) (regionFirstRowNum + products_amount), 1, (short) 1);
					CellRangeAddress cra3=new CellRangeAddress(regionFirstRowNum, (short) (regionFirstRowNum + products_amount), 2, (short) 2);
					CellRangeAddress cra4=new CellRangeAddress(regionFirstRowNum, (short) (regionFirstRowNum + products_amount), 3, (short) 3);
					sheet.addMergedRegion(cra1);
					sheet.addMergedRegion(cra2);
					sheet.addMergedRegion(cra3);
					sheet.addMergedRegion(cra4);
					regionFirstRowNum += products_amount + 1;
					int columnNum = 0;
					HSSFRow contentRow = sheet.createRow(rowNum);
					HSSFCell orderIDCell = contentRow.createCell(columnNum++);
					HSSFCell orderReceiverCell = contentRow.createCell(columnNum++);
					HSSFCell orderReceiverLocationCell = contentRow.createCell(columnNum++);
					HSSFCell orderReceiverTelNoCell = contentRow.createCell(columnNum++);
					
					/*CellStyle orderStyle = ExcelUtils.getCellStyle(wb);
					orderStyle.setFillForegroundColor(HSSFColor.ROSE.index);// 设置背景色   
					orderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
					orderIDCell.setCellStyle(orderStyle);
					orderReceiverCell.setCellStyle(orderStyle);
					orderReceiverLocationCell.setCellStyle(orderStyle);
					orderReceiverTelNoCell.setCellStyle(orderStyle);*/
					
					ExcelUtils.setCellValue(orderIDCell, order.getOrderId());
					ExcelUtils.setCellValue(orderReceiverCell,order.getReceiver());
					ExcelUtils.setCellValue(orderReceiverLocationCell,order.getReceiverLocation());
					ExcelUtils.setCellValue(orderReceiverTelNoCell,order.getMobileNo());
					
					/*CellStyle productStyle = ExcelUtils.getCellStyle(wb);
					productStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);// 设置背景色   
					productStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
					
					int productRowNum = rowNum;
					for (Product product : order.getProducts()) {
						//4 -> 13
						HSSFRow ProductContentRow = sheet.getRow(productRowNum);
						if(ProductContentRow == null){
							ProductContentRow = sheet.createRow(productRowNum);
						}
						//HSSFCell productNameCell = ProductContentRow.createCell(columnNum++);
						//HSSFCell productNumberCell = ProductContentRow.createCell(columnNum++);
						HSSFCell productIDCell = ProductContentRow.createCell(columnNum++);
						//HSSFCell productColorCell = ProductContentRow.createCell(columnNum++);
						//HSSFCell productSizeCell = ProductContentRow.createCell(columnNum++);
						HSSFCell productAmountCell = ProductContentRow.createCell(columnNum++);
						HSSFCell repertoryNameCell = ProductContentRow.createCell(columnNum++);
						HSSFCell repertoryDirectorCell = ProductContentRow.createCell(columnNum++);
						HSSFCell repertoryDirectorMobileNoCell = ProductContentRow.createCell(columnNum++);
						HSSFCell repertoryDirectorTelNoCell = ProductContentRow.createCell(columnNum++);
						
						
						//ExcelUtils.setCellValue(productNameCell,product.getName());
						//ExcelUtils.setCellValue(productNumberCell,product.getNumber());
						ExcelUtils.setCellValue(productIDCell,product.getId());
						//ExcelUtils.setCellValue(productColorCell,product.getColor());
						//ExcelUtils.setCellValue(productSizeCell,product.getSize());
						ExcelUtils.setCellValue(productAmountCell,String.valueOf(product.getCount()));
						ExcelUtils.setCellValue(repertoryNameCell,product.getMinDistanceRepertory().getRepertoryName() == null? "":product.getMinDistanceRepertory().getRepertoryName());
						ExcelUtils.setCellValue(repertoryDirectorCell,product.getMinDistanceRepertory().getDirector() == null? "":product.getMinDistanceRepertory().getDirector());
						ExcelUtils.setCellValue(repertoryDirectorMobileNoCell,product.getMinDistanceRepertory().getDirectorMobileNo() == null? "":product.getMinDistanceRepertory().getDirectorMobileNo());
						ExcelUtils.setCellValue(repertoryDirectorTelNoCell,product.getMinDistanceRepertory().getDirectorTelNo() == null? "":product.getMinDistanceRepertory().getDirectorTelNo());
						productRowNum++;
						columnNum = 4;
					}
					rowNum = rowNum + products_amount + 1;
					break;
				}
			}
			/*
			 * Map<String,List<Product>> repertorys_products =
			 * repertory_product_result.get(index); List<Repertory> repertorys =
			 * order_Repertory_result.get(index); for (Repertory repertory :
			 * repertorys) {//
			 * 
			 * List<Product> products =
			 * repertorys_products.get(repertory.getRepertoryName());
			 * //订单下面所订货物对应的仓库 repertory, products }
			 */
		}
		wb.write(os);
		os.flush();
		os.close();
		mf.getResultTextarea().append("\n生成完毕！");
		System.out.println("生成完毕！");
		return true;
	}
	
}
