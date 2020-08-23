import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import storage.Storage;

public class DukeTest {
    /**
     * tests whether storage path is correct
     */
    @Test
    public void pathTest() {
        // directory is in test now
        // go up test -> src -> iP -> data -> duke.txt
        Duke testDuke = new Duke("../../data/duke.txt");
        assertEquals(testDuke.getStores(), new Storage("../../data/duke.txt"));
    }
}
