package com.kikc.www.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.orderbean.Product;
import com.kikc.www.bean.repertorybean.Repertory;
import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.DistanceUtils;
import com.kikc.www.util.RepertoryComparator;

public class SortDistanceOfRepertoryThread extends BaseThread implements Runnable {

	private List<Repertory> foundRepertory_list;
	private Product product;
	private Order order;
	
	public List<Repertory> getFoundRepertory_list() {
		return foundRepertory_list;
	}

	public void setFoundRepertory_list(List<Repertory> foundRepertory_list) {
		this.foundRepertory_list = foundRepertory_list;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public SortDistanceOfRepertoryThread(List<Repertory> foundRepertory_list,
			Product product, Order order) {
		super();
		this.foundRepertory_list = new ArrayList<>();
		this.foundRepertory_list.addAll(foundRepertory_list);
		this.product = product;
		this.order = order;
	}

	public SortDistanceOfRepertoryThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SortDistanceOfRepertoryThread(MainFrame mf, File file, Data data)
			throws FileNotFoundException {
		super(mf, file, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		//MapPoint order_mp = DistanceUtils.getOrderDistance(order.getReceiverLocation());
		// TODO Auto-generated method stub
		for (Repertory tmpRepertory : foundRepertory_list) {
			double distance = 0.0;
			if(tmpRepertory.getLocation() == null || tmpRepertory.getRepertoryRegionName() == null || "".equals(tmpRepertory.getRepertoryRegionName())){
				distance = 999999999999999999999.9;
			}else{
				//distance = DistanceUtils.distanceOfTwoPoint(order_mp,tmpRepertory.getDetailLocation());
				distance = DistanceUtils.DistanceOfTwoPoints(order
						.getLocation().getLat(), order.getLocation().getLng(),
						tmpRepertory.getLocation().getLat(), tmpRepertory
								.getLocation().getLng());
			}
			tmpRepertory.setDistance(distance);
		}
		Collections.sort(foundRepertory_list,
				new RepertoryComparator());
		System.out.println("------------------------------One match product list sort start-------------------------------------");
		for (Repertory tmpRepertory : foundRepertory_list) {
			System.out.println("distance: " + tmpRepertory.getDistance() + ", match product amount: " + tmpRepertory.getMatchProductAmount() + " ,match level: " + tmpRepertory.getLevel());
		}
		System.out.println("------------------------------One match product list sort end-------------------------------------");
		if(foundRepertory_list.size() == 0){
			product.setMinDistanceRepertory(new Repertory(""));
		}else{
			product.setMinDistanceRepertory(foundRepertory_list.get(0));
		}
	}

}
