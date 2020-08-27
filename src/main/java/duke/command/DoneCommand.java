package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents command that is specific to the done command.
 */
public class DoneCommand extends Command {

    private String input;

    public DoneCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    /**
     * Executes done command based on user input.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If invalid input.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (Parser.isValidIndex(input, taskList.getListSize())) {
            Task task = taskList.getList().get(Parser.getIndex(input));
            task.markAsDone();
            ui.printDoneMessage(task);
            storage.saveListToFile(taskList.getList());
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }

}
