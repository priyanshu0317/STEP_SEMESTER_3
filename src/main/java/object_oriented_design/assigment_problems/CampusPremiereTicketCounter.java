package object_oriented_design.assigment_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CampusPremiereTicketCounter {

    // Abstract Seat base class defining seat properties and price contract
    public abstract static class Seat {
        private String seatNumber;
        private boolean booked;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
            this.booked = false;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public boolean isBooked() {
            return booked;
        }

        public void setBooked(boolean booked) {
            this.booked = booked;
        }

        public abstract double getPrice();
    }

    // RegularSeat specialization
    public static class RegularSeat extends Seat {
        public RegularSeat(String seatNumber) {
            super(seatNumber);
        }

        @Override
        public double getPrice() {
            return 150.0;
        }
    }

    // PremiumSeat specialization
    public static class PremiumSeat extends Seat {
        public PremiumSeat(String seatNumber) {
            super(seatNumber);
        }

        @Override
        public double getPrice() {
            return 250.0;
        }
    }

    // ReclinerSeat specialization
    public static class ReclinerSeat extends Seat {
        public ReclinerSeat(String seatNumber) {
            super(seatNumber);
        }

        @Override
        public double getPrice() {
            return 400.0;
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

    // Show holding seat inventory
    public static class Show {
        private String title;
        private String showTime;
        private Map<String, Seat> seats;

        public Show(String title, String showTime) {
            this.title = title;
            this.showTime = showTime;
            this.seats = new LinkedHashMap<>();
        }

        public void addSeat(Seat seat) {
            this.seats.put(seat.getSeatNumber(), seat);
        }

        public Seat getSeat(String seatNumber) {
            return seats.get(seatNumber);
        }

        public String getShowTime() {
            return showTime;
        }
    }

    // Booking entity managing reservations
    public static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> bookedSeats;
        private double totalAmount;
        private boolean active;

        public Booking(Customer customer, Show show, List<Seat> bookedSeats, double totalAmount) {
            this.customer = customer;
            this.show = show;
            this.bookedSeats = bookedSeats;
            this.totalAmount = totalAmount;
            this.active = true;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Show getShow() {
            return show;
        }

        public List<Seat> getBookedSeats() {
            return bookedSeats;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            this.active = false;
            for (Seat seat : bookedSeats) {
                seat.setBooked(false);
            }
        }
    }

    // TicketCounterService orchestrating booking and cancellations
    public static class TicketCounterService {
        private static final int MAX_SEATS_PER_BOOKING = 6;

        public Booking bookSeats(Customer customer, Show show, List<String> requestedSeatNumbers) {
            if (requestedSeatNumbers.size() > MAX_SEATS_PER_BOOKING) {
                System.out.printf("Maximum %d seats allowed per booking.%n", MAX_SEATS_PER_BOOKING);
                return null;
            }

            List<Seat> seatsToBook = new ArrayList<>();
            for (String seatNo : requestedSeatNumbers) {
                Seat seat = show.getSeat(seatNo);
                if (seat == null || seat.isBooked()) {
                    System.out.printf("Seat %s is already booked for this show.%n", seatNo);
                    return null;
                }
                seatsToBook.add(seat);
            }

            double total = 0.0;
            List<String> confirmedNumbers = new ArrayList<>();
            for (Seat seat : seatsToBook) {
                seat.setBooked(true);
                total += seat.getPrice();
                confirmedNumbers.add(seat.getSeatNumber());
            }

            Booking booking = new Booking(customer, show, seatsToBook, total);
            System.out.printf("Booking confirmed for %s: %s. Total: \u20B9%.2f.%n",
                    customer.getName(), String.join(", ", confirmedNumbers), total);
            return booking;
        }

        public void cancelBooking(Booking booking, boolean beforeShowStarts) {
            if (booking == null || !booking.isActive()) {
                return;
            }

            if (!beforeShowStarts) {
                System.out.println("Cannot cancel booking after the show starts.");
                return;
            }

            List<String> releasedSeats = new ArrayList<>();
            for (Seat seat : booking.getBookedSeats()) {
                releasedSeats.add(seat.getSeatNumber());
            }

            booking.cancel();
            System.out.printf("%s's booking cancelled. Seats %s released.%n",
                    booking.getCustomer().getName(), String.join(", ", releasedSeats));
        }
    }

    public static void main(String[] args) {
        TicketCounterService service = new TicketCounterService();

        Show show7PM = new Show("Campus Premiere", "7 PM");
        show7PM.addSeat(new RegularSeat("A1"));
        show7PM.addSeat(new RegularSeat("A2"));
        show7PM.addSeat(new PremiumSeat("F5"));
        show7PM.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("C1", "Asha");
        Customer ravi = new Customer("C2", "Ravi");
        Customer neha = new Customer("C3", "Neha");

        // Asha books Regular seats A1 and A2 and Premium seat F5 for the 7 PM show
        Booking ashaBooking = service.bookSeats(asha, show7PM, List.of("A1", "A2", "F5"));

        // Ravi attempts to book seat A2 for the same show
        service.bookSeats(ravi, show7PM, List.of("A2"));

        // Ravi books Recliner seat R1 for the same show
        service.bookSeats(ravi, show7PM, List.of("R1"));

        // Asha cancels her booking before the show starts
        service.cancelBooking(ashaBooking, true);

        // Neha books seat A2 for the same show
        service.bookSeats(neha, show7PM, List.of("A2"));
    }
}
