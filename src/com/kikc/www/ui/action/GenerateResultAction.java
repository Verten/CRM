package com.kikc.www.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import com.kikc.www.data.Data;
import com.kikc.www.thread.GenerateResultFileThread;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.DateUtil;

public class GenerateResultAction extends BaseAction implements ActionListener{

	
	public GenerateResultAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerateResultAction(MainFrame mf, JFileChooser jfc, Data data) {
		super(mf, jfc, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mf.getResultTextarea().setText(
				mf.getResultTextarea().getText() + "\n开始生成文件！");
		Date now = new Date();
		String fileName = "result_" + DateUtil.date2String("yyyy-MM-dd", now) + ".xls";
		File file = new File(fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		Thread t;
		try {
			t = new Thread(new GenerateResultFileThread(mf, file , data));
			t.start();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			mf.getResultTextarea().append("\n" + e1.getMessage());
		}
	}

}
