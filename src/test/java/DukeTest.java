import org.junit.Test;
//import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test void validFilePath(String filepath) {
        File file = new File(filepath);
        boolean isCreated = file.exists();
        assertEquals(true, isCreated);
    }
}