package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void descriptionTest() {
        assertEquals(new ToDo("testing").description, "testing");
    }

    @Test
    public void defaultStatusTest() {
        assertEquals(new ToDo("testing").isDone, false);
    }

    @Test
    public void overloadedConstructorTest() {
        assertEquals(new ToDo("testing", true).isDone, true);
    }

    @Test
    public void toStringTest() {
        assertEquals(new ToDo("testing").toString(), "[T] [✘] testing");
    }

    @Test
    public void infoStringTest() {
        assertEquals(new ToDo("testing").infoString(), "T | 0 | testing");
    }
}
