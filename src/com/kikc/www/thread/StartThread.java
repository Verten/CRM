package com.kikc.www.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.kikc.www.data.Data;
import com.kikc.www.ui.MainFrame;

public class StartThread extends BaseThread implements Runnable {

	private List<Thread> allDistanceThreads;
	
	public List<Thread> getAllDistanceThreads() {
		return allDistanceThreads;
	}

	public void setAllDistanceThreads(List<Thread> allDistanceThreads) {
		this.allDistanceThreads = allDistanceThreads;
	}

	public StartThread(List<Thread> allDistanceThreads) {
		super();
		this.allDistanceThreads = new ArrayList<Thread>(); 
		this.allDistanceThreads.addAll(allDistanceThreads);
	}

	public StartThread() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StartThread(MainFrame mf, File file, Data data)
			throws FileNotFoundException {
		super(mf, file, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean canBreak = false;
		int numberOfThread = 0;
		List<Thread> runningThread = new ArrayList<Thread>();
		while (!canBreak) {
			for (Thread thread : allDistanceThreads) {
				if(numberOfThread < allDistanceThreads.size() / 2){
					thread.start();
					numberOfThread++;
					runningThread.add(thread);
					continue;
				}else{
					try {
						this.wait(1000);
						for (Thread running : runningThread) {
							if(!running.isAlive()){
								numberOfThread--;
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
