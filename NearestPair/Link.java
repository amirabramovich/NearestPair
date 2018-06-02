
public class Link {
	private Point data;
	private Link Xnext;
        private Link Xprev;
        private Link Ynext;
        private Link Yprev;
	public Link(Point data, Link Xnext, Link Ynext, Link Xprev, Link Yprev){
		this.data = data;
		this.Xnext = Xnext;
                this.Xprev = Xprev;
                this.Ynext = Ynext;
                this.Yprev = Yprev;
	}
	public Link(Point data){
		this(data,null,null,null,null);
	}
	public Point getData(){
		return data;
	}
	public Link getNextX(){
		return Xnext; 
	}
	public void setNextX(Link Xnext){
		this.Xnext = Xnext;
	}
        public Link getNextY(){
		return Ynext; 
	}
	public void setNextY(Link Ynext){
		this.Ynext = Ynext;
	}
        public Link getPrevX(){
		return Xprev; 
	}
        public Link getPrevY(){
		return Yprev; 
	}
	public void setPrevX(Link Xprev){
		this.Xprev = Xprev;
	}
        public void setPrevY(Link Yprev){
		this.Yprev = Yprev;
	}
	public Point setData(Point data){
		Point tmp = this.data;
		this.data = data; 
		return tmp;
	}
	
	public String toString(){ 
		return data.toString();
	}
	
	public boolean equals(Object other) {  
	    boolean isEqual = true;
	    if (! (other instanceof Link)) // if it is link
	    	isEqual = false;
	    else {
	    	Link otherL = (Link) other;
	    	isEqual = getData().equals(otherL.getData()); // check equavility
	}
	    return isEqual;

	}


}

