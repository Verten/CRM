package com.kikc.www.thread;

import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.orderbean.Product;
import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.DateUtil;
import com.kikc.www.util.DistanceUtils;
import com.kikc.www.util.ExcelUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class OrderFileThread extends BaseThread implements Runnable{

	public OrderFileThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderFileThread(MainFrame mf, File file, Data data)
			throws FileNotFoundException {
		super(mf, file, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Set<Order> order_list = null;
		List<Product> product_list = null;
		Map<Integer, String> map = ExcelUtils.readExcelContent(file, 2);
		order_list = new HashSet<Order>();
		Set<Integer> lineNumber = map.keySet();
		for (Integer index : lineNumber) {
			String lineData = map.get(index);
			String[] order_data = lineData.split("\\|");
			if(order_data.length != 18){
				continue;
			}
			// 0 - 9 为order, 10 - 17 为product
			try {
				Order order = new Order(order_data[0], order_data[1],
						order_data[2], DateUtil.string2Date(
								"yyyy-MM-dd hh:mm", order_data[3]),
						order_data[4], order_data[5].replaceAll(" ", ""), order_data[6],
						order_data[7],
						Double.parseDouble(order_data[8]),
						order_data[9]);
				MapPoint order_mp = DistanceUtils.getOrderDistance(order_data[5].replaceAll(" ", ""));
				order.setLocation(order_mp.getResult().getLocation());
				Product product = new Product(order_data[10],
						order_data[11], order_data[12], order_data[13],
						order_data[14], order_data[15],
						Integer.parseInt(order_data[16]),
						Integer.parseInt(order_data[17]));
				if (order_list.contains(order)) {
					//System.out.println("contains exists order: " + order.toString());
					for (Order existsOrder : order_list) {
						if (existsOrder.equals(order)) {
							order = existsOrder;
							product_list = existsOrder.getProducts();
						}
					}
				} else {
					product_list = new ArrayList<Product>();
				}
				if (product_list != null) {
					product_list.add(product);
				}
				order.setProducts(product_list);
				order_list.add(order);
			} catch (NumberFormatException | ParseException e1) {
				e1.printStackTrace();
			}
		}
		mf.getResultTextarea().setText(
				mf.getResultTextarea().getText() + "\n导入订单成功！\n数据条数为： "
						+ order_list.size());
		data.setOrder_list(order_list);
		mf.getImportRepertoryButton().setEnabled(true);
	}
}
