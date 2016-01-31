package com.kikc.www.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kikc.www.bean.repertorybean.Repertory;
import com.kikc.www.bean.repertorybean.RepertoryProduct;
import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;
import com.kikc.www.util.ExcelUtils;

public class RepertoryFileThread extends BaseThread implements Runnable {


	public RepertoryFileThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RepertoryFileThread(MainFrame mf, File file, Data data)
			throws FileNotFoundException {
		super(mf, file, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Set<Repertory> repertory_list;
		List<RepertoryProduct> repertoryProduct_list = null;
		Map<Integer, String> map = ExcelUtils.readExcelContent(file, 1);
		repertory_list = new HashSet<Repertory>();
		Set<Integer> lineNumber = map.keySet();
		for (Integer index : lineNumber) {
			String lineData = map.get(index);
			String[] repertory_data = lineData.split("\\|");
			if(repertory_data.length != 13){
				continue;
			}
			// 0 - 1 为repertory, 2 - 12 为repertory product
			Repertory repertory = new Repertory(repertory_data[0],
					repertory_data[1]);
			RepertoryProduct repertoryProduct = new RepertoryProduct(
					repertory_data[2], repertory_data[3], repertory_data[4],
					repertory_data[5], repertory_data[6], repertory_data[7],
					repertory_data[8], repertory_data[9], repertory_data[10],
					repertory_data[11], Math.round(Float.parseFloat(repertory_data[12])));
			if (repertory_list.contains(repertory)) {
				for (Repertory existsRepertory : repertory_list) {
					if (existsRepertory.equals(repertory)) {
						repertory = existsRepertory;
						repertoryProduct_list = repertory
								.getRepertoryProducts();
					}
				}
			} else {
				repertoryProduct_list = new ArrayList<RepertoryProduct>();
			}
			if (repertoryProduct_list != null) {
				repertoryProduct_list.add(repertoryProduct);
			}
			repertory.setRepertoryProducts(repertoryProduct_list);
			repertory_list.add(repertory);
		}
		mf.getResultTextarea().setText(
				mf.getResultTextarea().getText() + "\n导入库存成功！\n数据条数为： "
						+ repertory_list.size());
		mf.getImportRepertoryLocationButton().setEnabled(true);
		data.setRepertory_list(repertory_list);
	}

}
