package com.kikc.www.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;

public class BaseThread {
	protected MainFrame mf;
	protected File file;
	protected InputStream in;
	protected Data data;

	public BaseThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseThread(MainFrame mf, File file,Data data) throws FileNotFoundException {
		super();
		this.mf = mf;
		this.file = file;
		this.data = data;
		this.in = new FileInputStream(file);
	}

}
