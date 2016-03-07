package com.kikc.www.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import com.kikc.www.bean.mapbean.MapPoint;
import com.kikc.www.bean.repertorybean.Repertory;
import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.DistanceUtils;
import com.kikc.www.util.ExcelUtils;

//应该从第三行开始读
public class ImportRepertoryLocationAction extends BaseAction implements ActionListener {


	public ImportRepertoryLocationAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImportRepertoryLocationAction(MainFrame mf, JFileChooser jfc,
			Data data) {
		super(mf, jfc, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Set<Repertory> repertory_list = data.getRepertory_list();
		if(repertory_list==null || repertory_list.size() == 0){
			mf.getResultTextarea().setText(mf.getResultTextarea() + "\n库存信息为空！");
			return;
		}
		boolean import_Flag = true;
		int flag = jfc.showOpenDialog(mf);
		if (flag == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			int count = 0;
			try {
				mf.getResultTextarea().setText(
						mf.getResultTextarea().getText() + "\n正在导入，请等待！");
				is = new FileInputStream(file);
				Map<Integer, String> map = ExcelUtils.readExcelContent(file, 2);
				Set<Integer> lineNumber = map.keySet();
				for (Integer index : lineNumber) {
					String lineData = map.get(index);
					String[] repertory_data = lineData.split("\\|");
					if(repertory_data.length != 25){
						continue;
					}
					Repertory repertory = new Repertory(repertory_data[1].trim());
					String repertoryLocation = repertory_data[16];//详细信息
					String level = repertory_data[3];//店铺等级
					MapPoint repertory_mp = DistanceUtils.getRepertoryDistance(repertoryLocation);
					String director = repertory_data[17];//联系人
					String directorTelNo = repertory_data[18];//联系人座机
					String directorMobileNo = repertory_data[19];//联系人手机
					if(repertory_list.contains(repertory)){
						for (Repertory existsRepertory : repertory_list) {
							if(existsRepertory.equals(repertory)){
								existsRepertory.setLevel(level);
								existsRepertory.setDetailLocation(repertoryLocation);
								existsRepertory.setDirector(director);
								existsRepertory.setDirectorTelNo(directorTelNo);
								existsRepertory.setDirectorMobileNo(directorMobileNo);
								existsRepertory.setLocation(repertory_mp.getResult().getLocation());
								mf.getResultTextarea().setText(
										mf.getResultTextarea().getText() + "\n更新库存成功！\n更新数据为： "
												+ existsRepertory.getRepertoryName() + "\t地址为: " + existsRepertory.getDetailLocation() + "\n\t联系人为：" + existsRepertory.getDirector() + "\t联系电话为：" + existsRepertory.getDirectorTelNo());
							count++;
							}
						}
					}else{
						//没有详细信息
					}
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				mf.getResultTextarea().append("\n" + e1.getMessage());
				import_Flag = false;
			} catch (Exception e2){
				mf.getResultTextarea().append("\n" + e2.getMessage());
				e2.printStackTrace();
				import_Flag = false;
			}
			if(import_Flag){
				mf.getResultTextarea().setText(
						mf.getResultTextarea().getText() + "\n更新库存成功！\n更新数据条数为： "
								+ count);
				mf.getGenerateResultButton().setEnabled(true);
			}
		}
	}

}
