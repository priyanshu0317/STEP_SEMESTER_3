package abstract_classes_and_interfaces.assigment_problems;

abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;

    public ArtPiece() {
        counter++;
        this.pieceId = "ART-" + counter;
    }

    public abstract String describe();

    public String getPieceId() {
        return this.pieceId;
    }
}

class Painting extends ArtPiece {
    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + this.title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + this.title + ", carved from stone";
    }
}

public class GalleryDescriptionCards {

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}
