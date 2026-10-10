package object_oriented_design.assigment_problems;

public class FitZoneMembershipDesk {

    // Status enum for membership lifecycle
    public enum MembershipStatus {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    // Interface for polymorphic MembershipPlan pricing
    public interface MembershipPlan {
        String getPlanName();
        int getDurationMonths();
        double calculateFee();
    }

    // MonthlyPlan specialization
    public static class MonthlyPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Monthly";
        }

        @Override
        public int getDurationMonths() {
            return 1;
        }

        @Override
        public double calculateFee() {
            return BASE_RATE_PER_MONTH * getDurationMonths();
        }
    }

    // QuarterlyPlan specialization (10% discount)
    public static class QuarterlyPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Quarterly";
        }

        @Override
        public int getDurationMonths() {
            return 3;
        }

        @Override
        public double calculateFee() {
            double rawFee = BASE_RATE_PER_MONTH * getDurationMonths();
            return rawFee * 0.90;
        }
    }

    // AnnualPlan specialization (25% discount)
    public static class AnnualPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Annual";
        }

        @Override
        public int getDurationMonths() {
            return 12;
        }

        @Override
        public double calculateFee() {
            double rawFee = BASE_RATE_PER_MONTH * getDurationMonths();
            return rawFee * 0.75;
        }
    }

    // Member entity
    public static class Member {
        private String id;
        private String name;

        public Member(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // Membership entity encapsulating status and transition validation
    public static class Membership {
        private Member member;
        private MembershipPlan plan;
        private double fee;
        private MembershipStatus status;

        public Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.fee = plan.calculateFee();
            this.status = MembershipStatus.ACTIVE;
        }

        public Member getMember() {
            return member;
        }

        public MembershipPlan getPlan() {
            return plan;
        }

        public double getFee() {
            return fee;
        }

        public MembershipStatus getStatus() {
            return status;
        }

        public boolean checkIn() {
            if (status == MembershipStatus.ACTIVE) {
                System.out.printf("%s checked in successfully.%n", member.getName());
                return true;
            } else if (status == MembershipStatus.FROZEN) {
                System.out.printf("Check-in denied: %s's membership is Frozen.%n", member.getName());
                return false;
            } else {
                System.out.printf("Check-in denied: %s's membership is Expired.%n", member.getName());
                return false;
            }
        }

        public boolean freeze() {
            if (status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return false;
            }
            if (status == MembershipStatus.FROZEN) {
                System.out.println("Membership is already Frozen.");
                return false;
            }
            this.status = MembershipStatus.FROZEN;
            System.out.printf("%s's membership frozen. Status: Frozen.%n", member.getName());
            return true;
        }

        public boolean unfreeze() {
            if (status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return false;
            }
            if (status == MembershipStatus.ACTIVE) {
                System.out.println("Membership is already Active.");
                return false;
            }
            this.status = MembershipStatus.ACTIVE;
            System.out.printf("%s's membership unfrozen. Status: Active.%n", member.getName());
            return true;
        }

        public void expire() {
            this.status = MembershipStatus.EXPIRED;
            System.out.printf("%s's membership expired. Status: Expired.%n", member.getName());
        }
    }

    // FitZoneService managing membership registrations
    public static class FitZoneService {
        public Membership buyMembership(Member member, MembershipPlan plan) {
            Membership membership = new Membership(member, plan);
            System.out.printf("%s membership created for %s. Fee: \u20B9%,.2f. Status: Active.%n",
                    plan.getPlanName(), member.getName(), membership.getFee());
            return membership;
        }
    }

    public static void main(String[] args) {
        FitZoneService service = new FitZoneService();

        Member asha = new Member("M1", "Asha");
        Member ravi = new Member("M2", "Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMembership = service.buyMembership(asha, new QuarterlyPlan());

        // Ravi buys a Monthly membership
        Membership raviMembership = service.buyMembership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes her membership
        ashaMembership.freeze();

        // Asha attempts to check in
        ashaMembership.checkIn();

        // Ravi's membership expires
        raviMembership.expire();

        // Ravi attempts to freeze his membership
        raviMembership.freeze();
    }
}
