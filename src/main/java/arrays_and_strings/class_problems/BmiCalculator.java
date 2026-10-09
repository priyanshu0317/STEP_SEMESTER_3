package arrays_and_strings.class_problems;

import java.util.Scanner;

public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-10s | %-12s | %-12s | %-8s | %-12s%n", "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("----------------------------------------------------------------------");

        int count = Math.min(heights.length, weights.length);
        for (int i = 0; i < count; i++) {
            double height = heights[i];
            double weight = weights[i];
            double bmi = weight / (height * height);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %-3d | %-12.2f | %-12.2f | %-8.2f | %-12s%n", (i + 1), height, weight, bmi, status);
        }
        System.out.println("----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of persons (or 0 to use demo data): ");
        int n = scanner.hasNextInt() ? scanner.nextInt() : 0;

        double[] heights;
        double[] weights;

        if (n <= 0) {
            // Sample demo dataset for 5 persons
            heights = new double[]{1.75, 1.60, 1.82, 1.68, 1.70};
            weights = new double[]{70.0, 90.0, 62.0, 85.0, 52.0};
        } else {
            heights = new double[n];
            weights = new double[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter height (m) and weight (kg) for Person " + (i + 1) + ": ");
                heights[i] = scanner.nextDouble();
                weights[i] = scanner.nextDouble();
            }
        }

        printWellnessReport(heights, weights);
        scanner.close();
    }
}
