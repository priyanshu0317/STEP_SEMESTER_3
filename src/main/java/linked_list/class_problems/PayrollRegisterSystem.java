package linked_list.class_problems;

import java.util.ArrayList;
import java.util.List;

public class PayrollRegisterSystem {

    public abstract static class StaffMember {
        private String name;
        private String type;

        public StaffMember(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public abstract int calculatePay();

        public String generatePayslip() {
            return String.format("Payslip[name=%s, type=%s, pay=%d]", name, type, calculatePay());
        }
    }

    public static class FullTimeStaff extends StaffMember {
        private int salary;

        public FullTimeStaff(String name, int salary) {
            super(name, "FullTime");
            this.salary = salary;
        }

        @Override
        public int calculatePay() {
            return salary;
        }
    }

    public static class PartTimeStaff extends StaffMember {
        private int hours;
        private int hourlyRate;

        public PartTimeStaff(String name, int hours, int hourlyRate) {
            super(name, "PartTime");
            this.hours = hours;
            this.hourlyRate = hourlyRate;
        }

        @Override
        public int calculatePay() {
            return hours * hourlyRate;
        }
    }

    public static class InternStaff extends StaffMember {
        private int stipend;

        public InternStaff(String name, int stipend) {
            super(name, "Intern");
            this.stipend = stipend;
        }

        @Override
        public int calculatePay() {
            return stipend;
        }
    }

    public static class PayrollManager {
        private List<StaffMember> staffList = new ArrayList<>();

        public void addStaff(StaffMember member) {
            staffList.add(member);
        }

        public void processPayroll() {
            int totalPay = 0;
            StaffMember topEarner = null;
            int maxPay = -1;

            for (StaffMember member : staffList) {
                System.out.println(member.generatePayslip());
                int pay = member.calculatePay();
                totalPay += pay;
                if (pay > maxPay) {
                    maxPay = pay;
                    topEarner = member;
                }
            }

            System.out.println("Total " + totalPay);
            if (topEarner != null) {
                System.out.println("top earner " + topEarner.getName());
            }
        }

        public void processInput(String[] lines) {
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 0 || parts[0].isEmpty()) continue;

                String type = parts[0];
                String name = parts[1];
                if (type.equalsIgnoreCase("FullTime")) {
                    int salary = Integer.parseInt(parts[2]);
                    addStaff(new FullTimeStaff(name, salary));
                } else if (type.equalsIgnoreCase("PartTime")) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    addStaff(new PartTimeStaff(name, hours, rate));
                } else if (type.equalsIgnoreCase("Intern")) {
                    int stipend = Integer.parseInt(parts[2]);
                    addStaff(new InternStaff(name, stipend));
                }
            }
            processPayroll();
        }
    }

    public static void main(String[] args) {
        PayrollManager manager = new PayrollManager();
        String[] staffInputs = {
            "FullTime Asha 50000",
            "PartTime Ravi 80 300",
            "Intern Neha 15000"
        };
        manager.processInput(staffInputs);
    }
}
