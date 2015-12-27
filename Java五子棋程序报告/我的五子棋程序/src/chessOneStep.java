public class chessOneStep {
	private int x;
	private int y;
	private int Weight;
	
	public chessOneStep(int tx,int ty,int tWeight)
	{
		x=tx;
		y=ty;
		Weight=tWeight;
	}
	
	public int GetWeight()
	{
		return Weight;
	}
	
	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
}
