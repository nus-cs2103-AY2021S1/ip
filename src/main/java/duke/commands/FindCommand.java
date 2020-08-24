package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/** Represents the command that displays all tasks that match the user's search word
 * when executed.
 */
public class FindCommand extends Command {

    private String searchWord;

    /** Constructs a FindCommand with the specified search word.
     *
     * @param searchWord The search word that the user input.
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /** Displays all tasks whose description contains the user's search word.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        ArrayList<Task> allTasks = taskList.getTasks();
        for (Task task : allTasks) {
            if (task.getDescription().contains(searchWord)) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.size() == 0) {
            ui.show("\t There are no tasks that matches your search word.");
        } else {
            String message = "\t Here are the matching tasks in your list:\n";
            ui.show(ListCommand.tasksToString(matchedTasks, message));
        }
    }
}
