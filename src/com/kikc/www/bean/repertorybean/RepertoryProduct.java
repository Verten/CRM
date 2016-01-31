package com.kikc.www.bean.repertorybean;

public class RepertoryProduct {
	private String productYear;
	private String productBoDuanName;
	private String productSeason;
	private String productMainCatagory;
	private String productSecondCatagory;
	private String productcatagory;
	private String productNumber;
	private String productcolorCode;
	private String productcolor;
	private String productsize;
	private int productAmount;
	public String getProductYear() {
		return productYear;
	}
	public void setProductYear(String productYear) {
		this.productYear = productYear;
	}
	public String getProductBoDuanName() {
		return productBoDuanName;
	}
	public void setProductBoDuanName(String productBoDuanName) {
		this.productBoDuanName = productBoDuanName;
	}
	public String getProductSeason() {
		return productSeason;
	}
	public void setProductSeason(String productSeason) {
		this.productSeason = productSeason;
	}
	public String getProductMainCatagory() {
		return productMainCatagory;
	}
	public void setProductMainCatagory(String productMainCatagory) {
		this.productMainCatagory = productMainCatagory;
	}
	public String getProductSecondCatagory() {
		return productSecondCatagory;
	}
	public void setProductSecondCatagory(String productSecondCatagory) {
		this.productSecondCatagory = productSecondCatagory;
	}
	public String getProductcatagory() {
		return productcatagory;
	}
	public void setProductcatagory(String productcatagory) {
		this.productcatagory = productcatagory;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductcolorCode() {
		return productcolorCode;
	}
	public void setProductcolorCode(String productcolorCode) {
		this.productcolorCode = productcolorCode;
	}
	public String getProductcolor() {
		return productcolor;
	}
	public void setProductcolor(String productcolor) {
		this.productcolor = productcolor;
	}
	public String getProductsize() {
		return productsize;
	}
	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productNumber == null) ? 0 : productNumber.hashCode());
		result = prime * result
				+ ((productcolor == null) ? 0 : productcolor.hashCode());
		result = prime
				* result
				+ ((productcolorCode == null) ? 0 : productcolorCode.hashCode());
		result = prime * result
				+ ((productsize == null) ? 0 : productsize.hashCode());
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
		RepertoryProduct other = (RepertoryProduct) obj;
		if (productNumber == null) {
			if (other.productNumber != null)
				return false;
		} else if (!productNumber.equals(other.productNumber))
			return false;
		if (productcolor == null) {
			if (other.productcolor != null)
				return false;
		} else if (!productcolor.equals(other.productcolor))
			return false;
		if (productcolorCode == null) {
			if (other.productcolorCode != null)
				return false;
		} else if (!productcolorCode.equals(other.productcolorCode))
			return false;
		if (productsize == null) {
			if (other.productsize != null)
				return false;
		} else if (!productsize.equals(other.productsize))
			return false;
		return true;
	}
	public RepertoryProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RepertoryProduct(String productYear, String productBoDuanName,
			String productSeason, String productMainCatagory,
			String productSecondCatagory, String productcatagory,
			String productNumber, String productcolorCode, String productcolor,
			String productsize, int productAmount) {
		super();
		this.productYear = productYear;
		this.productBoDuanName = productBoDuanName;
		this.productSeason = productSeason;
		this.productMainCatagory = productMainCatagory;
		this.productSecondCatagory = productSecondCatagory;
		this.productcatagory = productcatagory;
		this.productNumber = productNumber;
		this.productcolorCode = productcolorCode;
		this.productcolor = productcolor;
		this.productsize = productsize;
		this.productAmount = productAmount;
	}
	@Override
	public String toString() {
		return "RepertoryProduct [productYear=" + productYear
				+ ", productBoDuanName=" + productBoDuanName
				+ ", productSeason=" + productSeason + ", productMainCatagory="
				+ productMainCatagory + ", productSecondCatagory="
				+ productSecondCatagory + ", productcatagory="
				+ productcatagory + ", productNumber=" + productNumber
				+ ", productcolorCode=" + productcolorCode + ", productcolor="
				+ productcolor + ", productsize=" + productsize
				+ ", productAmount=" + productAmount + "]";
	}
}
