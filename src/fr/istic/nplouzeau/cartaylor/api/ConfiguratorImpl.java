package fr.istic.nplouzeau.cartaylor.api;

import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class ConfiguratorImpl implements Configurator{

	private Set<Category> cat;
	private Map <Category, Set<PartType>> variants; // maybe configuration can have this? but it's private
	private Configuration config;
	private CompatibilityChecker comp;
	
	public ConfiguratorImpl(Map <Category, Set<PartType>> v, Configuration conf, CompatibilityChecker co ) {
		this.variants = v;
		this.config = conf;
		this.comp =co;
	}
	
    public Set<Category> getCategories(){
    	cat = variants.keySet();
		return Collections.unmodifiableSet(cat);
    }

    public Set<PartType> getVariants(Category category){
		return Collections.unmodifiableSet(variants.get(category));
    }

    public Configuration getConfiguration() {
    	return config;
    }

    public CompatibilityChecker getCompatibilityChecker() {
    	return comp;
    }
	
}
