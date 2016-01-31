package com.kikc.www.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.kikc.www.bean.orderbean.Order;
import com.kikc.www.bean.repertorybean.Repertory;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser fc = new JFileChooser();
	private JButton importOrderButton;
	private JButton importRepertoryButton;
	private JButton importRepertoryLocationButton;
	private JTextArea resultTextarea;
	private JScrollPane scrollPane;
	private JButton generateResultButton;

	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public JButton getImportOrderButton() {
		return importOrderButton;
	}

	public void setImportOrderButton(JButton importOrderButton) {
		this.importOrderButton = importOrderButton;
	}

	public JButton getImportRepertoryButton() {
		return importRepertoryButton;
	}

	public void setImportRepertoryButton(JButton importRepertoryButton) {
		this.importRepertoryButton = importRepertoryButton;
	}

	public JButton getImportRepertoryLocationButton() {
		return importRepertoryLocationButton;
	}

	public void setImportRepertoryLocationButton(
			JButton importRepertoryLocationButton) {
		this.importRepertoryLocationButton = importRepertoryLocationButton;
	}

	public JTextArea getResultTextarea() {
		return resultTextarea;
	}

	public void setResultTextarea(JTextArea resultTextarea) {
		this.resultTextarea = resultTextarea;
	}

	public JButton getGenerateResultButton() {
		return generateResultButton;
	}

	public void setGenerateResultButton(JButton generateResultButton) {
		this.generateResultButton = generateResultButton;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		importOrderButton = new JButton("导入订单");
		importOrderButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		GridBagConstraints gbc_importOrderButton = new GridBagConstraints();
		gbc_importOrderButton.insets = new Insets(0, 0, 5, 5);
		gbc_importOrderButton.gridx = 2;
		gbc_importOrderButton.gridy = 1;
		contentPane.add(importOrderButton, gbc_importOrderButton);
		
		importRepertoryButton = new JButton("导入库存");
		importRepertoryButton.setEnabled(false);
		importRepertoryButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		GridBagConstraints gbc_importRepertoryButton = new GridBagConstraints();
		gbc_importRepertoryButton.insets = new Insets(0, 0, 5, 5);
		gbc_importRepertoryButton.gridx = 4;
		gbc_importRepertoryButton.gridy = 1;
		contentPane.add(importRepertoryButton, gbc_importRepertoryButton);
		
		importRepertoryLocationButton = new JButton("导入店铺");
		importRepertoryLocationButton.setEnabled(false);
		importRepertoryLocationButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		GridBagConstraints gbc_importRepertoryLocationButton = new GridBagConstraints();
		gbc_importRepertoryLocationButton.insets = new Insets(0, 0, 5, 5);
		gbc_importRepertoryLocationButton.gridx = 6;
		gbc_importRepertoryLocationButton.gridy = 1;
		contentPane.add(importRepertoryLocationButton, gbc_importRepertoryLocationButton);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		resultTextarea = new JTextArea();
		scrollPane.setViewportView(resultTextarea);
		resultTextarea.setEditable(false);
		resultTextarea.setText("结果显示");
		resultTextarea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		generateResultButton = new JButton("生成");
		generateResultButton.setEnabled(false);
		generateResultButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		GridBagConstraints gbc_generateResultButton = new GridBagConstraints();
		gbc_generateResultButton.insets = new Insets(0, 0, 5, 5);
		gbc_generateResultButton.gridx = 9;
		gbc_generateResultButton.gridy = 3;
		contentPane.add(generateResultButton, gbc_generateResultButton);
	}

}
