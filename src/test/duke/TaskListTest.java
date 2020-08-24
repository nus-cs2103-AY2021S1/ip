package duke;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

class TaskListTest {

    ArrayList<Task> tasks = new ArrayList<>(Arrays.asList( new ToDo("Read"),
            new Deadline("Submit Proposal", "Oct 30 2020 2359"),
            new Event("Book Fair" , "Aug 30 1200-1400")));
    TaskList taskList = new TaskList(tasks);

    @Test
    void getListOfTasks() {
        assertEquals(new ArrayList<>(Arrays.asList( new ToDo("Read"),
                new Deadline("Submit Proposal", "Oct 30 2020 2359"),
                new Event("Book Fair" , "Aug 30 1200-1400"))).toString(), taskList.getListOfTasks().toString());
    }

    @Test
    void get() {
        assertEquals(new Deadline("Submit Proposal", "Oct 30 2020 2359").toString(), taskList.get(1).toString());
    }

    @Test
    void remove() {
        assertEquals(new Event("Book Fair" , "Aug 30 1200-1400").toString(), taskList.get(2).toString());
    }

    @Test
    void size() {
        assertEquals(3, taskList.size());
    }

    @Test
    void add() {
        tasks.add(new ToDo("Meditate"));
        taskList.add(new ToDo("Meditate"));
        assertEquals(tasks.toString(), taskList.getListOfTasks().toString());
    }
}