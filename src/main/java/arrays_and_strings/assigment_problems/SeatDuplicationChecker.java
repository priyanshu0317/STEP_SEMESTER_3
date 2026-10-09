package arrays_and_strings.assigment_problems;

import java.util.Scanner;

public class SeatDuplicationChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length == 0) {
            System.out.println("No Duplicate Seats Found");
            return;
        }

        boolean foundDuplicate = false;
        int[] reported = new int[seatNumbers.length];
        int reportedCount = 0;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    boolean alreadyReported = false;
                    for (int k = 0; k < reportedCount; k++) {
                        if (reported[k] == seatNumbers[i]) {
                            alreadyReported = true;
                            break;
                        }
                    }
                    if (!alreadyReported) {
                        reported[reportedCount++] = seatNumbers[i];
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                        foundDuplicate = true;
                    }
                    break;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[] seats = new int[n];
            System.out.print("Enter " + n + " seat numbers: ");
            for (int i = 0; i < n; i++) {
                seats[i] = scanner.nextInt();
            }
            checkDuplicateSeats(seats);
        } else {
            // Default demo with sample data
            int[] demoSeats = {101, 102, 103, 102, 105};
            checkDuplicateSeats(demoSeats);
        }
        scanner.close();
    }
}
