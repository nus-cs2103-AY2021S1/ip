package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoConstructor_todoWithName(){  
        assertEquals("[T][\u2718] do homework",
                new Todo("do homework").toString());
    }

}
