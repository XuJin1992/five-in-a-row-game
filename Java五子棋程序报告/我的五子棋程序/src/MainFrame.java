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
				chessimage.ColorOfBackGround[chessimage.WitchMatch][2]));// ���ñ���ɫ
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĭ�Ϲرշ�ʽ 
		setSize(chessboard.scrSize.width-10,chessboard.scrSize.height-15);//���ô��ڴ�С 
		setIconImage(chessimage.IconImage);
		setLocationRelativeTo(null);// ���ô��ڳ�ʼλ�� 
		setTitle("��  ӭ  ��  ��  ��  ��  ��  ��  ��  ��  ��");//���ô��ڱ��� 
		
		//�������
		chesspanel Mychesspanel=new chesspanel(MyChessBoard,MyChessPiece);
		Mychesspanel.setLayout(null);
		Mychesspanel.add(MyChessButton.Undo);
		Mychesspanel.add(MyChessButton.Exit);
		Mychesspanel.add(MyChessButton.Surrender);
		Mychesspanel.add(MyChessButton.Tie);
		Mychesspanel.add(MyChessButton.NewChess);
		Mychesspanel.add(MyChessButton.Simple);//����Ѷȵȼ���ѡ��ť	
		Mychesspanel.add(MyChessButton.Middle);
		Mychesspanel.add(MyChessButton.Difficult);
		Mychesspanel.add(MyChessButton.Back1);//��ӱ������õ�ѡ��ť	
		Mychesspanel.add(MyChessButton.Back2);
		Mychesspanel.add(MyChessButton.Back3);
		
		//��ӱ�ǩ
		Mychesspanel.add(MyChessButton.MyLabel);
		Mychesspanel.add(MyChessButton.MyBackLabel);
		
		add(Mychesspanel);
		
		chessposition MyChessPosition=new chessposition(Mychesspanel,MyChessBoard,MyChessPiece);
		addMouseListener(MyChessPosition);
		
		MyBackMusic=new chessmusic("��ɽ��ˮ - ����.wav");//////////
		
		setVisible(true);//���ô���͸��
		setResizable(false);//���ô��ڴ�С���ɱ� 
	}

	public static void main(String[] str) {
	    new MainFrame();
	}
}
