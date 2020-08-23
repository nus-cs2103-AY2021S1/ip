package duke;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList list = new TaskList();
        Task task;
        String listString = "";
        int size = 0;

        // test 0
        assertEquals("", list.toString());

        // test 1
        try {
            task = new TodoStub("abc");
            listString = "1." + task.toString();
            list.addTask("todo", "abc");
            size++;
            assertEquals(listString, list.toString());
        } catch (DukeException e) {
            fail();
        }

        // test 2
        try {
            task = new EventStub("today/at8-09-2020 8:00");
            list.addTask("event", "today/at8-09-2020 8:00");
            listString += '\n' + "2." + task.toString();
            size++;
            assertEquals(listString, list.toString());
        } catch (DukeException e) {
            fail();
        }


        // test 3
        for (int i = 9; i < 24; i++) {
            try {
                task = new DeadlineStub("lock/by9-10-2021 " + i + ":00");
                list.addTask("deadline", "lock/by9-10-2021 " + i + ":00");
                size++;
                listString += "\n" + size + "." + task.toString();
            } catch (DukeException e) {
                fail();
            }
        }
        assertEquals(listString, list.toString());

        // test 4
        for (int i = 1; i < 10; i++) {
            try {
                task = new EventStub("test/at9-10-2021 " + i + ":00");
                size++;
                list.addTask("event", "test/at9-10-2021 " + i + ":00");
                listString += "\n" + size + "." + task.toString();
            } catch (DukeException e) {
                fail();
            }
        }
        assertEquals(listString, list.toString());

        // test 5
        try {
            list.addTask("event","today8-09-2020 8:00");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, when is the event? (/at...)", e.getMessage());
        } 
    }


    public void getTimedTasksTest() {
        TaskList list = new TaskList();
        Task task;
        String listString;

        // test 0
        // start condition: duke.txt empty
        try {
            list.getTimedTasks(LocalDate.parse("9-12-2020", DateTimeFormatter.ofPattern("d-M-yyyy k:mm")));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, no such task :<", e.getMessage());
        }

        // test 2
        try {
            task = new EventStub("today/at8-09-2020 8:00");
            list.addTask("event", "today/at8-09-2020 8:00");
            listString = "1." + task.toString();
            assertEquals(listString, list.getTimedTasks(LocalDate.parse(("8-09-2020"), DateTimeFormatter.ofPattern("d-M-yyyy k:mm"))).toString());
        } catch (DukeException e) {
            fail();
        }

        // test 3
        listString = "";
        for (int i = 9; i < 24; i++) {
            try {
                task = new DeadlineStub("lock/by9-10-2021 " + i + ":00");
                list.addTask("deadline", "lock/by9-10-2021 " + i + ":00");
                int listIndex = i - 8;
                listString += listIndex + "." + task.toString();
                if (i < 23) {
                    listString += "\n";
                }
                assertEquals(listString, list.getTimedTasks(LocalDate.parse("9-10-2021", DateTimeFormatter.ofPattern("d-M-yyyy k:mm"))).toString());
            } catch (DukeException e) {
                fail();
            }
        }
    }

}
