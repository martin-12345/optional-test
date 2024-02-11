package mseries.jacksontest;

import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class MyOptionalTest {

    private Optional<byte[]> getBook(String title) {
        Book b;
        byte[] bookBytes = null;
        if (!title.equals("X")) {
            b = new Book(title);
            bookBytes = b.getTitle().getBytes();
        }
        return Optional.ofNullable(bookBytes);
    }

    private byte[] regenerate(String title) {
        return (title+"-regen").getBytes();
    }

    @Test
	public void nullTest() {
        String title = "X";
        Optional<byte[]> b = getBook(title);

        byte[] pdfArray;
        pdfArray = b.orElseGet(() -> regenerate(title));
        assertEquals("X-regen", new String(pdfArray));
    }

    @Test
    public void notNullTest() {
        String title = "Andy Gibb";
        Optional<byte[]> b = getBook(title);

        byte[] pdfArray;
        pdfArray = b.orElseGet(() -> regenerate(title));
        assertEquals("Andy Gibb", new String(pdfArray));
    }
}
