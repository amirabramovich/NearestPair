
public class Container{
	private Point data;
        private Link link;
	
        public Container(Link link){
            data=link.getData();
            this.link=link;
        }
        public Link getLink()
	{
		return link;
	}
	
	public Point getData()
	{
		return data;
	}
}
