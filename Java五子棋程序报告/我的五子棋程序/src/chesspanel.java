import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.Date;
import javax.swing.JPanel;

public class chesspanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private chessboard MyChessBoard = new chessboard();
	private chesspiece MyChessPiece = new chesspiece();

	public chesspanel(chessboard MyChessBoard1, chesspiece MyChessPiece1) {
		MyChessBoard = MyChessBoard1;
		MyChessPiece = MyChessPiece1;
	}

	public void display(chessboard MyChessBoard1, chesspiece MyChessPiece1) {
		MyChessBoard = MyChessBoard1;
		MyChessPiece = MyChessPiece1;
		this.repaint();
	}

	public void paintComponent(Graphics g) {// paint(Graphics g)//
											// {//��ʱ������������ֻ����꾭���ǲ���ʾbutton
		super.paintComponent(g);// �����������
		setBackground(new Color(
				chessimage.ColorOfBackGround[chessimage.WitchMatch][0],
				chessimage.ColorOfBackGround[chessimage.WitchMatch][1],
				chessimage.ColorOfBackGround[chessimage.WitchMatch][2]));// ���ñ���ɫ

		if (MyChessBoard != null && MyChessPiece != null) {

			MyChessBoard.DrawChessBoard(g);// �������̱���
			MyChessPiece.DrawChessPiece(g);// ������������
		}

		// �����������
		g.drawImage(chessimage.LeftPlayer, 25, 25, this);
		g.drawImage(chessimage.RightPlayer, (int) (chessboard.scrSize.width
				- chessboard.Left + 50), 25, this);

		g.drawImage(chessimage.whiteBoard, 25, 25, this);
		g.drawImage(chessimage.blackBoard, (int) (chessboard.scrSize.width
				- chessboard.Left + 250), 25, this);

		if (chessimage.Message != "") {
			if (chessimage.IsGameOver) {
				Graphics2D g2d = (Graphics2D) g;
				Stroke stroke = g2d.getStroke();
				g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_SQUARE,
						BasicStroke.JOIN_ROUND));
				g2d.setColor(Color.pink);
				g2d.draw(new Line2D.Float(
						(float) (chessboard.Left + chessboard.Inc
								* chessimage.LineLeft.GetX()),
						(float) (25 + chessboard.Inc
								* chessimage.LineLeft.GetY()),
						(float) (chessboard.Left + chessboard.Inc
								* chessimage.LineRight.GetX()),
						(float) (25 + chessboard.Inc
								* chessimage.LineRight.GetY())));// ��������
				g2d.setStroke(stroke);

			}
			g.setColor(Color.red);
			g.setFont(new Font("����", Font.BOLD, 86));
			g.drawString(chessimage.Message, (int) (chessboard.Left - 50),
					(int) (chessboard.Low - 7 * chessboard.Inc));
		}
		// ������Ϸʱ��
		g.setColor(Color.blue);
		g.fillRect((int) (chessboard.Left + 260), 0, 140, 20);// ����˵��½�
		g.setColor(Color.yellow);
		g.setFont(new Font("����", Font.BOLD, 20));
		chessimage.cur = new Date();
		Long m = chessimage.cur.getTime() - chessimage.begin.getTime();
		Long H = m / (60 * 60 * 1000);
		m = m % (60 * 60 * 1000);
		Long M = m / (60 * 1000);
		m = m % (60 * 1000);
		m = m / 1000;
		String dif = "ʱ��" + H + ":" + M + ":" + m;
		g.drawString(dif, (int) (chessboard.Left + 280), 20);
	}
}
