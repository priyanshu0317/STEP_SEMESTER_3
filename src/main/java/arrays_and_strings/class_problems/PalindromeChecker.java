package arrays_and_strings.class_problems;

import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) {
            return false;
        }
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) {
            return false;
        }
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to verify palindrome: ");
        String text = scanner.hasNextLine() ? scanner.nextLine() : "madam";

        String iterativeResult = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String recursiveResult = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arrayReversalResult = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";

        System.out.println("Iterative: " + iterativeResult + " | Recursive: " + recursiveResult + " | Array Reversal: " + arrayReversalResult);

        scanner.close();
    }
}
