package com.kikc.www.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.orderbean.Product;
import com.kikc.www.data.Data;
import com.kikc.www.thread.OrderFileThread;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.DateUtil;
import com.kikc.www.util.DistanceUtils;
import com.kikc.www.util.ExcelUtils;

//应该从第三行开始读
public class ImportOrderAction extends BaseAction implements ActionListener {



	public ImportOrderAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImportOrderAction(MainFrame mf, JFileChooser jfc, Data data) {
		super(mf, jfc, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int flag = jfc.showOpenDialog(mf);
		if (flag == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			try {
				mf.getResultTextarea().setText(
						mf.getResultTextarea().getText() + "\n正在导入，请等待！");
				is = new FileInputStream(file);
				Thread t;
				try {
					t = new Thread(new OrderFileThread(mf, file,data));
					t.start();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					mf.getResultTextarea().append("\n" + e1.getMessage());
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				mf.getResultTextarea().setText(
						mf.getResultTextarea().getText() + "\n" + e1.getMessage());
			}
		}
	}

}
