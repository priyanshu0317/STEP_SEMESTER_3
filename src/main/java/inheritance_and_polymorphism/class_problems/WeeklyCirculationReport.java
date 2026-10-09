package inheritance_and_polymorphism.class_problems;

class ReportMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public ReportMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public String displayInfo() {
        return "General | Books: " + this.booksBorrowed;
    }
}

class ReportStudentMember extends ReportMember {
    protected String course;

    public ReportStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return this.course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + this.course + " | Books: " + this.booksBorrowed;
    }
}

public class WeeklyCirculationReport {

    public static String batchPrint(ReportMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (ReportMember m : members) {
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof ReportStudentMember) {
                        ReportStudentMember sm = (ReportStudentMember) m;
                        sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReportMember[] batch = {
            new ReportMember("LB5", 3),
            new ReportStudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(batch));
    }
}
