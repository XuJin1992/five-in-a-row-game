import java.awt.Graphics;

public class chesspiece {

	private int[][] PositionFlag = new int[15][15];// ��ʾ�����ϵ��������ͣ�0��ʾ���ӣ�1��ʾ����

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

	public void DrawChessPiece(Graphics g) {// ������

		for (int i = 0; i < 15; i++) {// ɨ�����������е�����
			for (int j = 0; j < 15; j++) {
				int x = (int) (chessboard.Left) + i * (int) (chessboard.Inc)
						- 15;// �������������ж�Ӧ���±�ת��������Ϸ�е�����
				int y = 25 + j * (int) (chessboard.Inc) - 15;
				if (GetPositionFlag(i, j) == 1) {// ���ָ��λ�õ������Ǻ�ɫ����
					g.drawImage(chessimage.whiteChess, x, y, null);
				} else if (GetPositionFlag(i, j) == 2) {// ���ָ��λ�õ������ǰ�ɫ���ӣ�
					g.drawImage(chessimage.blackChess, x, y, null);
				}
			}
		}
	}
}
