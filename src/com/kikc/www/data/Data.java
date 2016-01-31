package com.kikc.www.data;

import java.util.Set;

import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.repertorybean.Repertory;

public class Data {
	private Set<Order> order_list;
	private Set<Repertory> repertory_list;

	public Set<Order> getOrder_list() {
		return order_list;
	}

	public void setOrder_list(Set<Order> order_list) {
		this.order_list = order_list;
	}

	public Set<Repertory> getRepertory_list() {
		return repertory_list;
	}

	public void setRepertory_list(Set<Repertory> repertory_list) {
		this.repertory_list = repertory_list;
	}
}
