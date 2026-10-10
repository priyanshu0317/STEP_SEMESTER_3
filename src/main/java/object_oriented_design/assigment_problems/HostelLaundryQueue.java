package object_oriented_design.assigment_problems;

public class HostelLaundryQueue {

    // Abstract WashType base class defining duration and pricing contract
    public abstract static class WashType {
        private String name;
        private int durationMinutes;
        private double charge;

        public WashType(String name, int durationMinutes, double charge) {
            this.name = name;
            this.durationMinutes = durationMinutes;
            this.charge = charge;
        }

        public String getName() {
            return name;
        }

        public int getDurationMinutes() {
            return durationMinutes;
        }

        public double getCharge() {
            return charge;
        }
    }

    // Quick wash specialization
    public static class QuickWash extends WashType {
        public QuickWash() {
            super("Quick", 30, 20.0);
        }
    }

    // Normal wash specialization
    public static class NormalWash extends WashType {
        public NormalWash() {
            super("Normal", 45, 30.0);
        }
    }

    // Heavy wash specialization
    public static class HeavyWash extends WashType {
        public HeavyWash() {
            super("Heavy", 60, 45.0);
        }
    }

    // Delicate wash specialization (extensibility)
    public static class DelicateWash extends WashType {
        public DelicateWash() {
            super("Delicate", 25, 25.0);
        }
    }

    // Student entity
    public static class Student {
        private String id;
        private String name;

        public Student(String id, String name) {
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

    // WashCycle tracking an active wash session
    public static class WashCycle {
        private Student student;
        private WashingMachine machine;
        private WashType washType;

        public WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public Student getStudent() {
            return student;
        }

        public WashingMachine getMachine() {
            return machine;
        }

        public WashType getWashType() {
            return washType;
        }
    }

    // WashingMachine encapsulating its busy status
    public static class WashingMachine {
        private String id;
        private String name;
        private boolean busy;
        private WashCycle currentCycle;

        public WashingMachine(String id, String name) {
            this.id = id;
            this.name = name;
            this.busy = false;
            this.currentCycle = null;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isFree() {
            return !busy;
        }

        public boolean startWash(WashCycle cycle) {
            if (busy) {
                return false;
            }
            this.busy = true;
            this.currentCycle = cycle;
            return true;
        }

        public void completeCycle() {
            this.busy = false;
            this.currentCycle = null;
        }
    }

    // LaundryService managing machine allocation
    public static class LaundryService {
        public WashCycle startWash(Student student, WashingMachine machine, WashType washType) {
            if (!machine.isFree()) {
                System.out.printf("Machine %s is currently busy.%n", machine.getName());
                return null;
            }

            WashCycle cycle = new WashCycle(student, machine, washType);
            machine.startWash(cycle);
            System.out.printf("%s wash started on %s for %s (%d min). Charge: \u20B9%.2f.%n",
                    washType.getName(), machine.getName(), student.getName(),
                    washType.getDurationMinutes(), washType.getCharge());
            return cycle;
        }

        public void completeWash(WashingMachine machine) {
            machine.completeCycle();
            System.out.printf("%s cycle completed. %s is now free.%n", machine.getName(), machine.getName());
        }
    }

    public static void main(String[] args) {
        LaundryService service = new LaundryService();

        Student asha = new Student("S01", "Asha");
        Student ravi = new Student("S02", "Ravi");
        Student neha = new Student("S03", "Neha");

        WashingMachine m1 = new WashingMachine("M1", "M1");
        WashingMachine m2 = new WashingMachine("M2", "M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        // Asha starts a Quick wash on Machine M1
        service.startWash(asha, m1, quick);

        // Ravi attempts to start a Heavy wash on Machine M1
        service.startWash(ravi, m1, heavy);

        // Ravi starts a Heavy wash on Machine M2
        service.startWash(ravi, m2, heavy);

        // Machine M1 completes its cycle
        service.completeWash(m1);

        // Neha starts a Normal wash on Machine M1
        service.startWash(neha, m1, normal);
    }
}
