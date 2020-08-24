package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void checkStringOutput() throws DukeException {
        Deadline d = new Deadline("return book", "2020-08-28");
        assertEquals("[D][ ] return book (by: Aug 28 2020)", d.toString());
        d.markAsDone();
        assertEquals("[D][X] return book (by: Aug 28 2020)", d.toString());
    }

    @Test
    public void checkFileFormat() throws DukeException {
        Deadline d = new Deadline("birthday party", "2040-01-01");
        assertEquals("D , 0 , birthday party , 2040-01-01", d.fileFormat());
        d.markAsDone();
        assertEquals("D , 1 , birthday party , 2040-01-01", d.fileFormat());
    }
}
