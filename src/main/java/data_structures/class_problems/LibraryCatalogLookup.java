package data_structures.class_problems;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalogLookup {

    public static class BookRecord {
        private String isbn;
        private String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "(\"" + isbn + "\", \"" + title + "\")";
        }
    }

    /**
     * Efficiently searches for a book's title given its ISBN using binary search.
     * Time Complexity: O(log n) where n is the number of books in catalog.
     * Space Complexity: O(1) auxiliary space beyond the input list.
     *
     * @param catalog    sorted list of BookRecord objects ordered by ISBN ascending
     * @param targetIsbn the ISBN string to locate
     * @return book title if found, or "Not Found" otherwise
     */
    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        if (catalog == null || catalog.isEmpty() || targetIsbn == null) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord midBook = catalog.get(mid);
            int cmp = midBook.getIsbn().compareTo(targetIsbn);

            if (cmp == 0) {
                return midBook.getTitle();
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<BookRecord> catalog = new ArrayList<>();
        catalog.add(new BookRecord("0001112223", "Introduction to Algebra"));
        catalog.add(new BookRecord("0002223334", "Beginning Python"));
        catalog.add(new BookRecord("0003334445", "Classic Mythology"));
        catalog.add(new BookRecord("0004445556", "Data and Society"));
        catalog.add(new BookRecord("0005556667", "European History"));

        // Example 1
        String target1 = "0003334445";
        String result1 = findBook(catalog, target1);
        System.out.println("Search for \"" + target1 + "\": " + result1);

        // Example 2
        String target2 = "0009998887";
        String result2 = findBook(catalog, target2);
        System.out.println("Search for \"" + target2 + "\": " + result2);
    }
}
