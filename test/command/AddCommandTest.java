package command;

import misc.DukeDateTime;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    @Test
    void isModifying_true() {
        assertTrue(new AddCommand(null, null).hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new AddCommand(null, null).isExit());
    }

    @Test
    void executeAndUndo() {
        DukeDateTime dateTime = new DukeDateTime(LocalDateTime.now().format(DukeDateTime.FORMAT));
        List<Task> taskList = new ArrayList<>(5);

        Task task1 = new ToDo("Todo 1");
        Task task2 = new Deadline("Deadline 1", dateTime);
        Task task3 = new Event("Event 1", dateTime, dateTime);
        
        Command c1 = new AddCommand(taskList, task1);
        Command c2 = new AddCommand(taskList, task2);
        Command c3 = new AddCommand(taskList, task3);

        c1.execute();
        c2.execute();
        c3.execute();
        assertEquals(3, taskList.size());
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
        assertEquals(task3, taskList.get(2));

        c3.undo();
        c2.undo();
        c1.undo();
        assertEquals(0, taskList.size());

    }
}