package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDoneCommandException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;

/**
 * Represents a command that mark a task as done.
 */
public class DoneCommand extends UserCommand {

    /**
     * @param userInput user's input.
     */
    public DoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTasks();
        String[] doneCommandArray = userInput.split(" ");
        if (doneCommandArray.length < 2) {
            throw new InvalidDoneCommandException();
        } else {
            int itemToBeMarkedAsDone = Integer.parseInt(doneCommandArray[1]);
            if (itemToBeMarkedAsDone > ls.size() || itemToBeMarkedAsDone <= 0) {
                throw new InvalidDoneCommandException();
            } else {
                ls.get(itemToBeMarkedAsDone - 1).markAsDone();
//                ArrayListToTextConverter.convertArrayListToText(ls);
            }
        }
    }
}
