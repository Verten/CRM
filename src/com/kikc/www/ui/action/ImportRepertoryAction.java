package com.kikc.www.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

import com.kikc.www.bean.repertorybean.Repertory;
import com.kikc.www.bean.repertorybean.RepertoryProduct;
import com.kikc.www.data.Data;
import com.kikc.www.thread.RepertoryFileThread;
import com.kikc.www.ui.MainFrame;

//应该从第二行开始读
public class ImportRepertoryAction extends BaseAction implements ActionListener {



	public ImportRepertoryAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImportRepertoryAction(MainFrame mf, JFileChooser jfc, Data data) {
		super(mf, jfc, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int flag = jfc.showOpenDialog(mf);
		if (flag == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			mf.getResultTextarea().setText(
					mf.getResultTextarea().getText() + "\n正在导入，请等待！");
			Thread t;
			try {
				t = new Thread(new RepertoryFileThread(mf, file,data));
				t.start();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				mf.getResultTextarea().append("\n" + e1.getMessage());
			} catch (Exception e2 ){
				e2.printStackTrace();
				mf.getResultTextarea().append("\n" + e2.getMessage());
			}
		}
	}

}
