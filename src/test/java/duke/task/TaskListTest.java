package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
    }

    @Test
    void size_intial_returnZero() {
        assertEquals(taskList.size(), 0);
    }

    @Test
    void size_afterAddingOneItem_returnOne() {
        taskList.add(new Task("some", TaskType.TODO));
        assertEquals(taskList.size(), 1);
    }

    @Test
    void testAddAndRemove_validIndex_returnCorrectTasks() throws DukeException {
        Task task1 = new Task("some", TaskType.TODO);
        Task task2 = new Task("some", TaskType.TODO);
        Task task3 = new Task("some", TaskType.TODO);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        assertEquals(taskList.remove(1), task1);
        assertNotEquals(taskList.remove(1), task1);
        assertEquals(taskList.remove(1), task3);
    }

    @Test
    void testAddAndRemove_index0_throwsException() {
        Task task = new Task("some", TaskType.TODO);
        taskList.add(task);
        assertThrows(DukeException.class, () ->
                taskList.remove(0)
        );
    }

    @Test
    void testAddAndRemove_indexOutOfRange_throwsException() {
        Task task = new Task("some", TaskType.TODO);
        taskList.add(task);
        assertThrows(DukeException.class, () ->
                taskList.remove(100)
        );
    }

    @Test
    void get_afterAddTasks_returnCorrectTasks() throws DukeException {
        Task task1 = new Task("some", TaskType.TODO);
        Task task2 = new Task("some", TaskType.TODO);
        Task task3 = new Task("some", TaskType.TODO);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        assertEquals(taskList.get(1), task1);
        assertNotEquals(taskList.get(1), task2);
        assertEquals(taskList.get(3), task3);
    }

    @Test
    void iterator_afterAddTasks_returnTasksInCorrectOrder() throws DukeException {
        Task task1 = new Task("some", TaskType.TODO);
        Task task2 = new Task("some", TaskType.TODO);
        Task task3 = new Task("some", TaskType.TODO);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        int i = 1;
        for (Task task : taskList) {
            if (i == 1) {
                assertEquals(taskList.get(i), task1);
            } else if (i == 2) {
                assertEquals(taskList.get(i), task2);
            } else if (i == 3) {
                assertEquals(taskList.get(i), task3);
            }
            i++;
        }
    }
}