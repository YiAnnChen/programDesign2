package OtherJFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;

import control.GameControl;

public class JFrameSavePoint extends JFrame {
	
	private Font dF_Font = new Font("標楷體",Font.BOLD,12);
	private JLabel ibPoint;
	private JLabel ibRank;
	private JLabel ibName;
	private JLabel errMsg;
	private JTextField tfName;
	private JButton btnOK;
	private GameControl gameControl;
	
	
	public JFrameSavePoint(GameControl gameControl) {
		
		this.gameControl = gameControl;
		this.setTitle("保存分數");
		this.setSize(250, 141);
		this.setResizable(false);
		this.setDefaultCloseOperation(1);
		this.setLayout(null); //設定絕對佈局
		this.setLocationRelativeTo(null);
		this.creatCom(); //初始化組件
		this.creatAction(); //安裝事件監聽
	}
	
	//顯示保存分數窗口
	public void showSavePoint(int point,String rank) {
		this.ibPoint.setText("您的分數："+ point);
		this.ibRank.setText("您的等級："+ rank);
		this.setVisible(true);
	}

	//初始化組件
	private void creatCom() {
		this.ibPoint=new JLabel(); //初始化 jbpoint
		this.ibPoint.setFont(dF_Font);//設置字型
		this.ibPoint.setText("您的分數：6666"); //顯示分數
		this.ibPoint.setBounds(8,3,150,20 );  //設定位置
		this.add(ibPoint); //加到窗口
		this.ibRank=new JLabel(); //初始化 jbrank
		this.ibRank.setFont(dF_Font);//設置字型
		this.ibRank.setText("您的段位：6666"); //顯示分數
		this.ibRank.setBounds(8,30,150,20 );  //設定位置
		this.add(ibRank); //加到窗口
		this.ibName=new JLabel(); //初始化 ibName 
		this.ibName.setFont(dF_Font);//設置字型
		this.ibName.setText("您的名字："); //顯示分數
		this.ibName.setBounds(8,57,150,20 );  //設定位置
		this.add(ibName); //加到窗口
		
		this.tfName=new JTextField(); //初始化 tfName 
		this.tfName.setFont(dF_Font);//設置字型
		this.tfName.setBounds(75,57,120,22 );  //設定位置
		this.add(tfName); //加到窗口
		
		this.btnOK=new JButton(); //初始化 btnOK 
		this.btnOK.setFont(dF_Font);//設置字型
		this.btnOK.setText("保存"); //顯示分數
		this.btnOK.setBounds(92,84,60,22 );  //設定位置
		this.add(btnOK); //加到窗口
		
		this.errMsg=new JLabel(); //初始化 btnOK 
		this.errMsg.setFont(dF_Font);//設置字型
		this.errMsg.setForeground(Color.red);
		this.errMsg.setText(" "); //顯示分數
		this.errMsg.setBounds(120,3,150,20 );  //設定位置
		this.add(errMsg); //加到窗口
		
		
	}
	//安裝事件監聽
	private void creatAction() {
		this.btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//獲取輸入文字
				String name = tfName.getText();
				if(name.length()<1 || name.length()>4 || " ".equals(name)) {
					errMsg.setText("名字限制1-4字");
				}
				else {
					setVisible(false); //保存分數視窗關閉
					gameControl.savePoint(name); //保存分數
				}
				
			}
		});
	}
	
}
