package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class TaskListTest {

    @Test
    public void addTaskToList_addTestTask_addsTask() throws DukeException {
        TaskList testTaskList = new TaskList();
        testTaskList.addToList(new ToDo("test1"));
        assertEquals(1, testTaskList.getListSize());
    }

    @Test
    public void addTaskToList_noTask_throwsException() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.addToList(null);
            assert false;
        } catch (DukeException e) {
            assert true;
        }
    }

    @Test
    public void removeTaskFromList_withinIndex_listSizeDecrease() throws DukeException {
        TaskList testTaskList = new TaskList();
        testTaskList.addToList(new ToDo("test1"));
        testTaskList.removeFromList(0);
        assertEquals(0, testTaskList.getListSize());
    }

    @Test
    public void removeTaskFromList_negativeNumber_throwException() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.removeFromList(-1);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void removeTaskFromList_greaterThanSize_throwException() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.removeFromList(4);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void geTaskFromList_greaterThanSize_throwException() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.getTaskAtIndex(4);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void geTaskFromList_negativeNumber_throwException() {
        TaskList testTaskList = new TaskList();
        try {
            testTaskList.getTaskAtIndex(-1);
            assert false;
        } catch (IndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    public void geTaskFromList_withinSize_returnsTask() throws DukeException {
        TaskList testTaskList = new TaskList();
        ToDo testTodo = new ToDo("test1");
        testTaskList.addToList(testTodo);
        assertEquals(testTodo, testTaskList.getTaskAtIndex(0));
    }



}
