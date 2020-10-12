package duke.task;

import static duke.TestUtils.DEADLINE_UNDONE_STRING;
import static duke.TestUtils.EVENT_UNDONE_STRING;
import static duke.TestUtils.TODO_DONE_STRING;
import static duke.TestUtils.TODO_UNDONE_STRING;
import static duke.TestUtils.createDoneToDo;
import static duke.TestUtils.createUndoneDeadline;
import static duke.TestUtils.createUndoneDeadlineDifferentDate;
import static duke.TestUtils.createUndoneDeadlineDifferentTitle;
import static duke.TestUtils.createUndoneEvent;
import static duke.TestUtils.createUndoneToDo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DuplicateTaskException;
import duke.exception.InvalidTaskException;

public class TaskListTest {
    @Test
    public void addTask_addToDo_doesNotThrowException() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(createUndoneToDo()));
    }

    @Test
    public void addTask_addDuplicateToDo_throwsException() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> taskList.add(createUndoneToDo()));
        assertThrows(DuplicateTaskException.class, () -> taskList.add(createUndoneToDo()));
    }

    @Test
    public void markAsDone_validIndex_returnsTask() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneToDo());
            Task returnedTask = taskList.markAsDone(1);
            assertEquals(createDoneToDo(), returnedTask);
        });
    }

    @Test
    public void markAsDone_zeroIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(InvalidTaskException.class, () -> {
            taskList.add(createUndoneToDo());
            taskList.markAsDone(0);
        });
    }

    @Test
    public void markAsDone_bigIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(InvalidTaskException.class, () -> {
            taskList.add(createUndoneToDo());
            taskList.markAsDone(2);
        });
    }

    @Test
    public void deleteTask_validIndex_returnsTask() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneToDo());
            Task returnedTask = taskList.delete(1);
            assertEquals(createUndoneToDo(), returnedTask);
        });
    }

    @Test
    public void deleteTask_zeroIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(InvalidTaskException.class, () -> {
            taskList.add(createUndoneToDo());
            taskList.delete(0);
        });
    }

    @Test
    public void deleteTask_bigIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(InvalidTaskException.class, () -> {
            taskList.add(createUndoneToDo());
            taskList.delete(2);
        });
    }

    @Test
    public void showList_addDoneDeleteTask_returnsCorrectString() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            assertEquals("No tasks added.\n", taskList.showList().toString());

            taskList.add(createUndoneToDo());
            assertEquals("1. " + TODO_UNDONE_STRING + "\n", taskList.showList().toString());

            taskList.markAsDone(1);
            assertEquals("1. " + TODO_DONE_STRING + "\n", taskList.showList().toString());

            taskList.delete(1);
            assertEquals("No tasks added.\n", taskList.showList().toString());
        });
    }

    @Test
    public void showList_showTasksOnDate_returnsCorrectString() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneToDo());
            taskList.add(createUndoneDeadlineDifferentDate());
            taskList.add(createUndoneEvent());
            assertEquals("3. " + EVENT_UNDONE_STRING + "\n",
                    taskList.showList(LocalDate.parse("2020-01-01")).toString());
        });
    }

    @Test
    public void showList_noTasksOnDate_returnsCorrectString() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneToDo());
            taskList.add(createUndoneDeadlineDifferentDate());
            taskList.add(createUndoneEvent());
            assertEquals("No tasks found.\n", taskList.showList(LocalDate.parse("2020-02-01")).toString());
        });
    }

    @Test
    public void find_containsKeyword_returnsCorrectString() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneDeadline());
            taskList.add(createUndoneDeadlineDifferentTitle());
            taskList.add(createUndoneEvent());
            assertEquals("1. " + DEADLINE_UNDONE_STRING + "\n3. " + EVENT_UNDONE_STRING + "\n",
                    taskList.find("hello").toString());
        });
    }

    @Test
    public void find_doesNotContainKeyword_returnsCorrectString() {
        TaskList taskList = new TaskList();
        assertDoesNotThrow(() -> {
            taskList.add(createUndoneDeadline());
            taskList.add(createUndoneDeadlineDifferentTitle());
            taskList.add(createUndoneEvent());
            assertEquals("No tasks found.\n", taskList.find("bye").toString());
        });
    }
}
