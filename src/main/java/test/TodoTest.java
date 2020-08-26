package test;

import bot.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @org.junit.Test
    public void TodoTest_doneTodo_success() {
        Todo todo = new Todo("Name", true);
        assertEquals(todo.toString(), "[T][✓] Name");
    }
}
