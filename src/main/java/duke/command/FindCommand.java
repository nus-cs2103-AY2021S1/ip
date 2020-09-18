package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This Command will cause Duke to find and display the Tasks whose description contains the specified String.
 */
public class FindCommand implements Command {
    private String query;

    /**
     * Creates a FindCommand.
     *
     * @param query String query to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        String message = "";
        if (list.size() == 0) {
            message = "There are no items in your list.";
        } else {
            List<Task> matches =
                    list.toStream().filter(t -> t.getDescription().contains(query)).collect(Collectors.toList());

            if (matches.isEmpty()) {
                message = "No matching tasks found!";
            } else {
                message = "Here are the matching tasks in your list:\n" + Helper.tasksToDisplayListString(matches);
            }
        }
        assert !message.isBlank();
        ui.say(message);
    }
}
