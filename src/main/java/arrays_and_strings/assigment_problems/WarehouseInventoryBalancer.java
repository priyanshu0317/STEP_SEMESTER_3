package arrays_and_strings.assigment_problems;

import java.util.Scanner;

public class WarehouseInventoryBalancer {

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA == null || sectionB == null || sectionA.length == 0 || sectionB.length == 0) {
            System.out.println("Invalid inventory data.");
            return;
        }

        int sectionATotal = 0;
        int sectionBTotal = 0;

        int highestQuantity = Integer.MIN_VALUE;
        String highestSection = "Section A";
        int highestItemIndex = 1;

        for (int i = 0; i < sectionA.length; i++) {
            sectionATotal += sectionA[i];
            if (sectionA[i] > highestQuantity) {
                highestQuantity = sectionA[i];
                highestSection = "Section A";
                highestItemIndex = i + 1; // 1-based Item index
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            sectionBTotal += sectionB[i];
            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = "Section B";
                highestItemIndex = i + 1; // 1-based Item index
            }
        }

        String status = (sectionATotal == sectionBTotal) ? "Balanced" : "Not Balanced";

        System.out.println("Section A Total: " + sectionATotal + " | Section B Total: " + sectionBTotal
                + " | Status: " + status
                + " | Highest Quantity: " + highestQuantity + " (" + highestSection + ", Item " + highestItemIndex + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of product categories (or 0 for demo): ");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n > 0) {
                int[] sectionA = new int[n];
                int[] sectionB = new int[n];

                System.out.print("Enter Section A quantities: ");
                for (int i = 0; i < n; i++) {
                    sectionA[i] = scanner.nextInt();
                }

                System.out.print("Enter Section B quantities: ");
                for (int i = 0; i < n; i++) {
                    sectionB[i] = scanner.nextInt();
                }

                analyzeInventory(sectionA, sectionB);
            } else {
                int[] sectionA = {20, 15, 30};
                int[] sectionB = {25, 10, 30};
                analyzeInventory(sectionA, sectionB);
            }
        } else {
            int[] sectionA = {20, 15, 30};
            int[] sectionB = {25, 10, 30};
            analyzeInventory(sectionA, sectionB);
        }

        scanner.close();
    }
}
