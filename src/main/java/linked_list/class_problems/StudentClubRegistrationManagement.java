package linked_list.class_problems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StudentClubRegistrationManagement {

    public static class StudentMember {
        private String rollNumber;
        private String name;

        public StudentMember(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudentMember that = (StudentMember) o;
            // Only roll number is considered for uniqueness
            return Objects.equals(rollNumber, that.rollNumber);
        }

        @Override
        public int hashCode() {
            // Hash based solely on roll number
            return Objects.hash(rollNumber);
        }
    }

    public static class ClubRegistrationSystem {
        private Set<StudentMember> members = new HashSet<>();

        public boolean addMember(String rollNumber, String name) {
            StudentMember student = new StudentMember(rollNumber, name);
            if (members.contains(student)) {
                System.out.println("duplicate rejected");
                return false;
            } else {
                members.add(student);
                System.out.println("Added");
                return true;
            }
        }

        public void printMemberCount() {
            System.out.println("member count " + members.size());
        }

        public boolean containsMember(String rollNumber, String name) {
            StudentMember probe = new StudentMember(rollNumber, name);
            boolean exists = members.contains(probe);
            System.out.println("contains: " + exists);
            return exists;
        }

        public void processCommands(String[] commands) {
            boolean countPrinted = false;
            for (String cmd : commands) {
                String[] parts = cmd.trim().split("\\s+");
                if (parts.length == 0 || parts[0].isEmpty()) continue;
                String op = parts[0];

                if (op.equalsIgnoreCase("ADD")) {
                    addMember(parts[1], parts[2]);
                } else if (op.equalsIgnoreCase("CONTAINS")) {
                    if (!countPrinted) {
                        printMemberCount();
                        countPrinted = true;
                    }
                    containsMember(parts[1], parts[2]);
                }
            }
            if (!countPrinted) {
                printMemberCount();
            }
        }
    }

    public static void main(String[] args) {
        ClubRegistrationSystem system = new ClubRegistrationSystem();
        String[] operations = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };
        system.processCommands(operations);
    }
}
