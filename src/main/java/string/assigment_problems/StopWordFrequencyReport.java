package string.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StopWordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        String[] stopWordsArray = {"the", "was", "and", "a", "is", "of", "in"};
        Set<String> stopWords = new HashSet<>(Arrays.asList(stopWordsArray));

        // Normalize: convert to lowercase, strip punctuation using replace()
        String cleaned = feedback.toLowerCase();
        cleaned = cleaned.replace(".", "");
        cleaned = cleaned.replace(",", "");
        cleaned = cleaned.replace("!", "");
        cleaned = cleaned.replace("?", "");
        cleaned = cleaned.replace(";", "");
        cleaned = cleaned.replace(":", "");
        cleaned = cleaned.replace("\"", "");

        String[] words = cleaned.trim().split("\\s+");
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty() || stopWords.contains(word)) {
                continue;
            }
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Sort by frequency count in descending order
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter feedback paragraph: ");
        String feedback = scanner.hasNextLine() ? scanner.nextLine() : "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
        scanner.close();
    }
}
