package duke.command;

import duke.exception.DukeException;
import duke.TaskList;

public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addDeadline(input);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DeadlineCommand) {
            DeadlineCommand t = (DeadlineCommand) o;
            return t.input.equals(this.input);
        } else {
            return false;
        }
    }
}
