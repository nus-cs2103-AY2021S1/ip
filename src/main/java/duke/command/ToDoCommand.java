package duke.command;

import duke.exception.DukeException;
import duke.TaskList;

/** A command to add a @ToDo to the task list. */
public class ToDoCommand extends AddTaskCommand {

    /**
     * Constructs a @ToDoCommand.
     *
     * @param input The description of the @ToDo.
     */
    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Adds a @ToDo to the task list.
     *
     * @param taskList The task list containing all saved tasks.
     * @param input The description of the command.
     * @throws DukeException If the @input is not in a valid format.
     */
    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addToDo(input);
    }

    /**
     * Compares an object.
     *
     * @param o The object compared.
     * @return True if the object is of type @ToDoCommand and has the same @input.
     */
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
