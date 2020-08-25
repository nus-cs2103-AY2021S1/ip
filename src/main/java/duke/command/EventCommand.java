package duke.command;

import duke.exception.DukeException;
import duke.TaskList;

public class EventCommand extends AddTaskCommand {
    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addEvent(input);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof EventCommand) {
            EventCommand t = (EventCommand) o;
            return t.input.equals(this.input);
        } else {
            return false;
        }
    }
}
