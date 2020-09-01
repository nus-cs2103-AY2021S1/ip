package main.java.TaskList.tasks;

import Duke.TaskList.tasks.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void enumTest(){
        ToDos testTask = new ToDos("todo hello");
        assertEquals(testTask.toString(), "[T] [Not barked yet] hello");
    }
}