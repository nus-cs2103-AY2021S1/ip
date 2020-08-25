package duke.command;

import duke.exception.DukeException;
import duke.TaskList;

public class ToDoCommand extends AddTaskCommand {

    public ToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addToDo(input);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ToDoCommand) {
            ToDoCommand t = (ToDoCommand) o;
            return t.input.equals(this.input);
        } else {
            return false;
        }
    }
}
