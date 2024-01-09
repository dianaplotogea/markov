
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Markov markov = new Markov();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word: ");
            String word = scanner.nextLine();

            
            if (word.isEmpty()) {
                System.out.println("Word cannot be empty. Please try again.");
                continue;
            }
           
            
            markov.citire(new File("").getAbsolutePath() + "/rulesMarkov.txt");

            if (markov.getVocabulary().isEmpty()) {
                System.out.println("No valid alphabet found. Please check the input file.");
                continue;
            }
            
            if (!markov.validare(word)) {
                System.out.println("Word contains characters that are not part of the vocabulary.");
                continue;
            }

            markov.afisare();
            markov.derivare(word);

            System.out.print("Do you want to try another word? (yes/no): ");
            String anotherWord = scanner.nextLine();

            if (!anotherWord.equalsIgnoreCase("yes")) {
            	System.out.println("End of algorithm.");
                break; 
            }
        }

        scanner.close();
    }
}
