package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

/** A command to add a Deadline. */
public class DeadlineCommand extends AddTaskCommand {

    /**
     * Constructs a DeadlineCommand.
     *
     * @param input The description of the deadline.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Adds a deadline into the task list.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param input    The description of the deadline.
     * @throws DukeException If there is something wrong with the deadline input.
     */
    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addDeadline(input);
    }

    /**
     * Compares with an object.
     *
     * @param o The object compared.
     * @return True if the object is of type DeadlineCommand and has the same input.
     */
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
