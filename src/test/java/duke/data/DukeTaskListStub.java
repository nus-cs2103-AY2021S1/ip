package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class DukeTaskListStub extends DukeTaskList {

    public DukeTaskListStub() {
        tasks = new ArrayList<>();
        Task task01 = ToDo.createToDo("read book");
        Task task02 = Deadline.createDeadline("return book /by tmr");
        Task task03 = Event.createEvent("have dinner /at 18:00");

        tasks.add(task01);
        tasks.add(task02);
        tasks.add(task03);
    }
}
