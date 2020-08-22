package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void constructor_anyInput_noException() {
        try {
            ToDo t = new ToDo("return books");
        } catch (Exception e) {
            fail();
        }
    }
}
