package duke;

import static duke.storage.Storage.DEFAULT_STORAGE_FILEPATH;
import static duke.ui.Ui.GOODBYE;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void run_byeString_goodbyeMessage() {
        ByteArrayInputStream in = new ByteArrayInputStream("bye".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Duke(DEFAULT_STORAGE_FILEPATH, in).run();
        assertTrue(out.toString().contains(GOODBYE.replaceAll("\n", "")));
    }
}
