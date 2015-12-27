import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public class chessboard {
	public static double Inc, Left, Low;
	public static Dimension scrSize;

	public chessboard() {
		scrSize = Toolkit.getDefaultToolkit().getScreenSize();// 获得屏幕尺寸
		Inc = (scrSize.height - 100) / 14.0;// 设置格子间距
		Left = (scrSize.width - scrSize.height + 100) / 2.0;// 设置左右边框
		Low = scrSize.height - 100;//75;//棋盘下边缘
	}

	public void DrawChessBoard(Graphics g) {


		g.setColor(new Color(chessimage.ColorOfWindows[chessimage.WitchMatch][0],
				chessimage.ColorOfWindows[chessimage.WitchMatch][1],
				chessimage.ColorOfWindows[chessimage.WitchMatch][2]));//设置边框
		
		//窗口
		g.fillRect(0,0,scrSize.width-15,15);//上边框
		g.fillRect(0, (int) (25 + 14 * Inc+1),(int) (scrSize.width), 10);//下边框
		g.fillRect(0,0,15,scrSize.height-5);//左边框
		g.fillRect(scrSize.width-35,0,15,scrSize.height-5);//右边框
		
		//棋盘
		g.fillRect((int)(Left-35),0,15,(int)(25+14*Inc)+2);//左边框
		g.fillRect((int) (Left +14 * Inc+20),0,15,(int)(25+14*Inc));//右边框
		
		//弈者
		g.fillRect(10,(int)(10+14*Inc),(int)(Left-45) ,20);//左边人的下角
		g.fillRect((int) (Left +14 *Inc+20),(int)(10+14*Inc),(int)(Left-45) ,20);//右边人的下角
		
		g.setColor(Color.green);
		g.fillRect(0, (int) (25 + 14 * Inc+11) ,(int)(scrSize.width), 10);//模拟任务栏下边框
		
		g.setColor(Color.black);
		for (int NumX = 0; NumX < 15; ++NumX)// 绘制纵线
		{
			g.drawLine((int) (Left + NumX * Inc), 25,(int) (Left + NumX * Inc), scrSize.height - 75);
			g.drawLine((int) (Left + NumX * Inc+1), 25,(int) (Left + NumX * Inc+1), scrSize.height - 75);
		}

		//设置双线边框
		g.drawLine((int) (Left -2), 23,(int) (Left -2), scrSize.height - 73);
		g.drawLine((int) (Left +Inc*14+3), 23,(int) (Left+Inc*14+3), scrSize.height - 73);
		g.drawLine((int) Left-1, (int) (25 -3),(int) (scrSize.width - Left+1), (int) (25-3));
		g.drawLine((int) Left-1, (int) (25 + 14 * Inc+3),(int) (scrSize.width - Left+1), (int) (25 + 14 * Inc+3));
		
		for (int NumY = 0; NumY < 15; ++NumY)// 绘制横线
		{
			g.drawLine((int) Left, (int) (25 + NumY * Inc),(int) (scrSize.width - Left), (int) (25 + NumY * Inc));
			g.drawLine((int) Left, (int) (25 + NumY * Inc+1),(int) (scrSize.width - Left), (int) (25 + NumY * Inc+1));
		}
		
		//绘制五个点
		g.fillOval((int)(Left+3*Inc-5),(int)(25 + 3* Inc-5),10,10);
		g.drawOval((int)(Left+3*Inc-7),(int)(25 + 3* Inc-7),15,15); 
		g.drawOval((int)(Left+3*Inc-8),(int)(25 + 3* Inc-8),16,16); 
		
		g.fillOval((int)(Left+11*Inc-5),(int)(25 + 3* Inc-5),10,10); 
		g.drawOval((int)(Left+11*Inc-7),(int)(25 + 3* Inc-7),15,15); 
		g.drawOval((int)(Left+11*Inc-8),(int)(25 + 3* Inc-8),16,16); 
		
		g.fillOval((int)(Left+3*Inc-5),(int)(25 + 11* Inc-5),10,10); 
		g.drawOval((int)(Left+3*Inc-7),(int)(25 + 11* Inc-7),15,15); 
		g.drawOval((int)(Left+3*Inc-8),(int)(25 + 11* Inc-8),16,16); 
		
		g.fillOval((int)(Left+11*Inc-5),(int)(25 + 11* Inc-5),10,10); 
		g.drawOval((int)(Left+11*Inc-7),(int)(25 + 11* Inc-7),15,15); 
		g.drawOval((int)(Left+11*Inc-8),(int)(25 + 11* Inc-8),16,16); 
		
		g.fillOval((int)(Left+7*Inc-5),(int)(25 + 7* Inc-5),10,10); 
		g.drawOval((int)(Left+7*Inc-7),(int)(25 + 7* Inc-7),15,15); 
		g.drawOval((int)(Left+7*Inc-8),(int)(25 + 7* Inc-8),16,16); 
	}
}
