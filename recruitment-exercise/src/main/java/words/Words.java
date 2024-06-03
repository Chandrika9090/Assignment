package words;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
            // Using TreeSet to automatically sort the words and manage uniqueness
            Set<String> uniqueWords = new TreeSet<>();

            // Regular expression to split words, accounting for punctuation
            String[] words = sentence.split("\\W+");

            // Convert each word to lowercase and add to the set
            for (String word : words) {
                uniqueWords.add(word.toLowerCase());
            }

            //Converting the set into list and returning
            return uniqueWords.stream().toList();
    }
}
