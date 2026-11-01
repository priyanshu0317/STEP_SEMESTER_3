package linked_list.class_problems;

import java.util.ArrayList;
import java.util.List;

public class ClassMarksGridAnalysis {

    public static class StudentRecord {
        private String name;
        private int[] marks;

        public StudentRecord(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int[] getMarks() {
            return marks;
        }

        public int getTotal() {
            int sum = 0;
            for (int m : marks) {
                sum += m;
            }
            return sum;
        }
    }

    public static void analyzeMarks(List<StudentRecord> students) {
        if (students == null || students.isEmpty()) {
            return;
        }

        int numStudents = students.size();
        int numSubjects = students.get(0).getMarks().length;

        // 1. Calculate and print total marks for each student
        StringBuilder totalsBuilder = new StringBuilder("Totals ");
        String topperName = "";
        int maxTotal = -1;

        for (int i = 0; i < numStudents; i++) {
            StudentRecord student = students.get(i);
            int total = student.getTotal();
            totalsBuilder.append(student.getName()).append(" ").append(total);
            if (i < numStudents - 1) {
                totalsBuilder.append(", ");
            }

            if (total > maxTotal) {
                maxTotal = total;
                topperName = student.getName();
            }
        }
        System.out.println(totalsBuilder.toString());

        // 2. Calculate average marks for each subject
        double[] subjectTotals = new double[numSubjects];
        for (StudentRecord student : students) {
            int[] marks = student.getMarks();
            for (int j = 0; j < numSubjects; j++) {
                subjectTotals[j] += marks[j];
            }
        }

        StringBuilder averagesBuilder = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            double avg = subjectTotals[j] / numStudents;
            averagesBuilder.append(String.format(java.util.Locale.US, "%.2f", avg));
            if (j < numSubjects - 1) {
                averagesBuilder.append(", ");
            }
        }
        System.out.println(averagesBuilder.toString());

        // 3. Print topper
        System.out.printf("topper %s (%d)%n", topperName, maxTotal);
    }

    public static List<StudentRecord> parseRecords(String[] lines) {
        List<StudentRecord> records = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            int openBracket = trimmed.indexOf('[');
            int closeBracket = trimmed.indexOf(']');
            if (openBracket != -1 && closeBracket != -1) {
                String name = trimmed.substring(0, openBracket).trim();
                String marksStr = trimmed.substring(openBracket + 1, closeBracket).trim();
                String[] marksParts = marksStr.split(",");
                int[] marks = new int[marksParts.length];
                for (int i = 0; i < marksParts.length; i++) {
                    marks[i] = Integer.parseInt(marksParts[i].trim());
                }
                records.add(new StudentRecord(name, marks));
            }
        }
        return records;
    }

    public static void main(String[] args) {
        String[] inputLines = {
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        };

        List<StudentRecord> students = parseRecords(inputLines);
        analyzeMarks(students);
    }
}
