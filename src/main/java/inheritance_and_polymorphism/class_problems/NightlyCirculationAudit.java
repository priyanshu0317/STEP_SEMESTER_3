package inheritance_and_polymorphism.class_problems;

class AuditLibraryMember {
    private static int counter = 100;
    private static int membersEnrolled = 0;

    final String memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed = 0;

    public AuditLibraryMember(int borrowLimit) {
        counter++;
        this.memberNumber = "LIB-" + counter;
        membersEnrolled++;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class AuditFacultyMember extends AuditLibraryMember {
    protected String department;

    public AuditFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class NightlyCirculationAudit {

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(AuditLibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members != null) {
            for (AuditLibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof AuditFacultyMember) {
                        facultyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + facultyCount + " faculty | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        AuditLibraryMember m1 = new AuditLibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(AuditLibraryMember.getMembersEnrolled());

        System.out.println(isValidRenewalCode("R12A"));
        System.out.println(isValidRenewalCode("R1A"));
        System.out.println(isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        AuditLibraryMember[] batch = {
            new AuditFacultyMember(5, "Physics"),
            null,
            new AuditLibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch));
    }
}
