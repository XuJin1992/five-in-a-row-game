import java.awt.Graphics;

public class chesspiece {

	private int[][] PositionFlag = new int[15][15];// 表示格子上的棋子类型，0表示黑子，1表示白字

	public void SetPositionFlag(int x, int y, int flag) {
		PositionFlag[x][y] = flag;
	}

	public void SetAllFlag(int[][] NewFlag) {
		PositionFlag = NewFlag;
	}

	public int GetPositionFlag(int x, int y) {
		return PositionFlag[x][y];
	}

	public int[][] GetAllFlag() {
		return PositionFlag;
	}

	public void DrawChessPiece(Graphics g) {// 画棋子

		for (int i = 0; i < 15; i++) {// 扫描棋盘中所有的棋子
			for (int j = 0; j < 15; j++) {
				int x = (int) (chessboard.Left) + i * (int) (chessboard.Inc)
						- 15;// 把棋子在棋盘中对应的下标转化成在游戏中的坐标
				int y = 25 + j * (int) (chessboard.Inc) - 15;
				if (GetPositionFlag(i, j) == 1) {// 如果指定位置的棋子是黑色棋子
					g.drawImage(chessimage.whiteChess, x, y, null);
				} else if (GetPositionFlag(i, j) == 2) {// 如果指定位置的棋子是白色棋子，
					g.drawImage(chessimage.blackChess, x, y, null);
				}
			}
		}
	}
}
