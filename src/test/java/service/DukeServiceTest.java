package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DukeServiceTest {
    @Test
    public void ParseFailedTest() {
        DukeService service = new DukeService();

        try {
            service.markAsDone(-1);
            fail("Shoud not okay");
        } catch (Exception e) {
             //okay
        }
    }
}
