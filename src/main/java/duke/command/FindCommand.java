package duke.command;

import duke.Bot;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This Command will cause Duke to find and display the Tasks whose description contains the specified
 * String.
 */
public class FindCommand implements Command {
    private String query;

    /**
     * Creates a FindCommand.
     *
     * @param query the String query to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Bot bot, TaskList list) {
        if (list.size() == 0) {
            bot.sayLine("There are no items in your list.");
        } else {
            List<Task> matches = list.toStream()
                    .filter(t -> t.getDescription().contains(query))
                    .collect(Collectors.toList());

            if (matches.isEmpty()) {
                bot.sayLine("No matching tasks found!");
            } else {
                bot.sayLine("Here are the matching tasks in your list:");
                bot.sayLine(Helper.tasksToDisplayListString(matches));
            }
        }
    }
}
