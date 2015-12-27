import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class chessbutton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton Undo,Exit,Tie,Surrender,NewChess;//Undo为悔棋，Exit为退出,Tie为和棋，Surrender为认输,NewChess为重新开始
	public ButtonGroup MyButtonGroup,MyBack;
	public JLabel MyLabel,MyBackLabel;
	public JRadioButton Simple,Middle,Difficult,Back1,Back2,Back3;
	public chessbutton()
	{
		Undo=new JButton("悔棋");
		Undo.setBounds((int)(chessboard.Left/4-60),(int)(chessboard.Low-40),80,40);
		Undo.setFont(new Font("Serif",Font.PLAIN,20));

		Tie=new JButton("和棋");
		Tie.setBounds((int)(chessboard.Left/4*3-40),(int)(chessboard.Low-40),80,40);
		Tie.setFont(new Font("Serif",Font.PLAIN,20));
		
		Surrender=new JButton("认输");
		Surrender.setBounds((int)(chessboard.Left/4-60),(int)(chessboard.Low-100),80,40);
		Surrender.setFont(new Font("Serif",Font.PLAIN,20));
		
	
		Exit=new JButton("退出");
		Exit.setBounds((int)(chessboard.Left/4*3-40),(int)(chessboard.Low-100),80,40);
		Exit.setFont(new Font("Serif",Font.PLAIN,20));
		
		
		NewChess=new JButton("新游戏");
		NewChess.setBounds((int)(chessboard.Left/4+25),(int)(chessboard.Low-70),100,40);
		NewChess.setFont(new Font("Serif",Font.PLAIN,20));
		
		MyHandle h=new MyHandle();
		Undo.addActionListener(h);
		Exit.addActionListener(h);
		Tie.addActionListener(h);
		Surrender.addActionListener(h);
		NewChess.addActionListener(h);
		
		
		//设置难度等级
		MyButtonGroup=new ButtonGroup();
		Simple=new JRadioButton("简单",true);
		Middle=new JRadioButton("中等");
		Difficult=new JRadioButton("困难");
		Simple.setFont(new Font("Serif",Font.PLAIN,15));	
		Middle.setFont(new Font("Serif",Font.PLAIN,15));	
		Difficult.setFont(new Font("Serif",Font.PLAIN,15));	
		MyButtonGroup.add(Simple);
		MyButtonGroup.add(Middle);
		MyButtonGroup.add(Difficult);
		Simple.setBounds((int)(chessboard.scrSize.width-chessboard.Left+115),(int)(chessboard.Low-50),60,30);
		Middle.setBounds((int)(chessboard.scrSize.width-chessboard.Left+185),(int)(chessboard.Low-50),60,30);
		Difficult.setBounds((int)(chessboard.scrSize.width-chessboard.Left+255),(int)(chessboard.Low-50),60,30);
		MySelectHandle Se=new MySelectHandle();
		Simple.addItemListener(Se);	
		Middle.addItemListener(Se);	
		Difficult.addItemListener(Se);
		
		//设置背景
		MyBack=new ButtonGroup();
		Back1=new JRadioButton("初始",true);
		Back2=new JRadioButton("经典");
		Back3=new JRadioButton("时尚");
		Back1.setFont(new Font("Serif",Font.PLAIN,15));	
		Back2.setFont(new Font("Serif",Font.PLAIN,15));	
		Back3.setFont(new Font("Serif",Font.PLAIN,15));	
		MyBack.add(Back1);
		MyBack.add(Back2);
		MyBack.add(Back3);
		Back1.setBounds((int)(chessboard.scrSize.width-chessboard.Left+115),(int)(chessboard.Low-100),60,30);
		Back2.setBounds((int)(chessboard.scrSize.width-chessboard.Left+185),(int)(chessboard.Low-100),60,30);
		Back3.setBounds((int)(chessboard.scrSize.width-chessboard.Left+255),(int)(chessboard.Low-100),60,30);
		MyMusicHandle Me=new MyMusicHandle();
		Back1.addItemListener(Me);	
		Back2.addItemListener(Me);	
		Back3.addItemListener(Me);
		
		//设置标签
		MyBackLabel=new JLabel("背景设置");
		MyBackLabel.setFont(new Font("Serif",Font.PLAIN,15));
		MyBackLabel.setBounds((int)(chessboard.scrSize.width-chessboard.Left+50),(int)(chessboard.Low-100),90,30);
		MyLabel=new JLabel("难度等级");
		MyLabel.setFont(new Font("Serif",Font.PLAIN,15));	
		MyLabel.setBounds((int)(chessboard.scrSize.width-chessboard.Left+50),(int)(chessboard.Low-50),90,30);
		
	}
	
	private class MySelectHandle implements ItemListener
	{
		public void itemStateChanged(ItemEvent select)
		{
			if(Simple.isSelected())
				chessimage.Rank=0;
			else if(Middle.isSelected())
				chessimage.Rank=1;
			else if(Difficult.isSelected())
				chessimage.Rank=2;
			chessposition tmp=new chessposition();
			tmp.display();
		}
	}
	
	private class MyMusicHandle implements ItemListener
	{
		public void itemStateChanged(ItemEvent select)
		{
			if(Back1.isSelected())
				{
				  chessimage.WitchMatch=0;
				  MainFrame.MyBackMusic.Stop();
				 MainFrame.MyBackMusic=new chessmusic("高山流水 - 古筝.wav");
				}
			else if(Back2.isSelected())
				{
				chessimage.WitchMatch=1;
				MainFrame.MyBackMusic.Stop();
				MainFrame.MyBackMusic=new chessmusic("笑傲江湖琴箫合奏 - 女子十二乐坊.wav");
				}
			else if(Back3.isSelected())
				{
				chessimage.WitchMatch=2;
				MainFrame.MyBackMusic.Stop();
				MainFrame.MyBackMusic=new chessmusic("赛马 - 女子十二乐坊.wav");
				}
			chessposition tmp=new chessposition();
			tmp.display();
		}
	}
	
	private class MyHandle implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource()==Exit)
			{
				System.exit(0);
			}
			else if(chessposition.CanGo&&event.getSource()==Surrender)
			{
				chessimage.Message="佩服,鄙人甘拜下风!";
				chessimage.IsSurrender=true;
				chessposition tmp=new chessposition();
				tmp.display();
				chessposition.CanGo=false;
			}
			else if(chessposition.CanGo&&event.getSource()==Tie)
			{
				chessimage.Message="承让,才女技高一筹!";
				chessimage.IsSurrender=true;
				chessposition tmp=new chessposition();
				tmp.display();
				chessposition.CanGo=false;
			}
			else if(chessposition.CanGo&&event.getSource()==Undo)
			{
				if(chessposition.MyChessList.GetSize()!=0)
				{
					chessposition.MyChessList.RemoveLast();
					chessposition tmp=new chessposition();
					tmp.display();
				}
			}
			else if(event.getSource()==NewChess)
			{
				chessimage.begin=new Date();
				int [][]Array=new int[15][15];
				chessposition.MyChessPiece.SetAllFlag(Array);
				chessimage.IsSurrender=false;
				chessimage.IsTie=false;
				chessimage.Message="";
				chessposition tmp=new chessposition();
				tmp.display();
				chessposition.CanGo=true;
				chessimage.CurrentStep=0;
			}

		}
	}
}
