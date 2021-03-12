package fr.istic.nplouzeau.cartaylor.api;

public class PartTypeImpl implements PartType {

	private Category cat;
	private String name;
	
	public PartTypeImpl (String s, Category c) {
		this.cat = c;
		this.name = s;
	}
	
    public String getName() {
    	return name;
    }

    public Category getCategory() {
    	return cat;
    }
	
}
