import java.util.Comparator;

public class YComperator implements Comparator<Point> {

    @Override
	public int compare(Point p1,Point p2){
		if (p1.getY()<p2.getY()){
			return -1;
		}
		if (p2.getY()<p1.getY()){
			return 1;
		}
		return 0;
		
		
		
	}

}
