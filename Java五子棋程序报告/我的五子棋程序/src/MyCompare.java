import java.util.Comparator;


public class MyCompare implements Comparator<chessOneStep>{

	public int compare(chessOneStep arg0, chessOneStep arg1) {
		if(arg0.GetWeight()>arg1.GetWeight())
			return 1;
		else if(arg0.GetWeight()<arg1.GetWeight()) 
			return -1;
		else return 0;
	}

}
