package demo.collectionsgenerics;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class DemoGenericsTypeInference {

	public static void main(String[] args) {
		demoInferenceSyntax();
		demoInferenceLimitations();
	}
	
	
	private static void demoInferenceSyntax() {
		
		// Explicit type info in constructor.
		Map<String, Integer> europeDialCodes = new HashMap<String, Integer>();
		europeDialCodes.put("UK", 44);
		europeDialCodes.put("Hungary", 36);
		europeDialCodes.put("Norway", 47);
		
		// Inferred type in constructor, using <>.
		Map<String, Integer> asiaDialCodes = new HashMap<>();	
		asiaDialCodes.put("China", 86);
		asiaDialCodes.put("Japan", 81);
		asiaDialCodes.put("India", 91);
		
		// Absence of <> causes the raw type to be used. This will give you a warning.
		Map<String, Integer> africaDialCodes = new HashMap();	
		africaDialCodes.put("Kenya", 254);
		africaDialCodes.put("Morocco", 212);
		africaDialCodes.put("Zambia", 260);
	}


	private static void demoInferenceLimitations() {

		// Create a List<String> for the purposes of this example.
		List<String> list = new ArrayList<>();
		list.add("Huey");
		list.add("Lewey");
		list.add("Dewey");
		
		// This statement would cause a compiler error - the compiler can't infer the type parameter for the ArrayList.
		// list.addAll(new ArrayList<>());
		
		// This is fine - in addAll(), we explicitly tell the compiler it's an ArrayList<String>. 
		list.addAll(new ArrayList<String>());

		// This is fine too - in addAll(), the compiler knows anotherList is an ArrayList<String>. 
		List<String> anotherList = new ArrayList<>();
		list.addAll(anotherList);
	}
}	
