package access_modifiers_and_encapsulation.assigment_problems;

import java.util.Arrays;

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = (bookIds != null) ? Arrays.copyOf(bookIds, bookIds.length) : new String[0];
    }

    public String getMemberId() {
        return this.memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] corrected = Arrays.copyOf(this.bookIds, this.bookIds.length);
        if (index >= 0 && index < corrected.length) {
            corrected[index] = newId;
        }
        return new LoanReceipt(this.memberId, corrected);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }
}

class CirculationLedger {
    static String branchCode;

    static {
        branchCode = "PT-MAIN";
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnlyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnlyCount + " reference-only | " + regularCount + " regular";
    }
}

public class LoanReceiptLedger {

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(r.getBookIds()));
        System.out.println(Arrays.toString(corrected.getBookIds()));

        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(CirculationLedger.processNightlyCirculation(batch));
    }
}
