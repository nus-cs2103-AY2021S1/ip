package duke.core;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.ToDo;

class TaskListTest {

    @Test
    void testAdd_taskToAdd_updatedTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void testRemove_taskToRemove_updatedTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        taskList.remove(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void testMarkAsCompleted_taskToChange_updatedTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        taskList.markAsCompleted(0);
        assertEquals("\u2713", taskList.getTask(0).getState());
    }

    @Test
    void testHas_taskNumber_correspondingMessage() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals(true, taskList.has(0));
    }

    @Test
    void testFindTaskAt_localTime_correspondingTaskList() {
        TaskList taskList = new TaskList();
        Event event = new Event("a b c", LocalDate.parse("2014-12-07"));
        taskList.add(event);
        assertEquals(1, taskList.findTaskAt(LocalDate.parse("2014-12-07")).getSize());
    }

    @Test
    void testFindTaskWithDescription_taskDescription_correspondingTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals(1, taskList.findTaskWithDescription("a b c").getSize());
    }

    @Test
    void testToString_currentTaskList_correspondingMessage() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals("1.[T][\u2718] a b c\n", taskList.toString());
    }

    @Test
    void testGetTasks_taskCommand_correspondingListOfTask() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    void testGetSize_currentTaskList_correspondingTaskListSize() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("a b c"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void testGetTask_taskNumber_correspondingTask() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("a b c");
        taskList.add(toDo);
        assertEquals(toDo, taskList.getTask(0));
    }
}