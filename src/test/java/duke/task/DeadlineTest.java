package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void factoryMethods_success() {
        String command1 = "test /by 2/8/20";
        String command2 = "test";
        String command3 = "2020-08-02";
        String[] data = new String[]{"D", "0", command2, command3};
        try {
            assertEquals(Deadline.create(command1), Deadline.createFromFile(data));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void print_success() {
        try {
            Deadline test = Deadline.create("test /by 9-12-10");
            test.setDone();
            assertEquals("D | 1 | test | 2010-12-09", test.toDataString());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
