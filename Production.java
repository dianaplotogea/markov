
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Production {
	private Map<String, String> rules;

	public Production() {
		rules = new LinkedHashMap<>();
	}

	public void addRule(String inputPattern, String outputReplacement) {
		rules.put(inputPattern, outputReplacement);
	}

	public Map<String, String> getRules() {
		return rules;
	}
	
	public String applyRule(String input) {
	    while (true) {
	        boolean ruleApplied = false;

	        for (String pattern : rules.keySet()) {
	            Pattern p = Pattern.compile(Pattern.quote(pattern));
	            Matcher m = p.matcher(input);

	            if (m.find()) {
	                String replacement = rules.get(pattern);
	                input = m.replaceFirst(replacement);
	                String inputWithout0s = "";
	                for(int i=0;i<input.length();i++){
	                	if(input.charAt(i) != '0') {
	                		inputWithout0s += input.charAt(i);
	                	}
	                }
	                System.out.println("Replacement: " + inputWithout0s);  
	                ruleApplied = true;

	                if (replacement.contains(".") && !pattern.contains(".")) {
	                    return inputWithout0s;  
	                }
	                break; 
	            }
	        }

	        if (!ruleApplied) {
	            break;  
	        }
	        
	    }

	    return input;
	}

}


