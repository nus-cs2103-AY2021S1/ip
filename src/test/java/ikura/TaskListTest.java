// TaskListTest.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import ikura.task.Task;
import ikura.task.Todo;

public class TaskListTest {

    private static TaskList getDummyList() {
        return new TaskList(new DatabaseStub(new ArrayList<>(Arrays.asList(
            new Todo("one"),
            new Todo("two")
        ))));
    }

    @Test
    public void taskList_appendTask_taskAppended() {
        var list = getDummyList();

        list.addTask(new Todo("three"));

        assertEquals(list.count(), 3);
        assertEquals(list.getTaskByNumber(3).get().getName(), "three");
    }

    @Test
    public void taskList_findTask_taskFound() {
        var list = getDummyList();

        var task = list.getTaskByNumber(1);
        assertNotEquals(task, Optional.empty());
        assertEquals(task.get().getName(), "one");
    }

    @Test
    public void taskList_deleteTask_taskDeleted() {
        var list = getDummyList();

        list.removeTask(list.getTaskByNumber(1).get());
        assertEquals(list.count(), 1);

        list.removeTask(new Todo("two"));
        assertEquals(list.count(), 0);
    }
}
