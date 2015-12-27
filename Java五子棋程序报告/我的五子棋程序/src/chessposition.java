import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;


public class chessposition extends MouseAdapter {
	private static chessboard MyChessBoard;
	public static chesspiece MyChessPiece;
	private static chesspanel Mychesspanel;
	public static chesslist MyChessList=new chesslist();
	final private static int INF = (1 << 30); // 表示正无穷大的常量
	public static boolean CanGo;
	
	public chessposition(chesspanel Mychesspanel1, chessboard MyChessBoard1,
		chesspiece MyChessPiece1) {
		chessposition.Mychesspanel = Mychesspanel1;
		chessposition.MyChessBoard = MyChessBoard1;
		chessposition.MyChessPiece = MyChessPiece1;
		CanGo=true;
	}
	public chessposition() {}

	public void mouseClicked(MouseEvent event) {
		if(!CanGo)return ;	
		// 获取鼠标点击的棋盘相对位置
		int x = event.getX() - (int) (chessboard.Left);
		int y = event.getY() - 25;
		int Max = (int) (chessboard.Inc * 14) + 39;// 棋盘最右、下边
		int Min = 0;// 棋盘最左、上边
		if ((x < Min) || (x > Max) || (y < Min) || (y > Max))// 无效棋子
			return;
		
		int Inc = (int) chessboard.Inc;
		int NextX=x / Inc;
		int NextY=y / Inc;
		new chessmusic("下棋.wav");
		if(0!=MyChessPiece.GetPositionFlag(NextX,NextY))
		{
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "您的走法违规，该处已有棋子！","温馨提示",JOptionPane.ERROR_MESSAGE);
			return ;
		}
		chessOneStep OneStep=new chessOneStep(NextX,NextY,0);
		MyChessList.AddStep(OneStep);//保留当前下得棋
	    MyChessPiece.SetPositionFlag(NextX, NextY, 1);
	    chessimage.CurrentStep++;
		if(chessimage.CurrentStep==225)
		{
			chessimage.Message = "伯仲之间 ,胜负难分!";
			CanGo=false;
		}
		if(IsOver(MyChessPiece.GetAllFlag(),NextX, NextY))
			new chessmusic("取胜.wav");
	    display();	
		if(0==chessimage.Rank)GetGreedNext();
		else if(1==chessimage.Rank)GetSearchNext(1);
		else if(2==chessimage.Rank)GetMinMaxsearchNext(3);
	}
	
	//计算机采用一步攻防贪心策略下棋
	public void GetGreedNext()
	{
		int NextX,NextY;	
		if(!CanGo)return ;
		
		//完全裸下
		int MaxWei=-INF;
		int idX=-1;
		int idY=-1;
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(0==MyChessPiece.GetPositionFlag(i,j))
				{
					  MyChessPiece.SetPositionFlag(i,j, 2);
					  int tmp=Evaluate(MyChessPiece.GetAllFlag(),i,j);
					  if(tmp>=MaxWei)
					  {
						  MaxWei=tmp;
						  idX=i;idY=j;
					  }
					  
					  MyChessPiece.SetPositionFlag(i,j, 1);
					  tmp=Evaluate(MyChessPiece.GetAllFlag(),i,j);
					  if(tmp>MaxWei)
					  {
						  MaxWei=tmp;
						  idX=i;idY=j;
					  }
					  MyChessPiece.SetPositionFlag(i,j,0);
				}
			}
		}
		NextX=idX;NextY=idY;
		new chessmusic("下棋.wav");
		if(-1==NextX&&-1==NextY)
		{
			if(chessimage.CurrentStep==225)
			{
				chessimage.Message = "伯仲之间 ,胜负难分!";
				CanGo=false;
			}
			return ;
		}
		chessimage.CurrentStep++;
		chessOneStep OneStep=new chessOneStep(NextX,NextY,0);
		MyChessList.AddStep(OneStep);//保留当前下得棋
	    MyChessPiece.SetPositionFlag(NextX, NextY, 2);
	    if(IsOver(MyChessPiece.GetAllFlag(),NextX, NextY))
			new chessmusic("取胜.wav");
		display();
	}

	public void display() {// 用于重新显示游戏界面
		Mychesspanel.display(MyChessBoard, MyChessPiece);
	}

	//添加棋子后只需判断水平、竖直、成45、135度角上是否连成5个
	public boolean IsOver(int[][] Array,int x,int y)
	{
		boolean flag=false;
		int num=1;
		int k=1;
		while(x-k>=0&&Array[x][y]==Array[x-k][y])
		{
			num++;
			k++;
		}
		chessimage.LineLeft=new chessOneStep(x-k+1,y,0);	
		k=1;
		while(x+k<15&&Array[x][y]==Array[x+k][y])
		{
			num++;
			k++;
		}
		chessimage.LineRight=new chessOneStep(x+k-1,y,0);
		if(num>=5)
			flag=true;
		
		if(!flag)
		{
			num=1;
			k=1;
			while(y-k>=0&&Array[x][y]==Array[x][y-k])
			{
				num++;
				k++;
			}
			chessimage.LineLeft=new chessOneStep(x,y-k+1,0);
			k=1;
			while(y+k<15&&Array[x][y]==Array[x][y+k])
			{
				num++;
				k++;
			}
			chessimage.LineRight=new chessOneStep(x,y+k-1,0);
			if(num>=5)
				flag=true;
		}
		
		if(!flag)
		{
			num=1;
			k=1;
			while(y-k>=0&&x-k>=0&&Array[x][y]==Array[x-k][y-k])
			{
				num++;
				k++;
			}
			chessimage.LineLeft=new chessOneStep(x-k+1,y-k+1,0);
			k=1;
			while(y+k<15&&x+k<15&&Array[x][y]==Array[x+k][y+k])
			{
				num++;
				k++;
			}
			chessimage.LineRight=new chessOneStep(x+k-1,y+k-1,0);
			if(num>=5)
				flag=true;
		}
		if(!flag)
		{
			num=1;
			k=1;
			while(y+k<15&&x-k>=0&&Array[x][y]==Array[x-k][y+k])
			{
				num++;
				k++;
			}
			chessimage.LineLeft=new chessOneStep(x-k+1,y+k-1,0);
			k=1;
			while(y-k>=0&&x+k<15&&Array[x][y]==Array[x+k][y-k])
			{
				num++;
				k++;
			}
			chessimage.LineRight=new chessOneStep(x+k-1,y-k+1,0);
			if(num>=5)
				flag=true;
		}
		if(flag)
		{
			chessimage.IsGameOver=true;
			if(1==Array[x][y])
				chessimage.Message = "获胜了，恭喜您!";
			else 
				chessimage.Message = "失败了，振作点!";
			CanGo=false;
		}
		return flag;
	}

	
	public int GetMax(int a,int b)
	{
		return a<b?b:a;
	}
	
	// 预先设定一些规则估值,对已连成一片的
		public int GetValue(int flag, int num) {
			int ret = 0;
			if(1==num)ret=0;
			if (2 == num) {
				if (0 == flag)// 死2
					ret = 3;
				else if (1 == flag)// 单活2
					ret = 50;
				else
					ret = 100;// 双活2
			} else if (3 == num) {
				if (0 == flag)// 死3
					ret = 5;
				else if (1 == flag)// 单活3
					ret = 200;
				else
					ret = 5000;// 双活3
			} else if (4 == num) {
				if (0 == flag)// 死4
					ret = 10;
				else if (1 == flag)// 单活4
					ret = 8000;
				else ret=500000;
			} else if (5 == num) {
				ret = 10000000;
			}
			return ret;
		}
		
		
	//对未连成一片但通过再下一颗子就能连成一片的局面进行估值
	public int GetPredictValue(int flag, int num)
	{
		int ret=0;
		if(0==flag||num<=2)
			ret=0;
		else 
		{
			if(1==flag)
			{
				if(3==num)
					ret=10;
			   else if(4==num)
				   ret=50;
			  else ret=200;
			}
			else 
			{
				if(3==num)
					ret=100;
			   else if(4==num)
				   ret=5000;
			  else ret=8000;
			}
		}
		return ret;
	}
	
	// 以下棋点为中心，查看总得分，此评判方法为贪心法
	public int Evaluate(int[][] Array, int x, int y) {
		int ret = 0;
		int num, k,tag;
		boolean lflag,rflag;
		
		//先估值一连成一片的
		// 水平线
		k = 1;
		num = 1;
		lflag=true;rflag=true;
		while (x - k >= 0 && Array[x][y] == Array[x - k][y]) {
			num++;
			k++;
		}
		if (!(x - k >= 0&&0==Array[x - k][y]))
			lflag=false;
		k = 1;
		while (x + k < 15 && Array[x][y] == Array[x + k][y]) {
			num++;
			k++;
		}
		if (!(x+k <15&&0==Array[x +k][y]))
			rflag=false;
		num = (num < 5 ? num : 5);
		if(lflag&&rflag)
		{
			tag=2;
		}
		else 
		{
			if(lflag||rflag)
				tag=1;
			else tag=0;
		}
		ret += GetValue(tag, num);
		
		//竖直线
		k = 1;
		num = 1;
		lflag=true;rflag=true;
		while (y - k >= 0 && Array[x][y] == Array[x][y - k]) {
			num++;
			k++;
		}
		if (!(y- k >= 0&&0==Array[x ][y-k]))
			lflag=false;
		k = 1;
		while (y + k < 15 && Array[x][y] == Array[x][y + k]) {
			num++;
			k++;
		}
		if (!(y+k <15&&0==Array[x][y+k]))
			rflag=false;
		num = (num < 5 ? num : 5);
		if(lflag&&rflag)
		{
			tag=2;
		}
		else 
		{
			if(lflag||rflag)
				tag=1;
			else tag=0;
		}
		ret += GetValue(tag, num);

		//135度
		k = 1;
		num = 1;
		lflag=true;rflag=true;
		while (y - k >= 0 && x - k >= 0 && Array[x][y] == Array[x - k][y - k]) {
			num++;
			k++;
		}
		if (!(y- k >= 0&&x-k>=0&&0==Array[x-k ][y-k]))
			lflag=false;
		k = 1;
		while (y + k < 15 && x + k < 15 && Array[x][y] == Array[x + k][y + k]) {
			num++;
			k++;
		}
		if (!(y+ k <15&&x+k <15&&0==Array[x +k][y+k]))
			rflag=false;
		num = (num < 5 ? num : 5);
		if(lflag&&rflag)
		{
			tag=2;
		}
		else 
		{
			if(lflag||rflag)
				tag=1;
			else tag=0;
		}
		ret += GetValue(tag, num);
		
		//45度
		k = 1;
		num = 1;
		lflag=true;rflag=true;
		while (y + k < 15 && x - k >= 0 && Array[x][y] == Array[x - k][y + k]) {
			num++;
			k++;
		}
		if (!(y+ k <15&&x-k>=0&&0==Array[x -k][y+k]))
			lflag=false;
		k = 1;
		while (y - k >= 0 && x + k < 15 && Array[x][y] == Array[x + k][y - k]) {
			num++;
			k++;
		}
		if (!(y- k >=0&&x+k <15&&0==Array[x +k][y-k]))
			rflag=false;
		num = (num < 5 ? num : 5);
		if(lflag&&rflag)
		{
			tag=2;
		}
		else 
		{
			if(lflag||rflag)
				tag=1;
			else tag=0;
		}
		ret += GetValue(tag, num);
		
		
		//能成连成一片的
		// 水平线
		int add;
		int leftadd, rightadd;
		boolean leftflag, rightflag;
		int lvalue, rvalue;
		k = 1;
		num = 1;
		lflag = true;
		rflag = true;
		leftflag=true;
		rightflag=true;
		leftadd = 0;
		rightadd = 0;
		while (x - k >= 0 && Array[x][y] == Array[x - k][y]) {
			num++;
			k++;
		}
		if (!(x - k >= 0 && 0 == Array[x - k][y]))
			lflag = false;
		else {
			
			add = k +1;// 跳过空格
			while (x - add >= 0 && Array[x][y] == Array[x - add][y]) {
				leftadd++;
				add++;
			}
			if (!(x - add >= 0 && 0 == Array[x - add][y]))// 堵死了
				leftflag = false;
		}

		k = 1;
		while (x + k < 15 && Array[x][y] == Array[x + k][y]) {
			num++;
			k++;
		}
		if (!(x + k < 15 && 0 == Array[x + k][y]))
			rflag = false;
		else {
			add = k + 1;// 跳过空格
			while (x + add < 15 && Array[x][y] == Array[x + add][y]) {
				rightadd++;
				add++;
			}
			if (!(x + add < 15 && 0 == Array[x + add][y]))// 堵死了
				rightflag = false;
		}

		if (leftflag && rflag) {
			tag = 2;
		} else {
			if (leftflag || rflag)
				tag = 1;
			else
				tag = 0;
		}
		lvalue = GetPredictValue(tag, num + 1 + leftadd);

		if (lflag && rightflag) {
			tag = 2;
		} else {
			if (lflag || rightflag)
				tag = 1;
			else
				tag = 0;
		}
		rvalue = GetPredictValue(tag, num + 1 + rightadd);
		ret += GetMax(lvalue, rvalue);

		// 竖直线
		k = 1;
		num = 1;
		lflag = true;
		rflag = true;
		leftflag=true;
		rightflag=true;
		leftadd = 0;
		rightadd = 0;
		while (y - k >= 0 && Array[x][y] == Array[x][y - k]) {
			num++;
			k++;
		}
		if (!(y - k >= 0 && 0 == Array[x][y - k]))
			lflag = false;
	else {
			
			add = k +1;// 跳过空格
			while (y - add >= 0 && Array[x][y] == Array[x ][y- add]) {
				leftadd++;
				add++;
			}
			if (!(y - add >= 0 && 0 == Array[x ][y- add]))// 堵死了
				leftflag = false;
		}
		
		
		k = 1;
		while (y + k < 15 && Array[x][y] == Array[x][y + k]) {
			num++;
			k++;
		}
		if (!(y + k < 15 && 0 == Array[x][y + k]))
			rflag = false;
		else {
			add = k + 1;// 跳过空格
			while (y + add < 15 && Array[x][y] == Array[x ][y+ add]) {
				rightadd++;
				add++;
			}
			if (!(y + add < 15 && 0 == Array[x ][y+ add]))// 堵死了
				rightflag = false;
		}
		
		if (leftflag && rflag) {
			tag = 2;
		} else {
			if (leftflag || rflag)
				tag = 1;
			else
				tag = 0;
		}
		lvalue = GetPredictValue(tag, num + 1 + leftadd);

		if (lflag && rightflag) {
			tag = 2;
		} else {
			if (lflag || rightflag)
				tag = 1;
			else
				tag = 0;
		}
		rvalue = GetPredictValue(tag, num + 1 + rightadd);
		ret += GetMax(lvalue, rvalue);

		
		// 135度
		k = 1;
		num = 1;
		lflag = true;
		rflag = true;
		leftflag=true;
		rightflag=true;
		leftadd = 0;
		rightadd = 0;
		while (y - k >= 0 && x - k >= 0 && Array[x][y] == Array[x - k][y - k]) {
			num++;
			k++;
		}
		if (!(y - k >= 0 && x - k >= 0 && 0 == Array[x - k][y - k]))
			lflag = false;
		else {
			add = k +1;// 跳过空格
			while (y - add >= 0 && x - add >= 0 && Array[x][y] == Array[x - add][y - add]) {
				rightadd++;
				add++;
			}
			if (!(y - add >= 0 && x -add >= 0 && 0 == Array[x - add][y - add]))// 堵死了
				rightflag = false;
		}
		
		k = 1;
		while (y + k < 15 && x + k < 15 && Array[x][y] == Array[x + k][y + k]) {
			num++;
			k++;
		}
		if (!(y + k < 15 && x + k < 15 && 0 == Array[x + k][y + k]))
			rflag = false;
		else {
			add = k +1;// 跳过空格
			while (y + add < 15 && x + add < 15 && Array[x][y] == Array[x + add][y + add]) {
				rightadd++;
				add++;
			}
			if (!(y + add < 15 && x + add < 15 && 0 == Array[x + add][y +add]))// 堵死了
				rightflag = false;
		}
		
		if (leftflag && rflag) {
			tag = 2;
		} else {
			if (leftflag || rflag)
				tag = 1;
			else
				tag = 0;
		}
		lvalue = GetPredictValue(tag, num + 1 + leftadd);

		if (lflag && rightflag) {
			tag = 2;
		} else {
			if (lflag || rightflag)
				tag = 1;
			else
				tag = 0;
		}
		rvalue = GetPredictValue(tag, num + 1 + rightadd);
		ret += GetMax(lvalue, rvalue);

		k = 1;
		num = 1;
		leftflag=true;
		rightflag=true;
		leftadd = 0;
		rightadd = 0;
		while (y + k < 15 && x - k >= 0 && Array[x][y] == Array[x - k][y + k]) {
			num++;
			k++;
		}
		if (!(y + k < 15 && x - k >= 0 && 0 == Array[x - k][y + k]))
			lflag = false;
		else {
			add = k +1;// 跳过空格
			while (y + add < 15 && x -add >= 0 && Array[x][y] == Array[x - add][y + add]) {
				rightadd++;
				add++;
			}
			if (!(y + add < 15 && x - add >= 0 && 0 == Array[x -add][y + add]))// 堵死了
				rightflag = false;
		}
		
		k = 1;
		while (y - k >= 0 && x + k < 15 && Array[x][y] == Array[x + k][y - k]) {
			num++;
			k++;
		}
		if (!(y - k >= 0 && x + k < 15 && 0 == Array[x + k][y - k]))
			rflag = false;
		else {
			add = k +1;// 跳过空格
			while (y - add >= 0 && x + add < 15 && Array[x][y] == Array[x + add][y - add]) {
				rightadd++;
				add++;
			}
			if (!(y -add >= 0 && x +add < 15 && 0 == Array[x + add][y -add]))// 堵死了
				rightflag = false;
		}
		
		if (leftflag && rflag) {
			tag = 2;
		} else {
			if (leftflag || rflag)
				tag = 1;
			else
				tag = 0;
		}
		lvalue = GetPredictValue(tag, num + 1 + leftadd);

		if (lflag && rightflag) {
			tag = 2;
		} else {
			if (lflag || rightflag)
				tag = 1;
			else
				tag = 0;
		}
		rvalue = GetPredictValue(tag, num + 1 + rightadd);
		ret += GetMax(lvalue, rvalue);

		return ret;
	}

	//计算机人工智能中直接搜索下棋,向前看LookLength步
			public void GetSearchNext(int LookLength)
			{
				if(!CanGo)return ;
				chessOneStep Option=derectSearch(MyChessPiece.GetAllFlag(),true,LookLength);
				int NextX=Option.GetX();
				int NextY=Option.GetY();

				new chessmusic("下棋.wav");
				if(-1==NextX&&-1==NextY)
				{
					if(chessimage.CurrentStep==225)
					{
						chessimage.Message = "伯仲之间 ,胜负难分!";
						CanGo=false;
					}
					return ;
				}
				chessimage.CurrentStep++;
				chessOneStep OneStep=new chessOneStep(NextX,NextY,0);
				MyChessList.AddStep(OneStep);//保留当前下得棋
			    MyChessPiece.SetPositionFlag(NextX, NextY, 2);
			    if(IsOver(MyChessPiece.GetAllFlag(),NextX, NextY))
					new chessmusic("取胜.wav");
				display();
			}
			
			
			
			// 直接暴搜
			public chessOneStep derectSearch(int [][]Array,boolean who,int deepth)
			{
				if (0 == deepth)// 返回当前局面的评估函数值
				{
					int MaxWei=-INF;
					int idX=-1,idY = -1;
					for(int i=0;i<15;i++)
					{
						for(int j=0;j<15;j++)
						{
							// 5000,8000
							if(0==Array[i][j])
							{
								 Array[i][j]=2;
								 int tmp1=Evaluate(Array,i,j); 
								  Array[i][j]=1;
								  int tmp2=Evaluate(Array,i,j);   
								  if(tmp2>=10000000&&MaxWei<10000000)//机器未到死四且人到了活3
								  {
									  MaxWei=tmp2+10000000;
									  idX=i;idY=j;
								  }
								  else if(tmp2>=500000&&MaxWei<500000)
								  {
									  MaxWei=tmp2+500000;
									  idX=i;idY=j;
								  }
								  else if(tmp2>=10000&&MaxWei<10000)
								  {
									  MaxWei=tmp2+10000;
									  idX=i;idY=j;
								  }
								 
								  else if(tmp1>tmp2&&tmp1>MaxWei)
								  {
									  MaxWei=tmp1;
									  idX=i;idY=j;
								  }
								  else if(tmp2>tmp1&&tmp2>MaxWei)
								  {
									  MaxWei=tmp2;
									  idX=i;idY=j;
								  }
								  Array[i][j]=0; 
							}
						}
					}
					return new chessOneStep(idX,idY,MaxWei);	
				}
					chessOneStep ret=new chessOneStep(-1,-1,-INF);
					for(int i=0;i<15;i++)
					{
						for(int j=0;j<15;j++)
						{
							if(0==Array[i][j])
							{
								if(who)
								Array[i][j]=2;
								else Array[i][j]=1;
								chessOneStep tmp=derectSearch(Array,!who,deepth-1);
								Array[i][j]=0;
								if(tmp.GetWeight()>ret.GetWeight())
									ret=tmp;
							}
						}
					}
					return ret;
			}
		
		

	//计算机人工智能中极大极小法搜索下棋,向前看LookLength步
	public void GetMinMaxsearchNext(int LookLength)
	{
		chessOneStep Option=MinMaxsearch(MyChessPiece.GetAllFlag(),true,LookLength);
		
		int NextX=Option.GetX();
		int NextY=Option.GetY();

		new chessmusic("下棋.wav");
		if(-1==NextX&&-1==NextY)
		{
			if(chessimage.CurrentStep==225)
			{
				chessimage.Message = "伯仲之间 ,胜负难分!";
				CanGo=false;
			}
			return ;
		}
		chessimage.CurrentStep++;
		chessOneStep OneStep=new chessOneStep(NextX,NextY,0);
		MyChessList.AddStep(OneStep);//保留当前下得棋
	    MyChessPiece.SetPositionFlag(NextX, NextY, 2);
	    if(IsOver(MyChessPiece.GetAllFlag(),NextX, NextY))
			new chessmusic("取胜.wav");
		display();
	}
	
	
	// 极大极小博弈搜索
	public chessOneStep MinMaxsearch(int [][]Array,boolean who, int deepth) 
	{
		if (0 == deepth)// 返回当前局面的评估函数值
		{
			int MaxWei=-INF;
			int idX=-1,idY = -1;
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(0==Array[i][j])
					{
						 Array[i][j]=2;
						  int tmp=Evaluate(Array,i,j);
						  if(tmp>=MaxWei)
						  {
							  MaxWei=tmp;
							  idX=i;idY=j;
						  }
						  
						  Array[i][j]=1;
						  tmp=Evaluate(Array,i,j);
						  if(tmp>MaxWei)
						  {
							  MaxWei=tmp;
							  idX=i;idY=j;
						  }
						  Array[i][j]=0; 
					}
				}
			}
			return new chessOneStep(idX,idY,MaxWei);	
		}
		if (who)// 轮到己方,取极大值
		{
			chessOneStep ret=new chessOneStep(-1,-1,-INF);
			ArrayList<chessOneStep> TmpList = new ArrayList<chessOneStep>();
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(0==Array[i][j])
					{
						Array[i][j]=2;
						TmpList.add(new chessOneStep(i,j,Evaluate(Array,i,j)));
						Array[i][j]=0;
					}
				}
			}
			Collections.sort(TmpList, new MyCompare());
			int num=TmpList.size()<5?TmpList.size():5;
			for(int i=0;i<num;i++)
			{
				chessOneStep t=TmpList.get(i);
				Array[t.GetX()][t.GetY()]=2;
				chessOneStep tmp=MinMaxsearch(Array,!who, deepth - 1);
				if (tmp.GetWeight()>ret.GetWeight())
					ret=tmp;
				Array[t.GetX()][t.GetY()]=0;
			}
			return ret;
		} 
		else // 轮到对手，取极小值
		{
			chessOneStep ret=new chessOneStep(-1,-1,INF);
			ArrayList<chessOneStep> TmpList = new ArrayList<chessOneStep>();
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(0==Array[i][j])
					{
						Array[i][j]=1;
						TmpList.add(new chessOneStep(i,j,Evaluate(Array,i,j)));
						Array[i][j]=0;
					}
				}
			}
			Collections.sort(TmpList, new MyCompare());
			int num=TmpList.size()<5?TmpList.size():5;
			for(int i=0;i<num;i++)
			{
				chessOneStep t=TmpList.get(i);
				Array[t.GetX()][t.GetY()]=1;
				chessOneStep tmp=MinMaxsearch(Array,!who, deepth - 1);
				if (tmp.GetWeight()<ret.GetWeight())
					ret=tmp;
				Array[t.GetX()][t.GetY()]=0;
			}
			return ret;
		}
	}
	
}
