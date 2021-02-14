package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ToDo class
 */

public class ToDoTest {
    @Test
    public void ToDoTest(){
        ToDo toDo = new ToDo(Task.TASK_TODO, Task.DONE, "Homework");
        assertEquals(toDo.toString(), "[ToDo][v] Homework");

    }
}
