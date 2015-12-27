import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;


final public class chessimage{//230,230,250//169,169,169//0,206,209
	public static Date begin;//每局开始时间
	public static Date cur;//每局结束时间
	public static chessOneStep LineLeft;//结束端点1
	public static chessOneStep LineRight;//结束端点2
	public static boolean IsGameOver;//是否只有一方获胜
	public static int ColorOfBackGround[][]={{255, 227, 132},{0,255,127},{218,165,32}};//背景颜色
	public static int ColorOfWindows[][]={{ 60,179,113},{245,245,245},{122,122,122}};//背景颜色
	public static int WitchMatch;//背景搭配
	public static String MusicOfBackGround;//背景音乐
	public static String MusicOfVector;//取胜
	public static int CurrentStep;//记录当前步数
	public static int Rank;//设置难度等级
	public static boolean IsSurrender;//判断是否认输
	public static boolean IsTie;//判断是否认输
	public static String Message;
	public static Image IconImage;// 图标
	public static Image blackBoard;//白棋盘
	public static Image whiteBoard;//黑棋盘
	public static Image blackChess;// 白棋棋子图片 
	public static Image whiteChess;// 白棋棋子图片 
	public static Image RightPlayer;//白棋棋罐图片 
	public static Image LeftPlayer;//白棋玩家头像图片 
	//public static String path = "E:\\课件\\大三上学期\\Java语言程序设计\\我的Java测试代码\\我的五子棋程序\\src\\";// 图片的保存路径 
	public static String path = "src/";// 图片的保存路径 src/res/
	static {
		try {
			IsGameOver=false;
			begin=new Date();	
			CurrentStep=0;
			Rank=0;
			IsSurrender=false;
			IsTie=false;
			WitchMatch=0;
			Message="";
			blackBoard=ImageIO.read(new File(path + "黑棋盘.png"));
			whiteBoard=ImageIO.read(new File(path + "白棋盘.png"));
			
			IconImage = ImageIO.read(new File(path + "图标.jpg"));
			blackChess = ImageIO.read(new File(path + "黑棋 .png"));
			whiteChess = ImageIO.read(new File(path + "白棋.png"));
			LeftPlayer = ImageIO.read(new File(path + "弈者2.jpg"));
			RightPlayer= ImageIO.read(new File(path + "弈者1.jpg"));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
