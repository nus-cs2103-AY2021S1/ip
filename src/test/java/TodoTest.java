import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void runTodoTest() {
        Todo todoTest = new Todo("new todo");
        Assertions.assertEquals(todoTest.toString(), "[T][X] new todo");
        Todo todoTestDone = new Todo("new todo", true);
        Assertions.assertEquals(todoTestDone.toString(), "[T][O] new todo");
    }
}
