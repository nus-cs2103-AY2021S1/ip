import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void run() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("bye".getBytes());
        System.setIn(in);
        new Duke("data/duke.txt").run();
        System.setIn(sysInBackup);
    }
}
