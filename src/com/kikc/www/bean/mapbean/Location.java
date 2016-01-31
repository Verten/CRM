package com.kikc.www.bean.mapbean;

public class Location {
	private float lng;
	private float lat;
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(float lng, float lat) {
		super();
		this.lng = lng;
		this.lat = lat;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(lat);
		result = prime * result + Float.floatToIntBits(lng);
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
		Location other = (Location) obj;
		if (Float.floatToIntBits(lat) != Float.floatToIntBits(other.lat))
			return false;
		return Float.floatToIntBits(lng) == Float.floatToIntBits(other.lng);
	}
	@Override
	public String toString() {
		return "Location [lng=" + lng + ", lat=" + lat + "]";
	}
}
