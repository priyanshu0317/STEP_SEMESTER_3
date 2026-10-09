package inheritance_and_polymorphism.class_problems;

import java.util.Arrays;

class LibraryMemberWithFine {
    protected String memberId;
    protected int borrowLimit;
    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    public LibraryMemberWithFine(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
        totalFine += amount;
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return this.totalFine;
    }
}

class StudentMemberWithFine extends LibraryMemberWithFine {
    protected String course;

    public StudentMemberWithFine(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class LibraryFineLedger {

    public static void main(String[] args) {
        StudentMemberWithFine s = new StudentMemberWithFine("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}
