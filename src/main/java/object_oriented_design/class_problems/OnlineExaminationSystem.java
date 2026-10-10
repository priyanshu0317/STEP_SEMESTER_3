package object_oriented_design.class_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OnlineExaminationSystem {

    // Abstract Question class modeling common attributes and evaluation behavior
    public abstract static class Question {
        private int id;
        private String prompt;
        private int maxPoints;

        public Question(int id, String prompt, int maxPoints) {
            this.id = id;
            this.prompt = prompt;
            this.maxPoints = maxPoints;
        }

        public int getId() {
            return id;
        }

        public String getPrompt() {
            return prompt;
        }

        public int getMaxPoints() {
            return maxPoints;
        }

        public abstract boolean evaluate(String studentAnswer);
    }

    // MultipleChoiceQuestion specialization
    public static class MultipleChoiceQuestion extends Question {
        private String correctAnswer;

        public MultipleChoiceQuestion(int id, String prompt, int maxPoints, String correctAnswer) {
            super(id, prompt, maxPoints);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String studentAnswer) {
            return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        }
    }

    // TrueFalseQuestion specialization
    public static class TrueFalseQuestion extends Question {
        private String correctAnswer;

        public TrueFalseQuestion(int id, String prompt, int maxPoints, String correctAnswer) {
            super(id, prompt, maxPoints);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String studentAnswer) {
            return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        }
    }

    // ShortAnswerQuestion specialization
    public static class ShortAnswerQuestion extends Question {
        private String correctAnswer;

        public ShortAnswerQuestion(int id, String prompt, int maxPoints, String correctAnswer) {
            super(id, prompt, maxPoints);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String studentAnswer) {
            return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
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

    // Examination aggregating questions
    public static class Examination {
        private String id;
        private String title;
        private List<Question> questions;

        public Examination(String id, String title) {
            this.id = id;
            this.title = title;
            this.questions = new ArrayList<>();
        }

        public void addQuestion(Question question) {
            this.questions.add(question);
        }

        public String getTitle() {
            return title;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public int getTotalMarks() {
            int total = 0;
            for (Question q : questions) {
                total += q.getMaxPoints();
            }
            return total;
        }
    }

    // Attempt representing a single student's session on an exam
    public static class Attempt {
        private Student student;
        private Examination examination;
        private Map<Integer, String> answers;
        private boolean submitted;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            this.answers = new LinkedHashMap<>();
            this.submitted = false;
            System.out.printf("%s started by %s.%n", examination.getTitle(), student.getName());
        }

        public void recordAnswer(int questionId, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            answers.put(questionId, answer);
            System.out.printf("Answer recorded for Question %d.%n", questionId);
        }

        public void submit() {
            if (submitted) {
                System.out.println("Examination already submitted.");
                return;
            }
            this.submitted = true;
            System.out.printf("%s submitted by %s.%n", examination.getTitle(), student.getName());

            int totalScore = 0;
            StringBuilder resultBuilder = new StringBuilder("Result: ");
            List<Question> questions = examination.getQuestions();

            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                String ans = answers.get(q.getId());
                boolean isCorrect = q.evaluate(ans);
                int pointsScored = isCorrect ? q.getMaxPoints() : 0;
                totalScore += pointsScored;

                resultBuilder.append(String.format("Question %d: %s (%d points)",
                        q.getId(), (isCorrect ? "Correct" : "Incorrect"), pointsScored));
                if (i < questions.size() - 1) {
                    resultBuilder.append(", ");
                }
            }

            resultBuilder.append(String.format(". Total score: %d/%d.", totalScore, examination.getTotalMarks()));
            System.out.println(resultBuilder.toString());
        }
    }

    public static void main(String[] args) {
        Examination examA = new Examination("EX101", "Exam A");
        examA.addQuestion(new MultipleChoiceQuestion(1, "What is the capital of France?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion(2, "Java supports multiple class inheritance.", 5, "False"));

        Student student1 = new Student("S1", "Student 1");

        // Student 1 starts Exam A
        Attempt attempt = new Attempt(student1, examA);

        // Student 1 answers Question 1 (MCQ) with option C
        attempt.recordAnswer(1, "C");

        // Student 1 answers Question 2 (TF) with True
        attempt.recordAnswer(2, "True");

        // Student 1 submits Exam A
        attempt.submit();

        // Student 1 attempts to change answer for Question 1
        attempt.recordAnswer(1, "B");
    }
}
