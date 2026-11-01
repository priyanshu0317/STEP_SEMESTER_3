package linked_list.class_problems;

import java.util.HashMap;
import java.util.Map;

public class CampusVehiclePassSystem {

    public interface Chargeable {
        boolean canCharge();
    }

    public abstract static class Vehicle {
        private String passNumber;
        private String ownerName;
        private String typeName;

        public Vehicle(String passNumber, String ownerName, String typeName) {
            this.passNumber = passNumber;
            this.ownerName = ownerName;
            this.typeName = typeName;
        }

        public String getPassNumber() {
            return passNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getTypeName() {
            return typeName;
        }

        public abstract int getPassFee();
    }

    public static class Bike extends Vehicle {
        public Bike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Bike");
        }

        @Override
        public int getPassFee() {
            return 300;
        }
    }

    public static class Car extends Vehicle {
        public Car(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Car");
        }

        @Override
        public int getPassFee() {
            return 1000;
        }
    }

    public static class EBike extends Vehicle implements Chargeable {
        public EBike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "EBike");
        }

        @Override
        public int getPassFee() {
            return 300;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class ECar extends Vehicle implements Chargeable {
        public ECar(String passNumber, String ownerName) {
            super(passNumber, ownerName, "ECar");
        }

        @Override
        public int getPassFee() {
            return 1000;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class VehiclePassManager {
        private Map<String, Vehicle> vehicles = new HashMap<>();

        public void registerPass(String vehicleType, String passNumber, String ownerName) {
            Vehicle v;
            if (vehicleType.equalsIgnoreCase("Bike")) {
                v = new Bike(passNumber, ownerName);
            } else if (vehicleType.equalsIgnoreCase("Car")) {
                v = new Car(passNumber, ownerName);
            } else if (vehicleType.equalsIgnoreCase("EBike") || vehicleType.equalsIgnoreCase("E-Bike")) {
                v = new EBike(passNumber, ownerName);
            } else if (vehicleType.equalsIgnoreCase("ECar") || vehicleType.equalsIgnoreCase("E-Car")) {
                v = new ECar(passNumber, ownerName);
            } else {
                System.out.println("Unknown vehicle type: " + vehicleType);
                return;
            }

            vehicles.put(passNumber, v);
            System.out.printf("%s (%s) pass fee %d%n", v.getPassNumber(), v.getTypeName(), v.getPassFee());
        }

        public void charge(String passNumber) {
            Vehicle v = vehicles.get(passNumber);
            if (v instanceof Chargeable) {
                System.out.printf("%s charging bay allotted%n", passNumber);
            } else {
                System.out.printf("%s rejected: charging unsupported%n", passNumber);
            }
        }

        public void processCommands(String[] commands) {
            for (String cmd : commands) {
                String[] parts = cmd.trim().split("\\s+");
                if (parts.length == 0 || parts[0].isEmpty()) continue;
                String op = parts[0];
                if (op.equalsIgnoreCase("PASS")) {
                    registerPass(parts[1], parts[2], parts[3]);
                } else if (op.equalsIgnoreCase("CHARGE")) {
                    charge(parts[1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        VehiclePassManager manager = new VehiclePassManager();
        String[] operations = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };
        manager.processCommands(operations);
    }
}
