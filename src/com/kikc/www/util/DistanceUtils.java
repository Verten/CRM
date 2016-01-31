package com.kikc.www.util;

import java.net.URLEncoder;

import com.kikc.www.bean.mapbean.Location;
import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.bean.mapbean.Result;

public class DistanceUtils {
	private static double EARTH_RADIUS = 6370996.81;
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double DistanceOfTwoPoints(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		double ss = s * 1.0936132983377;
		return s;
		//System.out.println("两点间的距离是：" + s + "米" + "," + (int) ss + "码");
	}
	public static double distanceOfTwoPoint(String orderAddress,String repertoryAddress){
		String order_address = URLEncoder.encode(orderAddress);
		String responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ order_address +"&output=json");
		MapPoint order_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
		String repertory_address = URLEncoder.encode(repertoryAddress);
		responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ repertory_address +"&output=json");
		MapPoint repertory_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
		//System.out.println(repertory_mp.getResult().getLocation());
		
		double distance = DistanceUtils.DistanceOfTwoPoints(repertory_mp.getResult().getLocation().getLat(), 
				repertory_mp.getResult().getLocation().getLng(), 
				order_mp.getResult().getLocation().getLat(), order_mp.getResult().getLocation().getLng());
		//System.out.println("订单地址：" + orderAddress + ", 店铺地址：" + repertoryAddress + ", 距离为：" + distance);
		return distance;
	}
	
	public static double distanceOfTwoPoint(MapPoint orderAddress,String repertoryAddress){
		MapPoint order_mp = orderAddress;
		String repertory_address = URLEncoder.encode(repertoryAddress);
		String responseJSON;
		try {
			responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
					"address="+ repertory_address +"&output=json");
		} catch (Exception e) {
			responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
					"address="+ repertory_address +"&output=json");
			e.printStackTrace();
		}
		
		MapPoint repertory_mp = null;
		try {
			repertory_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(responseJSON);
			e.printStackTrace();
		}
		//System.out.println(repertory_mp.getResult().getLocation());
		
		double distance = DistanceUtils.DistanceOfTwoPoints(repertory_mp.getResult().getLocation().getLat(), 
				repertory_mp.getResult().getLocation().getLng(), 
				order_mp.getResult().getLocation().getLat(), order_mp.getResult().getLocation().getLng());
		//System.out.println("订单地址：" + orderAddress + ", 店铺地址：" + repertoryAddress + ", 距离为：" + distance);
		return distance;
	}
	
	public static MapPoint getOrderDistance(String orderAddress){
		String order_address = URLEncoder.encode(orderAddress);
		String responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ order_address +"&output=json");
		
		MapPoint order_mp = null;
		try {
			order_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
			if(order_mp.getStatus() != 0 ){
				order_mp = new MapPoint();
				Result r = new Result();
				Location l = new Location(0, 0);
				r.setLocation(l);
				order_mp.setResult(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order_mp;
	}
	public static MapPoint getRepertoryDistance(String repertoryAddress){
		String repertory_address = URLEncoder.encode(repertoryAddress);
		String responseJSON = HttpUtils.sendGet("http://api.map.baidu.com/geocoder/v2/", 
				"address="+ repertory_address +"&output=json");
		//System.out.println(responseJSON);
		MapPoint repertory_mp = null;
		try {
			repertory_mp = JSONUtils.toBean(responseJSON, MapPoint.class);
			if(repertory_mp.getStatus() != 0 ){
				repertory_mp = new MapPoint();
				Result r = new Result();
				Location l = new Location(0, 0);
				r.setLocation(l);
				repertory_mp.setResult(r);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repertory_mp;
	}
}
