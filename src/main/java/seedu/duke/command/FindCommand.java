package seedu.duke.command;

import seedu.duke.exception.DukeNotSureException;
import seedu.duke.TaskList;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Class that represents the command to find a task.
 */
public class FindCommand extends Command {
    public FindCommand(String[] words) {
        super(words);
    }

    /**
     * Find the tasks that match the given keyword.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @exception DukeNotSureException If the user does not type anything after the command.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeNotSureException {
        try {
            String keyword = words[1];
            ArrayList<Task> matches = new ArrayList<>();
            for (Task task : ls.getList()) {
                if (task.getName().contains(keyword)) {
                    matches.add(task);
                }
            }

            ui.printResult("Alright here's what I can find:");

            if (matches.size() == 0) {
                ui.printResult("Nothing, like your conscience.");
            }

            for (Task task : matches) {
                ui.printResult(((ls.indexOf(task) + 1) + ". " + task.getStatus()));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNotSureException("Type something to find, will you???");
        }
    }

}
