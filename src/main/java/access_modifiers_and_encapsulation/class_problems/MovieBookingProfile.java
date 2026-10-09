package access_modifiers_and_encapsulation.class_problems;

public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println(p1.getName());

        p1.setConfirmed(true);
        System.out.println(p1.isConfirmed());

        p1.setOtp("4471");
    }
}
