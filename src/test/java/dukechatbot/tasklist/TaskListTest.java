package dukechatbot.tasklist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dukechatbot.task.Task;
import dukechatbot.task.ToDoTask;


public class TaskListTest {
    
    private TaskList taskList;

    @BeforeEach
    void init() {
        this.taskList = new TaskList(Collections.singletonList(
                new ToDoTask("hi")));
    }

    @Test
    void markTestDone_success() {
        Task itemOnList = this.taskList.getList().get(0);
        assertFalse(itemOnList.getIsDone());
        this.taskList.markDone(0);
        assertTrue(itemOnList.getIsDone());
    }
}
