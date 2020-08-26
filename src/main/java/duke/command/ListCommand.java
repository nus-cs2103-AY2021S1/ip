package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that shows the task in the task list.
 */
public class ListCommand extends UserCommand{

    /**
     * @param userInput user's input.
     */
    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.printList(taskList);
    }
}
