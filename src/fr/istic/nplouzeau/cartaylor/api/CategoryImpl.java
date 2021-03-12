package fr.istic.nplouzeau.cartaylor.api;

public class CategoryImpl implements Category{

	private String name;
	
	public CategoryImpl (String s) {
		this.name = s;
	}
	
    public String getName() {
    	return name;
    }
	
}
