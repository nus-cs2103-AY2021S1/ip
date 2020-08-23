package duke;

import main.java.duke.Duke;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dukeTesting_filePathNotExist_exceptionThrown() throws FileNotFoundException {
            Duke duke = new Duke("./data/duke.txt");
            duke.run();
    }
}