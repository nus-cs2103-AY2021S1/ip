import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void runTodoTest() {
        Todo todoTest = new Todo("new todo");
        assertEquals(todoTest.toString(), "[T][X] new todo");
        Todo todoTestDone = new Todo("new todo", true);
        assertEquals(todoTestDone.toString(), "[T][O] new todo");
    }
}
