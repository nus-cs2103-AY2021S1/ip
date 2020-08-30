package seedu.duke;

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
     */
    @Override
    public void execute(TaskList ls, Ui ui) {
        String keyword = words[1];
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : ls.getList()) {
            if (task.getName().contains(keyword)) {
                matches.add(task);
            }
        }

        ui.printResult("Here are the matching tasks in your list:");

        for (Task task : matches) {
            ui.printResult(((ls.indexOf(task) + 1) + ". " + task.getStatus()));
        }
    }

}
