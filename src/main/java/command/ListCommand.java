package command;

import exception.InvalidInputException;
import logic.Storage;
import logic.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * List all tasks that logic.Duke currently has.
 */
public class ListCommand extends Command {
    
    public ListCommand(String input) {
        super(input);
    }
    /**
     * List out all tasks that logic.Duke is currently handling.
     * @param tasks List of all tasks logic.Duke has.
     * @param ui Handles the output that the user sees.
     * @param storage Writing of the save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (tasks.getTasks().size() == 0) {
            throw new InvalidInputException("List is empty! Start adding some tasks");
        } else {
            String result = "Here are the tasks in your list:";
            for (int i = 1; i <= tasks.getTasks().size(); i++) {
                Task current = tasks.getTasks().get(i - 1);
                result += ui.printOutput("\n" + i + "." + current.toString());
            }
            return result;
        }
    }
}
