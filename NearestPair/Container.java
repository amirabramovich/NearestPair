
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
        private Link link;
	
        public Container(Link link){
            data=link.getData();
            this.link=link;
        }
        public Link getLink()
	{
		return link;
	}
	//Don't delete or change this function
	public Point getData()
	{
		return data;
	}
}
