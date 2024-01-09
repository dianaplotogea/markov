

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Markov {
	private Production production;
	private Set<Character> vocabulary;

	public Markov() {
		production = new Production();
		vocabulary = new HashSet<>();
	}
	
	  public Set<Character> getVocabulary() {
	        return vocabulary;
	    }

	public void citire(String fileName) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(fileName));
	        String alphabetInput = reader.readLine();
	        
	        if (alphabetInput.length() != alphabetInput.chars().distinct().count()) {
	            System.out.println("Alphabet contains repeated characters. Please enter a valid alphabet.");
	            reader.close();
	            return;
	        }

	        for (char c : alphabetInput.toCharArray()) {
	            vocabulary.add(c);
	        }
	        
	        vocabulary.add('0');
	        vocabulary.add('.');

	        // Read rules from the remaining lines
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(" ");
	            if (parts.length == 2) {
	                String pattern = parts[0];
	                String replacement = parts[1];

	                // Check if the pattern and replacement contain only characters from the alphabet
	                if (pattern.chars().allMatch(c -> vocabulary.contains((char) c))
	                        && replacement.chars().allMatch(c -> vocabulary.contains((char) c))) {
	                    production.addRule(pattern, replacement);
	                } else {
	                    System.out.println("Invalid rule: " + line);
	                }
	            }
	        }

	        reader.close();
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}

	public boolean validare(String word) {
		for (char c : word.toCharArray()) {
			if (!vocabulary.contains(c)) {
				return false;
			}
		}
		return true;
	}

	public void afisare() {
		System.out.println("Vocabulary: " + vocabulary);

		System.out.println("Rules:");
		for (String pattern : production.getRules().keySet()) {
			System.out.println(pattern + " -> " + production.getRules().get(pattern)
					+ (production.getRules().get(pattern).contains(".") ? " (Final)" : ""));
		}
	}

	public void derivare(String input) {
	    System.out.println("Replacements:");

	    String result = input;
	    String previousResult = "";
	    boolean finalRuleApplied = false;

	    while (!result.equals(previousResult) && !finalRuleApplied) {
	    	result = result.replace("0", "");
	        previousResult = result;
	        result = production.applyRule(result);
	        

	        if (result.contains(".") && !result.endsWith(".")) {
	            finalRuleApplied = true;
	        }
	    }

	    System.out.println("Final Result: " + result);
	}


}