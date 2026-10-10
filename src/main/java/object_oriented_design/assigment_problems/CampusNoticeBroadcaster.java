package object_oriented_design.assigment_problems;

import java.util.ArrayList;
import java.util.List;

public class CampusNoticeBroadcaster {

    // Interface for polymorphic NotificationChannel abstraction
    public interface NotificationChannel {
        String getChannelName();
        void deliver(Student student, Notice notice);
    }

    // EmailChannel implementation
    public static class EmailChannel implements NotificationChannel {
        @Override
        public String getChannelName() {
            return "Email";
        }

        @Override
        public void deliver(Student student, Notice notice) {
            System.out.printf("[Email -> %s] %s.%n", student.getName(), notice.getTitle());
        }
    }

    // AppChannel implementation
    public static class AppChannel implements NotificationChannel {
        @Override
        public String getChannelName() {
            return "App";
        }

        @Override
        public void deliver(Student student, Notice notice) {
            System.out.printf("[App -> %s] %s.%n", student.getName(), notice.getTitle());
        }
    }

    // SmsChannel implementation
    public static class SmsChannel implements NotificationChannel {
        @Override
        public String getChannelName() {
            return "SMS";
        }

        @Override
        public void deliver(Student student, Notice notice) {
            System.out.printf("[SMS -> %s] %s.%n", student.getName(), notice.getTitle());
        }
    }

    // WhatsAppChannel (extensible channel)
    public static class WhatsAppChannel implements NotificationChannel {
        @Override
        public String getChannelName() {
            return "WhatsApp";
        }

        @Override
        public void deliver(Student student, Notice notice) {
            System.out.printf("[WhatsApp -> %s] %s.%n", student.getName(), notice.getTitle());
        }
    }

    // Student entity holding department and preferred delivery channels
    public static class Student {
        private String id;
        private String name;
        private String department;
        private List<NotificationChannel> preferredChannels;

        public Student(String id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.preferredChannels = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public List<NotificationChannel> getPreferredChannels() {
            return preferredChannels;
        }

        public void addChannel(NotificationChannel channel) {
            this.preferredChannels.add(channel);
        }
    }

    // Notice entity encapsulating notice title and target departments
    public static class Notice {
        private String title;
        private List<String> targetDepartments;

        public Notice(String title, List<String> targetDepartments) {
            this.title = title;
            this.targetDepartments = targetDepartments != null ? targetDepartments : new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public List<String> getTargetDepartments() {
            return targetDepartments;
        }
    }

    // NoticeBoard orchestrating validation and broadcasting
    public static class NoticeBoard {
        private List<Student> students;

        public NoticeBoard() {
            this.students = new ArrayList<>();
        }

        public void registerStudent(Student student) {
            this.students.add(student);
        }

        public boolean postNotice(Notice notice) {
            if (notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
                System.out.println("Cannot post notice: Title is required.");
                return false;
            }

            if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return false;
            }

            System.out.printf("Notice '%s' posted to %s.%n",
                    notice.getTitle(), String.join(", ", notice.getTargetDepartments()));

            for (Student student : students) {
                if (notice.getTargetDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.deliver(student, notice);
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        NotificationChannel email = new EmailChannel();
        NotificationChannel app = new AppChannel();
        NotificationChannel sms = new SmsChannel();

        // Asha (CSE) prefers Email and App
        Student asha = new Student("S1", "Asha", "CSE");
        asha.addChannel(email);
        asha.addChannel(app);
        noticeBoard.registerStudent(asha);

        // Ravi (ECE) prefers SMS
        Student ravi = new Student("S2", "Ravi", "ECE");
        ravi.addChannel(sms);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice notice1 = new Notice("Lab Closed Tomorrow", List.of("CSE"));
        noticeBoard.postNotice(notice1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice notice2 = new Notice("Fee Deadline Extended", List.of("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice notice3 = new Notice("Sports Day", List.of());
        noticeBoard.postNotice(notice3);
    }
}
