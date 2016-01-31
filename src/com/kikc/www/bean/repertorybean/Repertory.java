package com.kikc.www.bean.repertorybean;

import java.util.Comparator;
import java.util.List;

import com.kikc.www.bean.mapbean.Location;

public class Repertory implements Comparator{
	private String repertoryRegionName;
	private String repertoryName;//店铺名称，粗略的地址
	private String detailLocation;//详细地址，从店铺信息表中读取
	private int matchProductAmount;//拥有该货物的数量
	private double distance;
	private String director;
	private String directorTelNo;
	private String directorMobileNo;
	private Location location;
	private String level;//店铺等级
	
	private List<RepertoryProduct> repertoryProducts;
	
	public String getRepertoryRegionName() {
		return repertoryRegionName;
	}
	public void setRepertoryRegionName(String repertoryRegionName) {
		this.repertoryRegionName = repertoryRegionName;
	}
	public String getRepertoryName() {
		return repertoryName;
	}
	public void setRepertoryName(String repertoryName) {
		this.repertoryName = repertoryName;
	}
	public List<RepertoryProduct> getRepertoryProducts() {
		return repertoryProducts;
	}
	public void setRepertoryProducts(List<RepertoryProduct> repertoryProducts) {
		this.repertoryProducts = repertoryProducts;
	}
	
	public String getDetailLocation() {
		if(detailLocation == null || "".equals(detailLocation)){
			return repertoryName; 
		}
		return detailLocation;
	}
	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirectorTelNo() {
		return directorTelNo;
	}
	public void setDirectorTelNo(String directorTelNo) {
		this.directorTelNo = directorTelNo;
	}
	public String getDirectorMobileNo() {
		return directorMobileNo;
	}
	public void setDirectorMobileNo(String directorMobileNo) {
		this.directorMobileNo = directorMobileNo;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLevel() {
		if(level == null || "".equals(level)){
			return "0";
		}
		return level;
	}

	public void setLevel(String level) {
		if("".equals(level) || null == level){
			this.level = "0";
		}else{
			this.level = level;
		}
	}
	public int getMatchProductAmount() {
		return matchProductAmount;
	}
	public void setMatchProductAmount(int matchProductAmount) {
		this.matchProductAmount = matchProductAmount;
	}
	public Repertory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Repertory(String repertoryName) {
		super();
		if(repertoryName == null || "".equals(repertoryName)){
			detailLocation = "";
			director = "";
			directorMobileNo = "";
			directorTelNo = "";
			location = new Location(0, 0);
			this.repertoryName = "";
		}else{
			this.repertoryName = repertoryName;
		}
	}
	public Repertory(String repertoryRegionName, String repertoryName) {
		super();
		this.repertoryRegionName = repertoryRegionName;
		this.repertoryName = repertoryName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((repertoryName == null) ? 0 : repertoryName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repertory other = (Repertory) obj;
		if (repertoryName == null) {
			if (other.repertoryName != null)
				return false;
		} else if (!repertoryName.equals(other.repertoryName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Repertory [repertoryRegionName=" + repertoryRegionName
				+ ", repertoryName=" + repertoryName + "]";
	}
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		double repertory1 = ((Repertory)o1).getDistance();
		double repertory2 = ((Repertory)o2).getDistance();
		if(repertory1 > repertory2){
			return 1;
		}else if(repertory1 == repertory2){
			return 0;
		}else if(repertory1 < repertory2){
			return -1;
		}
		return 0;
	}

}
