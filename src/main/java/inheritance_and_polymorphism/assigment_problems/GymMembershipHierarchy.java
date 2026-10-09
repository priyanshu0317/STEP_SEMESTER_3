package inheritance_and_polymorphism.assigment_problems;

class GymMemberTree {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended = 0;

    public GymMemberTree(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void setSessionsAttended(int count) {
        this.sessionsAttended = count;
    }

    public int getSessionsAttended() {
        return this.sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + this.sessionsAttended;
    }
}

class PremiumMemberTree extends GymMemberTree {
    protected String trainerName;

    public PremiumMemberTree(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + this.trainerName + " | Sessions: " + this.sessionsAttended;
    }
}

class EliteMember extends PremiumMemberTree {
    protected String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + this.trainerName + " | Locker: " + this.lockerNumber + " | Sessions: " + this.sessionsAttended;
    }
}

class GroupClassMember extends GymMemberTree {
    protected String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + this.className + " | Sessions: " + this.sessionsAttended;
    }
}

public class GymMembershipHierarchy {

    public static String classifyGeneration(GymMemberTree member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Standard Member";
    }

    public static int getTotalSessionsAttended(GymMemberTree[] members) {
        int total = 0;
        if (members != null) {
            for (GymMemberTree m : members) {
                if (m != null) {
                    total += m.getSessionsAttended();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        GymMemberTree m1 = new GymMemberTree("MEM1", 1000);
        PremiumMemberTree m2 = new PremiumMemberTree("MEM2", 2000, "Coach Riya");
        EliteMember m3 = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember m4 = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println(classifyGeneration(m3));
        System.out.println(classifyGeneration(m4));

        m2.setSessionsAttended(3);
        m3.setSessionsAttended(2);
        m4.setSessionsAttended(4);

        GymMemberTree[] list = {m2, m3, m4};
        System.out.println(getTotalSessionsAttended(list));
    }
}
