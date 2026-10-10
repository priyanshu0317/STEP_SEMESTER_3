package abstract_classes_and_interfaces.class_problems;

abstract class DeliveryNote {
    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    private String trackingId;

    public ParcelNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + this.trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    private String trackingId;

    public LetterNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + this.trackingId + " delivered";
    }
}

public class PackageDropOffLog {

    public static void logAll(DeliveryNote[] notes) {
        if (notes != null) {
            for (DeliveryNote note : notes) {
                if (note != null) {
                    System.out.println(note.confirmDelivery());
                }
            }
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());

        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;
        logAll(new DeliveryNote[]{ref, new LetterNote("TRK-2")});
    }
}
