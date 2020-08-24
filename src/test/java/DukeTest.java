import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    //tests if duke can run
    @Test
    public void dukeTest(){
        new Duke("src/data/duke.txt").run();
    }
}
