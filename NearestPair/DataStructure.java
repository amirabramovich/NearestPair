import java.util.Comparator;
import java.util.Arrays;


public class DataStructure implements DT {

	public DataStructure()
	{
		Xfirst=null;
        Yfirst=null; 
        Xlast=null;
        Ylast=null;
        numOfPoints=0;
	}

	@Override
	public void addPoint(Point point) {
            Comparator Xcomp=new XComperator();
            Comparator Ycomp=new YComperator();
            Link current, temp;
            if (Xfirst == null || Xcomp.compare(Xfirst.getData(),point)>0){ //inserted X is the lowest
                if (Yfirst == null || Ycomp.compare(Yfirst.getData(),point)>0){ //inserted Y is the lowest
                    temp = new Link(point, Xfirst,Yfirst,null,null) ; //insert in begining of X and begining of Y
                    if(Xlast == null){ //in case DS is empty
                        Xfirst= temp;
                        Yfirst = temp;
                        Xlast = temp ;
                        Ylast = temp ;
                    }
                    else{
                        Xfirst.setPrevX(temp);
                        Yfirst.setPrevY(temp);
                        Xfirst= temp;
                        Yfirst = temp;
                    }
                }
                else{//inserted X is the lowest but inserted Y isnt the lowest.
                     // Locate the Y node before point of insertion. 
                    current = Yfirst;
                    while (current.getNextY() != null && Ycomp.compare(current.getNextY().getData() , point)<0)
                        current = current.getNextY(); //Y node before new one
                    if(current.getNextY() != null){ //insert node in middle of Y + begining of X
                        temp=new Link(point, Xfirst, current.getNextY(), null, current);
                        current.getNextY().setPrevY(temp);
                        current.setNextY(temp);
                        Xfirst.setPrevX(temp);
                        Xfirst=temp;
                    }
                    else{ // insert node in end of Y + begining of X
                        temp=(new Link(point, Xfirst, null, null,current));
                        Ylast=temp;
                        current.setNextY(Ylast);
                        Xfirst.setPrevX(temp);
                        Xfirst=temp;
                    }
                }
            }
            else{ //inserted X isnt the lowest
                // Locate the X node before point of insertion. 
                current = Xfirst;
                while (current.getNextX() != null && Xcomp.compare(current.getNextX().getData() , point)<0)
                    current = current.getNextX();
                if(current.getNextX() != null){ //insert X node in middle
                    if (Yfirst == null || Ycomp.compare(Yfirst.getData(),point)>0){ //insert middle of X+begining of Y
                        temp=new Link(point, current.getNextX(), Yfirst, current, null);
                        Yfirst.setPrevY(temp);
                        Yfirst=temp;
                        current.getNextX().setPrevX(temp);
                        current.setNextX(temp);
                    }
                    else{//inserted Y isnt the lowest
                        Link current2 = Yfirst;
                        while (current2.getNextY() != null && Ycomp.compare(current2.getNextY().getData() , point)<0)
                            current2 = current2.getNextY();
                        if(current2.getNextY() != null){ //insert Y node in middle
                            temp=new Link(point, current.getNextX(), current2.getNextY(), current, current2);
                            current2.getNextY().setPrevY(temp);
                            current2.setNextY(temp);
                            current.getNextX().setPrevX(temp);
                            current.setNextX(temp);
                        }
                        else{ // insert in the middle of X and end of Y
                            temp=new Link(point, current.getNextX(), null, current,current2);
                            current.getNextX().setPrevX(temp);
                            current.setNextX(temp);
                            Ylast=temp;
                            current2.setNextY(Ylast);
                        }
                    }
                }
                else{// insert in end of X
                    if (Yfirst == null || Ycomp.compare(Yfirst.getData(),point)>0){ //insert in begining of Y 
                        temp=new Link(point, null, Yfirst, current,null);
                        Xlast=temp;
                        current.setNextX(Xlast);
                        Yfirst.setPrevY(temp);
                        Yfirst=temp;
                    }
                    else{ //inserted Y isnt the lowest
                        Link current2 = Yfirst;
                        while (current2.getNextY() != null && Ycomp.compare(current2.getNextY().getData() , point)<0)
                            current2 = current2.getNextY();
                        if(current2.getNextY() != null){ //insert node in middle of Y + end of X
                            temp=new Link(point, null, current2.getNextY(), current, current2);
                            Xlast=temp;
                            current.setNextX(Xlast);
                            current2.getNextY().setPrevY(temp);
                            current2.setNextY(temp);
                        }
                        else{ // insert node in end of Y+end of X
                            Ylast=(new Link(point, null, null, current,current2));
                            Xlast=Ylast;
                            current2.setNextY(Ylast);
                            current.setNextX(Xlast);
                        }
                    }
                }     
            }
            numOfPoints++;
	}

	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
                Point[] parray=new Point[numOfPoints];
                int i=0;
                if(axis){//sorted by X
                    Link P=Xfirst;
                    while(P!=null&&P.getData().getX()<=max){
                        if(P.getData().getX()>=min){
                            parray[i]=P.getData();
                            i++;
                        }
                        P=P.getNextX();
                    }
                }
                else{ //sorted by Y
                    Link P=Yfirst;
                    while(P!=null&&P.getData().getY()<=max){
                        if(P.getData().getY()>=min){
                            parray[i]=P.getData();
                            i++;
                        }
                        P=P.getNextY();
                    }  
                }
                if (i!=0){
                    Point[] output=new Point[i];
                    i=0;
                    while(i<output.length){
                        output[i]=parray[i];
                        i++;
                    }
                    return output;
                }
                else{ //in case no points in range
                    return new Point[0];
                }
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		Point[] parray=new Point[numOfPoints];
                int i=0;
                if(axis){//sorted by X
                    Link P=Yfirst;
                    while(P!=null){
                        if(P.getData().getX()>=min&&P.getData().getX()<=max){
                            parray[i]=P.getData();
                            i++;
                        }
                        P=P.getNextY();
                    }
                }
                else{ //sorted by Y
                    Link P=Xfirst;
                    while(P!=null){
                        if(P.getData().getY()>=min&&P.getData().getY()<=max){
                            parray[i]=P.getData();
                            i++;
                        }
                        P=P.getNextX();
                    }  
                }
                if (i!=0){
                    Point[] output=new Point[i];
                    i=0;
                    while(i<output.length){
                        output[i]=parray[i];
                        i++;
                    }
                    return output;
                }
                else{ //in case no points in range
                    return new Point[0];
                }
	}

	@Override
	public double getDensity() {
		int maxX=Xlast.getData().getX();
                int minX=Xfirst.getData().getX();
                int maxY=Ylast.getData().getY();
                int minY=Yfirst.getData().getY();
                return (numOfPoints/((maxX-minX)*(maxY-minY)));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
                Link linkBefore,linkAfter;
                if(axis){//X axis
                    while(Xfirst!=null&&Xfirst.getData().getX()<min){
                        if(numOfPoints==1){//case has single link
                            Xfirst=null;
                            Yfirst=null;
                            Xlast=null;
                            Ylast=null;
                        }
                        else{//has 2+ links in DS
                            if(Xfirst.getPrevY()!=null){//if has prev Y point
                                linkBefore=Xfirst.getPrevY();
                                linkAfter=Xfirst.getNextY();
                                linkBefore.setNextY(linkAfter);
                                if(linkAfter!=null)//if we didnt delete last Y point
                                    linkAfter.setPrevY(linkBefore);
                                else //if we deleted last y point
                                    Ylast=linkBefore;
                            }
                            else{//if deleting also first Y point
                                Yfirst=Yfirst.getNextY();
                                Yfirst.setPrevY(null);
                            }
                            Xfirst=Xfirst.getNextX();
                            Xfirst.setPrevX(null);
                        }
                        numOfPoints--;
                    }
                    while(Xlast!=null&&Xlast.getData().getX()>max){
                        if(numOfPoints==1){//case has single link
                            Xfirst=null;
                            Yfirst=null;
                            Xlast=null;
                            Ylast=null;
                        }
                        else{
                            if(Xlast.getNextY()!=null){//if has next Y point
                                linkAfter=Xlast.getNextY();
                                linkBefore=Xlast.getPrevY();
                                linkAfter.setPrevY(linkBefore);   
                                if(linkBefore!=null)//if we dont delete first Y point
                                    linkBefore.setNextY(linkAfter);
                                else//if we delete first Y point
                                    Yfirst=linkAfter;
                            }
                            else{//if deleting also last Y point
                                Ylast=Ylast.getPrevY();
                                Ylast.setNextY(null);                            
                            }
                            Xlast=Xlast.getPrevX();
                            Xlast.setNextX(null);
                        }
                        numOfPoints--;
                    }
                }
                else{//Y axis
                    while(Yfirst!=null&&Yfirst.getData().getY()<min){
                        if(numOfPoints==1){//case has single link
                            Xfirst=null;
                            Yfirst=null;
                            Xlast=null;
                            Ylast=null;
                        }
                        else{
                            if(Yfirst.getPrevX()!=null){//if has prev X point
                                linkBefore=Yfirst.getPrevX();
                                linkAfter=Yfirst.getNextX();
                                linkBefore.setNextX(linkAfter);
                                if(linkAfter!=null)//if we dont delete last X point
                                    linkAfter.setPrevX(linkBefore);
                                else
                                    Xlast=linkBefore;
                            }
                            else{//if deleting also first x point
                                Xfirst=Xfirst.getNextX();
                                Xfirst.setPrevX(null);
                            }
                            Yfirst=Yfirst.getNextY();
                            Yfirst.setPrevY(null);
                        }
                        numOfPoints--;
                    }
                    while(Ylast!=null&&Ylast.getData().getY()>max){
                        if(numOfPoints==1){//case has single link
                            Xfirst=null;
                            Yfirst=null;
                            Xlast=null;
                            Ylast=null;
                        }
                        else{
                            if(Ylast.getNextX()!=null){//if has next X point
                                linkAfter=Ylast.getNextX();
                                linkBefore=Ylast.getPrevX();
                                linkAfter.setPrevX(linkBefore);
                                if(linkBefore!=null)//if we dont delete first X point
                                    linkBefore.setNextX(linkAfter);
                                else
                                    Xfirst=linkAfter;
                            }
                            else{//if deleting also last Y point
                                Xlast=Xlast.getPrevX();
                                Xlast.setNextX(null);
                            }
                            Ylast=Ylast.getPrevY();
                            Ylast.setNextY(null);
                        }
                        numOfPoints--;
                    }    
                }	
	}

	@Override
	public Boolean getLargestAxis() {
		int maxX=Xlast.getData().getX();
                int minX=Xfirst.getData().getX();
                int maxY=Ylast.getData().getY();
                int minY=Yfirst.getData().getY();
                return (maxX-minX)>(maxY-minY);
	}

	@Override
	public Container getMedian(Boolean axis) {
		int median=1+numOfPoints/2;
                if(axis){//X Axis
                    Link current=Xfirst;
                    for(int i=1;i<=median;i++){
                        if(i!=median)
                            current=current.getNextX();
                    }
                    return new Container(current);
                }
                else{//Y Axis
                    Link current=Yfirst;
                    for(int i=1;i<=median;i++){
                        if(i!=median)
                            current=current.getNextY();
                    }
                    return new Container(current);
                }
        }
        
        public double distanceBetween(Point A,Point B){
                return Math.sqrt(Math.pow(A.getX()-B.getX(),2) + Math.pow(A.getY()-B.getY(),2));
        }

	@Override
	public Point[] nearestPairInStrip(Container container, double width,
			Boolean axis) {
                double rightLimit,leftLimit;
                Link right=container.getLink();
                Link left=container.getLink();
                int i=0;
                double shortestDist=Double.MAX_VALUE;
                Point[] output=new Point[2]; //nearest pair
                Point[] strip2= new Point[numOfPoints];
                Comparator comp;
                if(axis){//x axis
                    comp=new YComperator();
                    rightLimit=container.getData().getX()+width/2;
                    leftLimit=container.getData().getX()-width/2;
                    right=right.getNextX();
                    while(right!=null&&(right.getData()).getX()<=rightLimit){                        
                        strip2[i]=right.getData();
                        i++;
                        right=right.getNextX();
                    }
                    left=left.getPrevX();
                    while(left!=null&&(left.getData()).getX()>=leftLimit){                       
                        strip2[i]=left.getData();
                        i++;
                        left=left.getPrevX();
                    }
                    strip2[i]=container.getData();
                }
                else{//y axis
                    comp=new XComperator();
                    rightLimit=container.getData().getY()+width/2;
                    leftLimit=container.getData().getY()-width/2;
                    right=right.getNextY();
                    while(right!=null&&(right.getData()).getY()<=rightLimit){                        
                        strip2[i]=right.getData();
                        i++;
                        right=right.getNextY();
                    }
                    left=left.getPrevY();
                    while(left!=null&&(left.getData()).getY()>=leftLimit){                        
                        strip2[i]=left.getData();
                        i++;
                        left=left.getPrevY();
                    }
                    strip2[i]=container.getData();
                }
                Point[] strip= new Point [i+1];
                for (i=0;i<strip.length;i++)
                    strip[i]=strip2[i]; 
                if(numOfPoints<strip.length*Math.log(strip.length))
                	strip=getPointsInRangeRegAxis(Integer.MIN_VALUE, Integer.MAX_VALUE, axis);
                else
                	Arrays.sort(strip, comp);
                if (strip.length<2) 
                    return null;
                for (i=0;i<strip.length;i++) {
                    Point p1=strip[i];
                    for (int j=i+1;j<strip.length&&j<i+7;j++) {
                        Point p2=strip[j];
                        double dist = distanceBetween(p1,p2);
                        if (dist < shortestDist) {
                            shortestDist = dist;
                            output[0] = p1;
                            output[1] = p2;
                        }
                    }
                }
                return output;
	}
        
       

	@Override
	public Point[] nearestPair() {
                Point[] output=new Point[2];
                if(numOfPoints<2)
                    return output;
                if(numOfPoints==2){ 
                    output[0]=Xfirst.getData();
                    output[1]=Xlast.getData();
                    return output;
                }
                boolean axis=getLargestAxis();
                Container cont=getMedian(axis);
                DataStructure left=new DataStructure();
                DataStructure right=new DataStructure();
                if(axis){
                    Link current=Xfirst;
                    while(current!=null){
                        if(current.getData().getX()<cont.getData().getX())
                            left.addPoint(current.getData());
                        else
                            right.addPoint(current.getData());
                        current=current.getNextX();
                    }
                }
                else{
                    Link current=Yfirst;
                    while(current!=null){
                        if(current.getData().getY()<cont.getData().getY())
                            left.addPoint(current.getData());
                        else
                            right.addPoint(current.getData());
                        current=current.getNextY();
                    }
                }
                double dist1=Double.MAX_VALUE;
                double dist2=Double.MAX_VALUE;
                Point[] rightA=right.nearestPair();
                Point[] leftA=left.nearestPair();
                if(rightA[0]!=null&rightA[1]!=null)
                    dist1=distanceBetween(rightA[0],rightA[1]);
                if(leftA[0]!=null&leftA[1]!=null)
                    dist2=distanceBetween(leftA[0],leftA[1]);               
                if(dist1<dist2){
                    Point[] array=nearestPairInStrip(cont, 2*dist1,axis);
                    double dist3=Double.MAX_VALUE;
                    if(array!=null)
                    	dist3=distanceBetween(array[0],array[1]);
                    if(dist3<dist1)
                        return array;
                    else
                        return rightA;
                }
                else{
                    Point[] array=nearestPairInStrip(cont, 2*dist2,axis);
                    double dist3=Double.MAX_VALUE;
                    if(array!=null)
                    	dist3=distanceBetween(array[0],array[1]);
                    if(dist3<dist2)
                        return array;
                    else
                        return leftA;
                } 
	}
        
        public String toString() {
		String output="X: ";
                String output2='\n'+"Y: ";
		Link curr = Xfirst ;
                Link curr2 = Yfirst ;
		for (int i = 0; i <numOfPoints; i=i+1){
			
    		output = output + curr.getData() + " ";
                output2 = output2 + curr2.getData() + " ";
    		curr = curr.getNextX() ;
                curr2 = curr2.getNextY() ;
			
		}
		return output+output2;
	}
	
        private Link Xfirst;
        private Link Yfirst;
        private Link Xlast;
        private Link Ylast;
        private int numOfPoints;
}
