package sparrow.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StorageTest {

    Storage storage = new Storage();

    @Test
    public void stringToDate() {
        // correct format -> returns true
        String correctFormat = "2020-10-31";
        LocalDate correctDate = LocalDate.parse("2020-10-31");
        assertEquals(storage.stringToDate(correctFormat), correctDate);

        // wrong order -> throws exception
        String wrongFormat = "31-10-2020";
        assertThrows(DateTimeParseException.class, () -> { storage.stringToDate(wrongFormat);
        });

        // wrong format -> throws exception
        String notDate = "Tuesday";
        assertThrows(DateTimeParseException.class, () -> {
            storage.stringToDate(notDate);
        });
    }
}
