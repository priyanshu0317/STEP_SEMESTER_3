package inheritance_and_polymorphism.assigment_problems;

class GymMemberAnnouncer {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended = 0;

    public GymMemberAnnouncer(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + this.sessionsAttended;
    }
}

class PremiumMemberAnnouncer extends GymMemberAnnouncer {
    protected String trainerName;

    public PremiumMemberAnnouncer(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return this.trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + this.trainerName + " | Sessions: " + this.sessionsAttended;
    }
}

public class MonthlyAttendanceAnnouncer {

    public static String batchPrint(GymMemberAnnouncer[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMemberAnnouncer m : members) {
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof PremiumMemberAnnouncer) {
                        PremiumMemberAnnouncer pm = (PremiumMemberAnnouncer) m;
                        sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GymMemberAnnouncer[] batch = {
            new GymMemberAnnouncer("MEM6", 1000),
            new PremiumMemberAnnouncer("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(batch));
    }
}
