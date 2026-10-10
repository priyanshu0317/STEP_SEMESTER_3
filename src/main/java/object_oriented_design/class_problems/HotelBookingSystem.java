package object_oriented_design.class_problems;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {

    // Abstract Room base class modeling room attributes and polymorphic pricing
    public abstract static class Room {
        private String roomNumber;
        private String category;
        private List<Reservation> activeReservations;

        public Room(String roomNumber, String category) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.activeReservations = new ArrayList<>();
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getCategory() {
            return category;
        }

        public String getFullName() {
            return category + " " + roomNumber;
        }

        public abstract double calculatePrice(int nights);

        public boolean isAvailable(int startDay, int endDay) {
            for (Reservation res : activeReservations) {
                if (res.isActive()) {
                    // Overlap occurs if requested start is before existing end and requested end is after existing start
                    if (startDay < res.getEndDay() && endDay > res.getStartDay()) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void addReservation(Reservation res) {
            this.activeReservations.add(res);
        }

        public void removeReservation(Reservation res) {
            this.activeReservations.remove(res);
        }
    }

    // StandardRoom category
    public static class StandardRoom extends Room {
        private static final double RATE_PER_NIGHT = 100.0;

        public StandardRoom(String roomNumber) {
            super(roomNumber, "Standard Room");
        }

        @Override
        public double calculatePrice(int nights) {
            return RATE_PER_NIGHT * nights;
        }
    }

    // DeluxeRoom category
    public static class DeluxeRoom extends Room {
        private static final double RATE_PER_NIGHT = 150.0;

        public DeluxeRoom(String roomNumber) {
            super(roomNumber, "Deluxe Room");
        }

        @Override
        public double calculatePrice(int nights) {
            return RATE_PER_NIGHT * nights;
        }
    }

    // Suite category
    public static class Suite extends Room {
        private static final double RATE_PER_NIGHT = 250.0;

        public Suite(String roomNumber) {
            super(roomNumber, "Suite");
        }

        @Override
        public double calculatePrice(int nights) {
            return RATE_PER_NIGHT * nights;
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

    // Reservation entity holding association between Customer and Room
    public static class Reservation {
        private Customer customer;
        private Room room;
        private String dateRange;
        private int startDay;
        private int endDay;
        private double totalPrice;
        private boolean active;

        public Reservation(Customer customer, Room room, String dateRange, int startDay, int endDay, double totalPrice) {
            this.customer = customer;
            this.room = room;
            this.dateRange = dateRange;
            this.startDay = startDay;
            this.endDay = endDay;
            this.totalPrice = totalPrice;
            this.active = true;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Room getRoom() {
            return room;
        }

        public String getDateRange() {
            return dateRange;
        }

        public int getStartDay() {
            return startDay;
        }

        public int getEndDay() {
            return endDay;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            this.active = false;
            this.room.removeReservation(this);
        }
    }

    // HotelBookingService managing availability, booking, and cancellation
    public static class HotelBookingService {
        public boolean checkAvailability(Room room, String dateRange, int startDay, int endDay) {
            boolean available = room.isAvailable(startDay, endDay);
            if (available) {
                System.out.printf("%s is available from %s.%n", room.getFullName(), dateRange);
            } else {
                System.out.printf("%s is not available from %s.%n", room.getFullName(), dateRange);
            }
            return available;
        }

        public Reservation makeReservation(Customer customer, Room room, String dateRange, int startDay, int endDay) {
            if (!room.isAvailable(startDay, endDay)) {
                System.out.printf("%s is not available from %s.%n", room.getFullName(), dateRange);
                return null;
            }

            int nights = endDay - startDay;
            double price = room.calculatePrice(nights);
            Reservation res = new Reservation(customer, room, dateRange, startDay, endDay, price);
            room.addReservation(res);
            System.out.printf("Reservation confirmed for %s, %s (%s). Price: $%.2f.%n",
                    customer.getName(), room.getFullName(), dateRange, price);
            return res;
        }

        public void cancelReservation(Reservation res, boolean beforeDeadline) {
            if (res == null || !res.isActive()) {
                return;
            }
            if (!beforeDeadline) {
                System.out.println("Cancellation deadline exceeded. Reservation cannot be cancelled.");
                return;
            }

            res.cancel();
            System.out.printf("Reservation for %s, %s (%s) cancelled successfully.%n",
                    res.getCustomer().getName(), res.getRoom().getFullName(), res.getDateRange());
        }
    }

    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();

        Room standard101 = new StandardRoom("101");
        Room deluxe201 = new DeluxeRoom("201");

        Customer custA = new Customer("C1", "Customer A");
        Customer custB = new Customer("C2", "Customer B");
        Customer custC = new Customer("C3", "Customer C");

        // Customer A checks availability for Standard Room 101 from Jan 1 to Jan 5 (days 1 to 5)
        service.checkAvailability(standard101, "Jan 1 to Jan 5", 1, 5);

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5
        Reservation resA = service.makeReservation(custA, standard101, "Jan 1-5", 1, 5);

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7
        service.makeReservation(custB, standard101, "Jan 3 to Jan 7", 3, 7);

        // Customer A cancels reservation for Standard Room 101 for Jan 1-5 (before deadline)
        service.cancelReservation(resA, true);

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12 (days 41 to 43)
        service.makeReservation(custC, deluxe201, "Feb 10-12", 41, 43);
    }
}
