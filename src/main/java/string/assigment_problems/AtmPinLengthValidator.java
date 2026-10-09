package string.assigment_problems;

import java.util.Scanner;

public class AtmPinLengthValidator {

    public static void checkPinLength(String pin) {
        if (pin != null && pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN - must be exactly 4 digits.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ATM PIN: ");
        String pin = scanner.hasNextLine() ? scanner.nextLine() : "4820";

        checkPinLength(pin);
        scanner.close();
    }
}
