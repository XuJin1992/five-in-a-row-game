import java.util.ArrayList;
import java.util.List;

public class chesslist {
	private List<chessOneStep> MyList = new ArrayList<chessOneStep>();

	public void AddStep(chessOneStep OneStep) {
		MyList.add(OneStep);
	}

	public int GetSize() {
		return MyList.size();
	}

	public void ClearList()
	{
		MyList.clear();
	}
	
	public void RemoveLast() {
		
		chessOneStep OneStep = MyList.get(GetSize()-1);
		int x = OneStep.GetX();
		int y = OneStep.GetY();
		chessposition.MyChessPiece.SetPositionFlag(x, y, 0);
		MyList.remove(GetSize() - 1);
		chessimage.CurrentStep--;
		if(GetSize()%2!=0)//说明最上面是人控制的棋
		{		
			OneStep = MyList.get(GetSize()-1);
			x = OneStep.GetX();
			y = OneStep.GetY();
			chessposition.MyChessPiece.SetPositionFlag(x, y, 0);
			MyList.remove(GetSize() - 1);
			chessimage.CurrentStep--;
		}
	}
}
