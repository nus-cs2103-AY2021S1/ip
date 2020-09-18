import static org.junit.Assert.assertEquals;

import bot.task.Todo;

public class TodoTest {

    @org.junit.Test
    public void todoTest_doneTodo_success() {
        Todo todo = new Todo("Name", true);
        assertEquals(todo.toString(), "[T][✓] Name");
    }
}
