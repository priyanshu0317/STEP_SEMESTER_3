package inheritance_and_polymorphism.class_problems;

class LibraryMemberHierarchical {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMemberHierarchical(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void setBooksBorrowed(int count) {
        this.booksBorrowed = count;
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + this.booksBorrowed;
    }
}

class StudentMemberHierarchical extends LibraryMemberHierarchical {
    protected String course;

    public StudentMemberHierarchical(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + this.course + " | Books Borrowed: " + this.booksBorrowed;
    }
}

class HonorsStudentMember extends StudentMemberHierarchical {
    protected int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + this.course + " | Bonus Limit: " + this.bonusLimit + " | Books Borrowed: " + this.booksBorrowed;
    }
}

class FacultyMember extends LibraryMemberHierarchical {
    protected String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + this.department + " | Books Borrowed: " + this.booksBorrowed;
    }
}

public class LibraryMembershipHierarchy {

    public static String classifyGeneration(LibraryMemberHierarchical member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Base member";
    }

    public static int getTotalBooksBorrowed(LibraryMemberHierarchical[] members) {
        int total = 0;
        if (members != null) {
            for (LibraryMemberHierarchical m : members) {
                if (m != null) {
                    total += m.getBooksBorrowed();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        LibraryMemberHierarchical m1 = new LibraryMemberHierarchical("STU1", 3);
        StudentMemberHierarchical m2 = new StudentMemberHierarchical("STU2", 3, "CSE");
        HonorsStudentMember m3 = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember m4 = new FacultyMember("STU4", 5, "Physics");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println(classifyGeneration(m3));
        System.out.println(classifyGeneration(m4));

        m2.setBooksBorrowed(2);
        m3.setBooksBorrowed(1);
        m4.setBooksBorrowed(3);

        LibraryMemberHierarchical[] list = {m2, m3, m4};
        System.out.println(getTotalBooksBorrowed(list));
    }
}
