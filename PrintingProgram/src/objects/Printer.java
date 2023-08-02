package objects;

public class Printer {
	private String location ="";
	private String name ="";


	public Printer(String name, String location) {
		
		this.name = name;
		this.location = location;
	}
	
	public String getLocation() {
		return location;
		
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
