package duke.ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void getListString() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("hi"));
        tasks.add(new ToDo("hello"));
        tasks.add(new ToDo("how"));
        assertEquals(
                Ui.getListString(tasks), "1. [T][ ] hi\n" + "2. [T][ ] hello\n" + "3. [T][ ] how\n");
    }

    @Test
    void getListDate() {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("hi", LocalDate.parse("1900-05-10")));
        tasks.add(new Event("hi", LocalDate.parse("1900-05-10")));
        tasks.add(new Deadline("hi", LocalDate.parse("2010-05-10")));

        assertEquals(
                Ui.getListDate(tasks, LocalDate.parse("1900-05-10")),
                "1. [D][ ] hi (by: May 10 1900)\n" + "2. [E][ ] hi (at: May 10 1900)\n");
    }
}
