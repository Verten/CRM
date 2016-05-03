package com.kikc.www.bean.mapbean;

import java.util.List;

public class MapPoint {
	// the point in the map
	private int status; //0 -> success, 1 -> no result
	private Result result;
	private List<Result> results;
	private String msg;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		if(this.status != 0 ){
			//无相关结果
			Location l = new Location(0, 0);
			this.result = new Result();
			this.result.setLocation(l);
		}
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	//for error information
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
