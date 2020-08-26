package task;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoConstructor_TodoWithName(){
        assertEquals("[T][\u2718] do homework",
                new Todo("do homework").toString());
    }

}
