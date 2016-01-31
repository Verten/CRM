package com.kikc.www.in;

import javax.swing.JFileChooser;

import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.ui.action.GenerateResultAction;
import com.kikc.www.ui.action.ImportOrderAction;
import com.kikc.www.ui.action.ImportRepertoryAction;
import com.kikc.www.ui.action.ImportRepertoryLocationAction;

public class Launcher {
	
	private MainFrame mf;
	private JFileChooser jfc;
	private static Data data;

	public Launcher(MainFrame mf) {
		super();
		this.mf = mf;
		data = new Data();
		initMainFrame();
		initMainFrameAction();
	}
	
	private void initMainFrame(){
		mf.setVisible(true);
		jfc = mf.getFc();
	}
	
	private void initMainFrameAction(){
		mf.getImportOrderButton().addActionListener(new ImportOrderAction(mf,jfc,data));
		mf.getImportRepertoryButton().addActionListener(new ImportRepertoryAction(mf,jfc,data));
		mf.getImportRepertoryLocationButton().addActionListener(new ImportRepertoryLocationAction(mf,jfc,data));
		mf.getGenerateResultButton().addActionListener(new GenerateResultAction(mf,jfc,data));
	}
	
	//lat:纬度,lng:经度
	public static void main(String[] args) {
		new Launcher(new MainFrame());
	}
}
