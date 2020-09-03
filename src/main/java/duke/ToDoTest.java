package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    Task newToDoTask = new ToDo("read book");
    
    @Test
    void getTaskTypeTest(){
        assertEquals("ToDo", newToDoTask.getTaskType());
    }
    
    @Test
    public void toStringTest() {
        assertEquals("[T][✘] read book", newToDoTask.toString());
    }

    @Test
    public void toStringInFile() {
        assertEquals("T | 0 | read book", newToDoTask.toStringInFile());
    }
}