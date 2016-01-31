package com.kikc.www.bean.orderbean;

import java.util.Date;
import java.util.List;

import com.kikc.www.bean.mapbean.Location;

public class Order {
	private String orderId;
	private String tradeId;
	private String repertoryName;
	private Date createDate;
	private String receiver;
	private String receiverLocation;
	private String telNo;
	private String mobileNo;
	private double paidMoney;
	private String comment;
	private List<Product> products;//商品名称-商品货号
	private Location location;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getRepertoryName() {
		return repertoryName;
	}
	public void setRepertoryName(String repertoryName) {
		this.repertoryName = repertoryName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiverLocation() {
		return receiverLocation;
	}
	public void setReceiverLocation(String receiverLocation) {
		this.receiverLocation = receiverLocation;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public double getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(double paidMoney) {
		this.paidMoney = paidMoney;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void setProduct(Product product) {
		if(!this.products.contains(product)){
			this.products.add(product);
		}
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderId, String tradeId, String repertoryName,
			Date createDate, String receiver, String receiverLocation,String telNo,
			String mobileNo, double paidMoney, String comment) {
		super();
		this.orderId = orderId;
		this.tradeId = tradeId;
		this.repertoryName = repertoryName;
		this.createDate = createDate;
		this.receiver = receiver;
		this.receiverLocation = receiverLocation;
		this.telNo = telNo;
		this.mobileNo = mobileNo;
		this.paidMoney = paidMoney;
		this.comment = comment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
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
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", tradeId=" + tradeId
				+ ", repertoryName=" + repertoryName + ", createDate="
				+ createDate + ", receiver=" + receiver + ", receiverLocation="
				+ receiverLocation + ", telNo=" + telNo + ", mobileNo="
				+ mobileNo + ", paidMoney=" + paidMoney + ", comment="
				+ comment + ", products=" + products + "]";
	}
}
