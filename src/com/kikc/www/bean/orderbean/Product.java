package com.kikc.www.bean.orderbean;

import com.kikc.www.bean.repertorybean.Repertory;

public class Product {
	private String name;
	private String number;//商品货号
	private String id;//商品条码
	private String color;
	private String size;
	private String specifications;
	private int count;
	private int shortage;
	private Repertory minDistanceRepertory;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getShortage() {
		return shortage;
	}
	public void setShortage(int shortage) {
		this.shortage = shortage;
	}
	
	public Repertory getMinDistanceRepertory() {
		return minDistanceRepertory;
	}
	public void setMinDistanceRepertory(Repertory minDistanceRepertory) {
		this.minDistanceRepertory = minDistanceRepertory;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String number, String id, String color,
			String size, String specifications, int count, int shortage) {
		super();
		this.name = name;
		this.number = number;
		this.id = id;
		this.color = color;
		this.size = size;
		this.specifications = specifications;
		this.count = count;
		this.shortage = shortage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		Product other = (Product) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", number=" + number + ", id=" + id
				+ ", color=" + color + ", size=" + size + ", specifications="
				+ specifications + ", count=" + count + ", shortage="
				+ shortage + "]";
	}
}
