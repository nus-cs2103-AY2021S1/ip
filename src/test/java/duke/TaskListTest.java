package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void successAddToList() {
        LocalDate testDate = LocalDate.now();
        Task task1 = new Task("Task1");
        Task task2 = new Event("Birthday", testDate);
        Task task3 = new Deadline("Assignment", testDate);
        Task task4 = new ToDo("Study for Midterms");
        TaskList testList = new TaskList();
        testList.add(task1);
        testList.add(task2);
        testList.add(task3);
        testList.add(task4);
        assertEquals(testList.size(), 4);
    }

    @Test
    public void successDeleteFromListByTask() {
        LocalDate testDate = LocalDate.now();
        Task task1 = new Task("Task1");
        Task task2 = new Event("Birthday", testDate);
        Task task3 = new Deadline("Assignment", testDate);
        Task task4 = new ToDo("Study for Midterms");
        TaskList testList = new TaskList();
        testList.add(task1);
        testList.add(task2);
        testList.add(task3);
        testList.add(task4);
        testList.remove(task3);
        testList.remove(task1);
        assertEquals(testList.size(), 2);
        testList.remove(task2);
        assertEquals(testList.size(), 1);
        testList.remove(task4);
        assertEquals(testList.size(), 0);
    }

    @Test
    public void successDeleteFromListByIndex() {
        LocalDate testDate = LocalDate.now();
        Task task1 = new Task("Task1");
        Task task2 = new Event("Birthday", testDate);
        Task task3 = new Deadline("Assignment", testDate);
        Task task4 = new ToDo("Study for Midterms");
        TaskList testList = new TaskList();
        testList.add(task1);
        testList.add(task2);
        testList.add(task3);
        testList.add(task4);
        testList.remove(3);
        testList.remove(2);
        assertEquals(testList.size(), 2);
        testList.remove(1);
        assertEquals(testList.size(), 1);
        testList.remove(0);
        assertEquals(testList.size(), 0);
    }

    @Test
    public void successRetrieveFromList() {
        LocalDate testDate = LocalDate.now();
        Task task1 = new Task("Task1");
        Task task2 = new Event("Birthday", testDate);
        Task task3 = new Deadline("Assignment", testDate);
        Task task4 = new ToDo("Study for Midterms");
        TaskList testList = new TaskList();
        testList.add(task1);
        testList.add(task2);
        testList.add(task3);
        testList.add(task4);
        assertEquals(testList.get(0), task1);
        assertEquals(testList.get(1), task2);
        assertEquals(testList.get(2), task3);
        assertEquals(testList.get(3), task4);

    }
}
