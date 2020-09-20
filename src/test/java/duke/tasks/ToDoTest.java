package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    public final ToDo toDo = new ToDo("test task");
    
    @Test
    void testDescription_isEquivalent() {
        assertEquals("test task", toDo.getDescription());
    }
    
    @Test
    void testStatusIcon_matchesExpectedStatus() {
        assertEquals("[\u2718] ", toDo.statusIcon());
    }
    
    @Test
    void testSaveTaskEncoding_matchesExpectedStatus() {
        assertEquals("T#sep#false#test task#sep#", toDo.saveTask());
    }
    
    @Test
    void testMarkDoneTask_changesOutput() {
        toDo.doTask();
        assertEquals("[\u2713] test task", toDo.toString());
    }
}
