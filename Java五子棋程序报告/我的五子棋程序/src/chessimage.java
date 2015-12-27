import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;


final public class chessimage{//230,230,250//169,169,169//0,206,209
	public static Date begin;//ÿ�ֿ�ʼʱ��
	public static Date cur;//ÿ�ֽ���ʱ��
	public static chessOneStep LineLeft;//�����˵�1
	public static chessOneStep LineRight;//�����˵�2
	public static boolean IsGameOver;//�Ƿ�ֻ��һ����ʤ
	public static int ColorOfBackGround[][]={{255, 227, 132},{0,255,127},{218,165,32}};//������ɫ
	public static int ColorOfWindows[][]={{ 60,179,113},{245,245,245},{122,122,122}};//������ɫ
	public static int WitchMatch;//��������
	public static String MusicOfBackGround;//��������
	public static String MusicOfVector;//ȡʤ
	public static int CurrentStep;//��¼��ǰ����
	public static int Rank;//�����Ѷȵȼ�
	public static boolean IsSurrender;//�ж��Ƿ�����
	public static boolean IsTie;//�ж��Ƿ�����
	public static String Message;
	public static Image IconImage;// ͼ��
	public static Image blackBoard;//������
	public static Image whiteBoard;//������
	public static Image blackChess;// ��������ͼƬ 
	public static Image whiteChess;// ��������ͼƬ 
	public static Image RightPlayer;//�������ͼƬ 
	public static Image LeftPlayer;//�������ͷ��ͼƬ 
	//public static String path = "E:\\�μ�\\������ѧ��\\Java���Գ������\\�ҵ�Java���Դ���\\�ҵ����������\\src\\";// ͼƬ�ı���·�� 
	public static String path = "src/";// ͼƬ�ı���·�� src/res/
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
			blackBoard=ImageIO.read(new File(path + "������.png"));
			whiteBoard=ImageIO.read(new File(path + "������.png"));
			
			IconImage = ImageIO.read(new File(path + "ͼ��.jpg"));
			blackChess = ImageIO.read(new File(path + "���� .png"));
			whiteChess = ImageIO.read(new File(path + "����.png"));
			LeftPlayer = ImageIO.read(new File(path + "����2.jpg"));
			RightPlayer= ImageIO.read(new File(path + "����1.jpg"));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
