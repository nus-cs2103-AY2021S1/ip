package nite.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nite.exception.NiteException;

public class DeadlineTest {
    @Test
    public void testToData() {
        try {
            assertEquals("D~0~tP AB3 familiarization~2020-08-26 2359\n",
                    new Deadline("tP AB3 familiarization", "2020-08-26 2359").toData());
        } catch (NiteException ex) {
            fail();
        }
    }
}
