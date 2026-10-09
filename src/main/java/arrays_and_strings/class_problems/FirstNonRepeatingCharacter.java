package arrays_and_strings.class_problems;

import java.util.Scanner;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256) {
                frequency[c]++;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 256 && frequency[c] == 1) {
                return c;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = scanner.hasNextLine() ? scanner.nextLine() : "swiss";

        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        scanner.close();
    }
}
