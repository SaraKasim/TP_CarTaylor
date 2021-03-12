package fr.istic.nplouzeau.cartaylor.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.*;

public class CarTaylorTests {

	private ConfiguratorImpl config;
	private ConfigurationImpl configT;
	private CompManImpl compCheck;
	private Set<PartType> eParts = new  HashSet<PartType>();
	private Set<PartType> tParts= new  HashSet<PartType>();
	private Set<PartType> extParts= new  HashSet<PartType>();
	private Set<PartType> intParts= new  HashSet<PartType>();
	private Category eCat;
	private Category tCat;
	private Category extCat;
	private Category intCat;
	private Set<Category> allCats = new  HashSet<Category>();
	private Map<Category,Set<PartType>> variants = new HashMap<Category, Set<PartType>>();
	private Map<PartType,Set<PartType>> incomp = new HashMap<PartType, Set<PartType>>();
	private Map<PartType,Set<PartType>> req = new HashMap<PartType, Set<PartType>>();
	
	@BeforeEach
	void setUp() throws Exception {
		eCat = new CategoryImpl("Engin");
		tCat = new CategoryImpl("Transmission");
		extCat = new CategoryImpl("Exterior");
		intCat = new CategoryImpl("Interior");
		allCats.add(eCat);
		allCats.add(tCat);
		allCats.add(extCat);
		allCats.add(intCat);
		variants.put(eCat, eParts);
		variants.put(tCat, tParts);
		variants.put(extCat, extParts);
		variants.put(intCat, intParts);
		
		compCheck = new CompManImpl(incomp,req);
		configT = new ConfigurationImpl(compCheck, variants);
		config = new ConfiguratorImpl(variants, configT, compCheck);
	}
	
	@Test
	@DisplayName("Testing CategoryImpl: getName")
	void test1() {
		assertEquals("Engin", eCat.getName());
	}

	@Test
	@DisplayName("Testing PartTypeImpl: getName")
	void test2() {
		PartType p1 = new PartTypeImpl("EG100" , eCat); 
		assertEquals("EG100", p1.getName());
	}
	
	@Test
	@DisplayName("Testing PartTypeImpl: getName")
	void test3() {
		PartType p1 = new PartTypeImpl("EG100" , eCat); 
		assertEquals(eCat, p1.getCategory());
	}
	
	@Test
	@DisplayName("Testing CompManImpl: getIncompatibilities + addIncompatibilities + removeIncompatibilities")
	void test4() {
		PartType p1 = new PartTypeImpl("TSF7" , tCat);
		PartType p2 = new PartTypeImpl("EG100" , eCat);
		PartType p3 = new PartTypeImpl("EG133" , eCat);
		PartType p4 = new PartTypeImpl("ED110" , eCat);
		Set<PartType> TSF7incomp= new  HashSet<PartType>();
		TSF7incomp.add(p2);
		TSF7incomp.add(p3);
		TSF7incomp.add(p4);
		compCheck.addIncompatibilities(p1, TSF7incomp);
		assertEquals(TSF7incomp, compCheck.getIncompatibilities(p1)); 
		compCheck.removeIncompatibility(p1, p3);
		TSF7incomp.remove(p3);
		assertEquals(TSF7incomp, compCheck.getIncompatibilities(p1)); 
	}
	
	@Test
	@DisplayName("Testing CompManImpl: getIncompatibilities + addIncompatibilities, test to add a req value to the list of incomp")
	void test5() {
		PartType p5 = new PartTypeImpl("EH120" , eCat);
		PartType p7 = new PartTypeImpl("TC120" , tCat);
		
		Set<PartType> EH120Req= new  HashSet<PartType>();
		EH120Req.add(p7);
		Set<PartType> EH120incomp= new  HashSet<PartType>();
		EH120incomp.add(p7);
		compCheck.addRequirements(p5, EH120Req);
		compCheck.addIncompatibilities(p5, EH120incomp);
		
		Set<PartType> empty= new  HashSet<PartType>();
		
		assertEquals(empty, compCheck.getIncompatibilities(p5)); 
	}

	@Test
	@DisplayName("Testing CompManImpl: getRequirements + addRequirements + removeRequirements")
	void test6() {
		PartType p5 = new PartTypeImpl("EH120" , eCat);
		PartType p7 = new PartTypeImpl("TC120" , tCat);
		
		Set<PartType> EH120Req= new  HashSet<PartType>();
		EH120Req.add(p7);
		Set<PartType> TC120Req= new  HashSet<PartType>();
		TC120Req.add(p5);
		compCheck.addRequirements(p5, EH120Req);
		//compCheck.addRequirements(p7, TC120Req);
		
		assertEquals(EH120Req, compCheck.getRequirements(p5)); 
		assertEquals(TC120Req, compCheck.getRequirements(p7));
		
		compCheck.removeRequirement(p5, p7);
		Set<PartType> empty= new  HashSet<PartType>();
		
		assertEquals(empty, compCheck.getRequirements(p7));
	}
	
	@Test
	@DisplayName("Testing CompManImpl: getRequirements + addRequirements, test to add an incompatibile value to the list of Req")
	void test7() {
		PartType p5 = new PartTypeImpl("EH120" , eCat);
		PartType p7 = new PartTypeImpl("TC120" , tCat);
		
		Set<PartType> EH120Req= new  HashSet<PartType>();
		EH120Req.add(p7);
		Set<PartType> TC120Req= new  HashSet<PartType>();
		TC120Req.add(p5);
		compCheck.addIncompatibilities(p7, TC120Req);
		compCheck.addRequirements(p7, TC120Req);

		Set<PartType> empty= new  HashSet<PartType>();
		
		assertEquals(empty, compCheck.getRequirements(p7)); 
		//assertEquals(TC120Req, compCheck.getRequirements(p7));
	}
	
	@Test
	@DisplayName("Testing CongigurationImpl: unselectPartType + clear")
	void test8() {
		PartType p5 = new PartTypeImpl("EH120" , eCat);
		PartType p6 = new PartTypeImpl("T120" , tCat);
		
		configT.selectPart(p5);
		configT.selectPart(p6);
		
		Set<PartType> parts= new  HashSet<PartType>();
		
		parts.add(p5);
		parts.add(p6);
		
		assertEquals(parts, configT.getSelectedParts());
		assertEquals(p5, configT.getSelectionForCategory(eCat));
		
		configT.unselectPartType(eCat);
		parts.remove(p5);
		assertEquals(parts, configT.getSelectedParts());
		configT.clear();
		Set<PartType> empty= new  HashSet<PartType>();
		assertEquals(empty, configT.getSelectedParts());	
	}
	
	@Test
	@DisplayName("Testing CongigurationImpl: selectPart + getSelectedParts + getSelectionForCategory  + isComplete + isValid")
	void test9() {
		//PartType p5 = new PartTypeImpl("EH120" , eCat);
		PartType p6 = new PartTypeImpl("T120" , tCat);
		
		//configT.selectPart(p5);
		configT.selectPart(p6);
		
		Set<PartType> parts= new  HashSet<PartType>();
		
		//parts.add(p5);
		parts.add(p6);
		
		assertEquals(parts, configT.getSelectedParts());
		assertEquals(p6, configT.getSelectionForCategory(tCat));
			
		assertTrue(configT.isValid());
		
		PartType p4 = new PartTypeImpl("ED110" , eCat);
		Set<PartType> incomps= new  HashSet<PartType>();
		incomps.add(p4);
		compCheck.addIncompatibilities(p6, incomps);
		configT.selectPart(p4);
		parts.add(p4);
	
		assertFalse(configT.isValid());
		assertTrue(configT.isComplete());
		
		PartType p10 = new PartTypeImpl("EH120" , extCat);
		PartType p11 = new PartTypeImpl("T120" , intCat);
		
		configT.selectPart(p10);
		configT.selectPart(p11);
		assertFalse(configT.isComplete()); // probleme?
			
	}
	
	@Test
	@DisplayName("Testing ConfiguratorImpl: getCategories + getVariants")
	void test10() {
		Set<Category> cats= new  HashSet<Category>();
		cats.add(eCat);
		cats.add(tCat);
		cats.add(extCat);
		cats.add(intCat);
		
		assertEquals(cats,config.getCategories());
		
		assertEquals(eParts,config.getVariants(eCat));
	}
	
}
