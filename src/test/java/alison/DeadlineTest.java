package alison;

import alison.exception.AlisonException;
import alison.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void formatTest() throws AlisonException {
        Deadline ddl = new Deadline("final exam", "2020-12-08");
        ddl.parseTime();
        assertEquals(ddl.toString()
                ,"[D][✗] final exam (by: Dec 8 2020)");
    }

}
