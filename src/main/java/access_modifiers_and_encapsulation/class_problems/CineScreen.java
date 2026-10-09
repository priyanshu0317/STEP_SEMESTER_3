package access_modifiers_and_encapsulation.class_problems;

public class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected");
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
        } else {
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }
    }

    public void bookSeat() {
        if (this.seatsAvailable > 0) {
            this.seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (this.seatsAvailable < this.seatsTotal) {
            this.seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }

    public static void main(String[] args) {
        new CineScreen(0);

        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat();
        System.out.println(c.getSeatsAvailable());

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();
        System.out.println(c.getSeatsAvailable());
    }
}
