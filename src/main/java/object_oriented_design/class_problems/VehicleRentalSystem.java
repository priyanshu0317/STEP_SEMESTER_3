package object_oriented_design.class_problems;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {

    // Abstract Vehicle base class defining common state and pricing abstraction
    public abstract static class Vehicle {
        private String id;
        private String model;
        private boolean available;

        public Vehicle(String id, String model) {
            this.id = id;
            this.model = model;
            this.available = true;
        }

        public String getId() {
            return id;
        }

        public String getModel() {
            return model;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public abstract double calculateRentalCharge(int days);
    }

    // Concrete Sedan vehicle category
    public static class Sedan extends Vehicle {
        private static final double DAILY_RATE = 50.0;

        public Sedan(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    // Concrete SUV vehicle category
    public static class SUV extends Vehicle {
        private static final double DAILY_RATE = 80.0;

        public SUV(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    // Concrete Truck vehicle category
    public static class Truck extends Vehicle {
        private static final double DAILY_RATE = 100.0;

        public Truck(String id, String model) {
            super(id, model);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    // Customer entity
    public static class Customer {
        private String id;
        private String name;

        public Customer(String id, String name) {
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

    // Rental entity managing booking details
    public static class Rental {
        private Customer customer;
        private Vehicle vehicle;
        private int days;
        private double totalCharge;
        private boolean active;

        public Rental(Customer customer, Vehicle vehicle, int days, double totalCharge) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.totalCharge = totalCharge;
            this.active = true;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public int getDays() {
            return days;
        }

        public double getTotalCharge() {
            return totalCharge;
        }

        public boolean isActive() {
            return active;
        }

        public void completeRental() {
            this.active = false;
            this.vehicle.setAvailable(true);
        }
    }

    // RentalService orchestrating booking and return workflows
    public static class RentalService {
        private List<Rental> rentals = new ArrayList<>();

        public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getModel() + " is currently unavailable.");
                return null;
            }

            double charge = vehicle.calculateRentalCharge(days);
            vehicle.setAvailable(false);
            Rental rental = new Rental(customer, vehicle, days, charge);
            rentals.add(rental);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                    vehicle.getModel(), customer.getName(), charge);
            return rental;
        }

        public void returnVehicle(Rental rental) {
            if (rental != null && rental.isActive()) {
                rental.completeRental();
                System.out.printf("%s returned by %s.%n",
                        rental.getVehicle().getModel(), rental.getCustomer().getName());
            }
        }
    }

    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        Customer customer1 = new Customer("C1", "Customer 1");
        Customer customer2 = new Customer("C2", "Customer 2");
        Customer customer3 = new Customer("C3", "Customer 3");

        Vehicle sedanA = new Sedan("V1", "Sedan A");
        Vehicle suvB = new SUV("V2", "SUV B");

        // Customer 1 rents Sedan A for 3 days
        Rental rental1 = rentalService.rentVehicle(customer1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days (while it's rented by Customer 1)
        rentalService.rentVehicle(customer2, sedanA, 2);

        // Customer 1 returns Sedan A
        rentalService.returnVehicle(rental1);

        // Customer 3 rents SUV B for 5 days
        rentalService.rentVehicle(customer3, suvB, 5);
    }
}
