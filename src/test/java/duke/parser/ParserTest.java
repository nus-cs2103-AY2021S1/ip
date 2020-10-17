package duke.parser;

import java.util.ArrayList;

import duke.storage.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class ParserTest {
    protected TaskList lines = new TaskList(new ArrayList<>());

    protected void setLines() {
        lines.addTask(new Todo("Homework").toString());
        lines.addTask(new Deadline("Project", "2020-09-09").toString());
        lines.addTask(new Event("Meeting", "2020-10-10").toString());
    }

    protected void resetLines() {
        lines = new TaskList(new ArrayList<>());
    }
}
