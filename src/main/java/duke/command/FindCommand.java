package duke.command;

import java.util.ArrayList;

import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;

/**
 * Find command type.
 * Takes a string and find is there are task description that contains the string.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class FindCommand extends Command {
    private final String fullCommand;

    /**
     * Class constructor.
     * Extracts search value from full command.
     *
     * @param fullCommand full command input by user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Extracts out search value.
     * Searches from the task arraylist.
     * Prints out search result.
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
        StringBuilder resultSb = new StringBuilder(
                String.format("%s\n %s\n", ui.showLine(), "Here are the matching tasks in your list:"));
        if (input.length == 2) {
            toFind = fullCommand.substring(fullCommand.indexOf(" ")).trim();
            ArrayList<Task> results = taskList.find(toFind);
            for (int i = 0; i < results.size(); i++) {
                resultSb.append(String.format("%d. %s%n\n", i + 1, results.get(i)));
            }
            resultSb.append(ui.showLine());
            Ui.printString(resultSb.toString());
        } else {
            throw new DukeException("No input search item!");
        }
    }

    /**
     * Returns indication for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
