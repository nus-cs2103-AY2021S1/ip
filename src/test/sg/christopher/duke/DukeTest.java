package christopher.duke;

import org.junit.jupiter.api.Test;
import sg.christopher.duke.Duke;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DukeTest {
    @Test
    public void duke_runsNormally() {
        InputStream normalSystemIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        Duke.main(new String[0]);
        System.setIn(normalSystemIn);
        return;
    }
}
