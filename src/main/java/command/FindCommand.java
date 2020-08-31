package command;

import java.util.List;

import exception.InvalidInputException;
import logic.Storage;
import logic.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents the command to find a certain task that
 * the user has input.
 */
public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {

        final int INPUT_INDEX = 5;

        //Check if description of task to find is non-empty.
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("\tDescription of item to find cannot be empty! Please try again!");
        }
        List<Task> foundTasks = tasks.findTasks(super.input.substring(INPUT_INDEX));
        if (foundTasks.size() == 0) {
            ui.printOutput("\tThere is no item that matches your description!");
        } else {
            ui.printOutput("\tHere are the matching tasks in your list:");
            for (int x = 0; x < foundTasks.size(); x++) {
                ui.printOutput("\t" + (x + 1) + "." + foundTasks.get(x).toString());
            }
        }
    }
}
