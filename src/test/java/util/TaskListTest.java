package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.ToDo;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList list = new TaskList();
        assertEquals(list.getSize(), 0);
        list.addTask(new ToDo("todo 1"));
        list.addTask(new ToDo("todo 2"));
        list.addTask(new ToDo("todo 3"));
        list.addTask(new ToDo("todo 4"));
        assertEquals(list.getSize(), 4);
    }
}
