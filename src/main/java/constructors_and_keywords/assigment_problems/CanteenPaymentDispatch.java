package constructors_and_keywords.assigment_problems;

class Payment {
    public void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {
    public void payWithProcessingFee(double amount) {
        double total = amount * 1.02;
        System.out.printf("Charged (card, incl. fee): Rs %.1f%n", total);
    }
}

public class CanteenPaymentDispatch {

    public static double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment card = (CardPayment) payment;
            card.payWithProcessingFee(amount);
            return amount * 1.02;
        } else {
            payment.pay(amount);
            return amount;
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100.0, 50.0, 200.0, 75.0, 120.0};
        double totalCollected = 0.0;

        for (int i = 0; i < payments.length; i++) {
            totalCollected += processTransaction(payments[i], amounts[i]);
        }

        System.out.printf("Total Collected: Rs %.1f%n", totalCollected);
    }
}
