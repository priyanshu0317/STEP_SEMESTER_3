package inheritance_and_polymorphism.assigment_problems;

class SettlementGymMember {
    private static int counter = 2000;
    private static int membersEnrolled = 0;

    final String membershipNumber;
    protected int monthlyFee;
    protected int feesPaid = 0;

    public SettlementGymMember(int monthlyFee) {
        counter++;
        this.membershipNumber = "GYM-" + counter;
        membersEnrolled++;
        this.monthlyFee = monthlyFee;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return this.feesPaid;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class SettlementGroupClassMember extends SettlementGymMember {
    protected String className;

    public SettlementGroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class WeeklyCheckInSettlement {

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String processWeeklyCheckIn(SettlementGymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members != null) {
            for (SettlementGymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof SettlementGroupClassMember) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        SettlementGymMember m1 = new SettlementGymMember(1000);
        System.out.println(m1.membershipNumber);
        System.out.println(SettlementGymMember.getMembersEnrolled());

        System.out.println(isValidReferralCode("G45B"));
        System.out.println(isValidReferralCode("G4B"));
        System.out.println(isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        SettlementGymMember[] batch = {
            new SettlementGroupClassMember(1500, "Zumba"),
            null,
            new SettlementGymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(batch));
    }
}
