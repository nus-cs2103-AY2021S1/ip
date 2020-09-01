package test;

import bot.task.Todo;

import static org.junit.Assert.assertEquals;

public class TodoTest {

    @org.junit.Test
    public void TodoTest_doneTodo_success() {
        Todo todo = new Todo("Name", true);
        assertEquals(todo.toString(), "[T][✓] Name");
    }
}
