package arrays_and_strings.assigment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            // Strip any trailing punctuation if present so word length is based on letters
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]", "");
            int len = cleanWord.isEmpty() ? word.length() : cleanWord.length();

            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movie review: ");
        String review = scanner.hasNextLine() ? scanner.nextLine() : "This movie was absolutely fantastic and thrilling";

        classifyWordLengths(review);
        scanner.close();
    }
}
