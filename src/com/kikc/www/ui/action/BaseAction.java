package com.kikc.www.ui.action;

import java.io.InputStream;

import javax.swing.JFileChooser;

import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;

public class BaseAction {
	protected MainFrame mf;
	protected JFileChooser jfc;
	protected Data data;
	protected InputStream is;
	
	public BaseAction(MainFrame mf, JFileChooser jfc, Data data) {
		super();
		this.mf = mf;
		this.jfc = jfc;
		this.data = data;
	}

	public BaseAction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
