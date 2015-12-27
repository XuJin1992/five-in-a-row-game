import java.awt.Color;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static chessmusic MyBackMusic;/////////

	public MainFrame() {
		chessboard MyChessBoard = new chessboard();
		chessbutton MyChessButton = new chessbutton();	
		chesspiece MyChessPiece=new chesspiece();
		
		setBackground(new Color(chessimage.ColorOfBackGround[chessimage.WitchMatch][0], 
				chessimage.ColorOfBackGround[chessimage.WitchMatch][1],
				chessimage.ColorOfBackGround[chessimage.WitchMatch][2]));// 设置背景色
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口默认关闭方式 
		setSize(chessboard.scrSize.width-10,chessboard.scrSize.height-15);//设置窗口大小 
		setIconImage(chessimage.IconImage);
		setLocationRelativeTo(null);// 设置窗口初始位置 
		setTitle("欢  迎  来  到  我  的  五  子  棋  界  面");//设置窗口标题 
		
		//创建面板
		chesspanel Mychesspanel=new chesspanel(MyChessBoard,MyChessPiece);
		Mychesspanel.setLayout(null);
		Mychesspanel.add(MyChessButton.Undo);
		Mychesspanel.add(MyChessButton.Exit);
		Mychesspanel.add(MyChessButton.Surrender);
		Mychesspanel.add(MyChessButton.Tie);
		Mychesspanel.add(MyChessButton.NewChess);
		Mychesspanel.add(MyChessButton.Simple);//添加难度等级单选按钮	
		Mychesspanel.add(MyChessButton.Middle);
		Mychesspanel.add(MyChessButton.Difficult);
		Mychesspanel.add(MyChessButton.Back1);//添加背景设置单选按钮	
		Mychesspanel.add(MyChessButton.Back2);
		Mychesspanel.add(MyChessButton.Back3);
		
		//添加标签
		Mychesspanel.add(MyChessButton.MyLabel);
		Mychesspanel.add(MyChessButton.MyBackLabel);
		
		add(Mychesspanel);
		
		chessposition MyChessPosition=new chessposition(Mychesspanel,MyChessBoard,MyChessPiece);
		addMouseListener(MyChessPosition);
		
		MyBackMusic=new chessmusic("高山流水 - 古筝.wav");//////////
		
		setVisible(true);//设置窗口透明
		setResizable(false);//设置窗口大小不可变 
	}

	public static void main(String[] str) {
	    new MainFrame();
	}
}
