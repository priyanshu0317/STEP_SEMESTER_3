package arrays_and_strings.assigment_problems;

import java.util.Scanner;

public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }

        int total = original.length();
        int matched = 0;
        int firstMismatch = -1;

        int compareLength = Math.min(original.length(), typed.length());

        for (int i = 0; i < compareLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        if (firstMismatch == -1 && original.length() != typed.length()) {
            firstMismatch = compareLength;
        }

        double accuracy = total == 0 ? 100.0 : ((double) matched / total) * 100.0;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", matched, total, accuracy);

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            char origChar = firstMismatch < original.length() ? original.charAt(firstMismatch) : ' ';
            char typedChar = firstMismatch < typed.length() ? typed.charAt(firstMismatch) : ' ';
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + origChar + "' vs '" + typedChar + "')");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter original passage: ");
        String original = scanner.hasNextLine() ? scanner.nextLine() : "hello world";

        System.out.print("Enter typed text: ");
        String typed = scanner.hasNextLine() ? scanner.nextLine() : "hello worlt";

        checkTypingAccuracy(original, typed);
        scanner.close();
    }
}
