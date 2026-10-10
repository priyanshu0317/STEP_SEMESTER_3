package object_oriented_design.assigment_problems;

import java.util.HashMap;
import java.util.Map;

public class AssignmentSubmissionPortal {

    // Status enum for submission lifecycle
    public enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    // Abstract Assignment base class defining common properties and polymorphic penalty rates
    public abstract static class Assignment {
        private String title;
        private int maxMarks;
        private int dueDay; // Represented as calendar day (e.g., 10 for Mar 10)

        public Assignment(String title, int maxMarks, int dueDay) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
        }

        public String getTitle() {
            return title;
        }

        public int getMaxMarks() {
            return maxMarks;
        }

        public int getDueDay() {
            return dueDay;
        }

        public abstract double getLatePenaltyRatePerDay();

        public double calculatePenaltyPercent(int lateDays) {
            return Math.min(100.0, lateDays * getLatePenaltyRatePerDay());
        }

        public double calculateFinalMarks(double rawMarks, int lateDays) {
            if (lateDays <= 0) {
                return rawMarks;
            }
            double penaltyPercent = calculatePenaltyPercent(lateDays);
            double deduction = rawMarks * (penaltyPercent / 100.0);
            return Math.max(0.0, rawMarks - deduction);
        }
    }

    // CodingAssignment loses 10% per day late
    public static class CodingAssignment extends Assignment {
        public CodingAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        public double getLatePenaltyRatePerDay() {
            return 10.0;
        }
    }

    // WrittenAssignment loses 20% per day late
    public static class WrittenAssignment extends Assignment {
        public WrittenAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        public double getLatePenaltyRatePerDay() {
            return 20.0;
        }
    }

    // Student entity
    public static class Student {
        private String id;
        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // Submission managing submission state and marks
    public static class Submission {
        private Student student;
        private Assignment assignment;
        private int submitDay;
        private int lateDays;
        private SubmissionStatus status;
        private double finalMarks;

        public Submission(Student student, Assignment assignment, int submitDay, int lateDays) {
            this.student = student;
            this.assignment = assignment;
            this.submitDay = submitDay;
            this.lateDays = lateDays;
            this.status = SubmissionStatus.SUBMITTED;
            this.finalMarks = 0.0;
        }

        public Student getStudent() {
            return student;
        }

        public Assignment getAssignment() {
            return assignment;
        }

        public int getLateDays() {
            return lateDays;
        }

        public SubmissionStatus getStatus() {
            return status;
        }

        public double getFinalMarks() {
            return finalMarks;
        }

        public void grade(double awardedMarks) {
            this.finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
            this.status = SubmissionStatus.GRADED;
        }
    }

    // PortalService handling submissions and grading workflows
    public static class PortalService {
        private Map<String, Submission> submissions = new HashMap<>();

        private String getKey(Student student, Assignment assignment) {
            return student.getId() + "@" + assignment.getTitle();
        }

        public Submission submitWork(Student student, Assignment assignment, int submitDay) {
            String key = getKey(student, assignment);
            Submission existing = submissions.get(key);

            if (existing != null && existing.getStatus() == SubmissionStatus.GRADED) {
                System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
                return null;
            }

            int lateDays = Math.max(0, submitDay - assignment.getDueDay());
            Submission submission = new Submission(student, assignment, submitDay, lateDays);
            submissions.put(key, submission);

            if (lateDays == 0) {
                System.out.printf("%s's submission for '%s' received (on time). Status: Submitted.%n",
                        student.getName(), assignment.getTitle());
            } else {
                System.out.printf("%s's submission for '%s' received (%d days late). Status: Submitted.%n",
                        student.getName(), assignment.getTitle(), lateDays);
            }

            return submission;
        }

        public void gradeSubmission(Submission submission, double awardedMarks) {
            if (submission == null || submission.getStatus() != SubmissionStatus.SUBMITTED) {
                return;
            }

            submission.grade(awardedMarks);
            int lateDays = submission.getLateDays();
            Assignment assignment = submission.getAssignment();
            int finalScore = (int) Math.round(submission.getFinalMarks());

            if (lateDays == 0) {
                System.out.printf("%s graded: %d/%d. Status: Graded.%n",
                        submission.getStudent().getName(), finalScore, assignment.getMaxMarks());
            } else {
                int penaltyPercent = (int) Math.round(assignment.calculatePenaltyPercent(lateDays));
                System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: Graded.%n",
                        submission.getStudent().getName(), finalScore, assignment.getMaxMarks(), penaltyPercent);
            }
        }
    }

    public static void main(String[] args) {
        PortalService portal = new PortalService();

        // Coding assignment 'Linked List Lab' (max 50, due Mar 10)
        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        // Written assignment 'Design Essay' (max 50, due Mar 12)
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("S1", "Asha");
        Student ravi = new Student("S2", "Ravi");

        // Asha submits 'Linked List Lab' on Mar 10 (day 10)
        Submission ashaSub = portal.submitWork(asha, linkedListLab, 10);

        // Ravi submits 'Design Essay' on Mar 14 (day 14)
        Submission raviSub = portal.submitWork(ravi, designEssay, 14);

        // Faculty awards Asha 45 marks
        portal.gradeSubmission(ashaSub, 45);

        // Faculty awards Ravi 40 marks
        portal.gradeSubmission(raviSub, 40);

        // Asha attempts to resubmit 'Linked List Lab'
        portal.submitWork(asha, linkedListLab, 15);
    }
}
