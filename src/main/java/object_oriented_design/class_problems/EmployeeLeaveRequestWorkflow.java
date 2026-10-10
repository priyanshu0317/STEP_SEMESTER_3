package object_oriented_design.class_problems;

public class EmployeeLeaveRequestWorkflow {

    // Status enum representing lifecycle of a leave request
    public enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    // Abstract Employee base class
    public abstract static class Employee {
        private String id;
        private String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract boolean isEligibleForLeave(int days);
    }

    // FullTimeEmployee specialization
    public static class FullTimeEmployee extends Employee {
        private static final int MAX_LEAVE_DAYS = 20;

        public FullTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public boolean isEligibleForLeave(int days) {
            return days <= MAX_LEAVE_DAYS;
        }
    }

    // PartTimeEmployee specialization
    public static class PartTimeEmployee extends Employee {
        private static final int MAX_LEAVE_DAYS = 10;

        public PartTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public boolean isEligibleForLeave(int days) {
            return days <= MAX_LEAVE_DAYS;
        }
    }

    // Contractor specialization
    public static class Contractor extends Employee {
        private static final int MAX_LEAVE_DAYS = 5;

        public Contractor(String id, String name) {
            super(id, name);
        }

        @Override
        public boolean isEligibleForLeave(int days) {
            return days <= MAX_LEAVE_DAYS;
        }
    }

    // LeaveRequest entity with state transition guards
    public static class LeaveRequest {
        private Employee employee;
        private String dateRange;
        private int days;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, String dateRange, int days) {
            this.employee = employee;
            this.dateRange = dateRange;
            this.days = days;
            this.status = LeaveStatus.PENDING;
        }

        public Employee getEmployee() {
            return employee;
        }

        public String getDateRange() {
            return dateRange;
        }

        public int getDays() {
            return days;
        }

        public LeaveStatus getStatus() {
            return status;
        }

        public boolean setStatus(LeaveStatus newStatus) {
            if ((this.status == LeaveStatus.APPROVED || this.status == LeaveStatus.REJECTED)
                    && newStatus == LeaveStatus.PENDING) {
                String currentFormatted = this.status == LeaveStatus.APPROVED ? "Approved" : "Rejected";
                System.out.printf("Cannot change leave request status from %s to Pending.%n", currentFormatted);
                return false;
            }
            this.status = newStatus;
            return true;
        }
    }

    // Manager/Reviewer actor
    public static class Manager {
        private String name;

        public Manager(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void approveRequest(LeaveRequest request) {
            if (request != null && request.getStatus() == LeaveStatus.PENDING) {
                request.setStatus(LeaveStatus.APPROVED);
                System.out.printf("%s's leave request (%s) approved. Status: Approved.%n",
                        request.getEmployee().getName(), request.getDateRange());
            }
        }

        public void rejectRequest(LeaveRequest request) {
            if (request != null && request.getStatus() == LeaveStatus.PENDING) {
                request.setStatus(LeaveStatus.REJECTED);
                System.out.printf("%s's leave request (%s) rejected. Status: Rejected.%n",
                        request.getEmployee().getName(), request.getDateRange());
            }
        }
    }

    public static LeaveRequest submitLeave(Employee employee, String dateRange, int days) {
        LeaveRequest request = new LeaveRequest(employee, dateRange, days);
        System.out.printf("Leave request submitted for %s (%s). Status: Pending.%n",
                employee.getName(), dateRange);
        return request;
    }

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("E01", "John");
        Employee jane = new PartTimeEmployee("E02", "Jane");

        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        // FullTimeEmployee John submits leave request for 5 days (Jan 1-5)
        LeaveRequest johnRequest = submitLeave(john, "Jan 1-5", 5);

        // Manager Alice reviews John's request and approves it
        alice.approveRequest(johnRequest);

        // PartTimeEmployee Jane submits leave request for 2 days (Feb 10-11)
        LeaveRequest janeRequest = submitLeave(jane, "Feb 10-11", 2);

        // Manager Bob reviews Jane's request and rejects it
        bob.rejectRequest(janeRequest);

        // John attempts to change his approved leave request to Pending
        johnRequest.setStatus(LeaveStatus.PENDING);
    }
}
