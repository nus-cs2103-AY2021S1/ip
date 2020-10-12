package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;

public class TodoTest {
    @Test
    public void todoConstructor_todoWithName() {
        assertEquals("[T][\u2718] do homework",
                new ToDo("do homework", false).toString());
    }

}

