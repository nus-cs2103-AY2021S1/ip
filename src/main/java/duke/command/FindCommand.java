package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Find command type.
 * Take a string and find is there are task description that contains the string.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class FindCommand extends Command {
    private final String fullCommand;

    /**
     * Class constructor.
     * Extract search value from full command.
     *
     * @param fullCommand full command input by user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Extract out search value.
     * Search from the task arraylist.
     * Print out search result.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException Exception for wrong date or time format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        String[] input = fullCommand.split(" ");
        String toFind;
        if (input.length == 2) {
            toFind = fullCommand.substring(fullCommand.indexOf(" ")).trim();
            ArrayList<Task> results = taskList.find(toFind);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < results.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, results.get(i));
            }
            ui.showLine();
        } else {
            throw new DukeException("No input search item!");
        }
    }

    /**
     * Indicator for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
