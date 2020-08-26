package duke.command;

import duke.Bot;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        // TODO: reduce list related repetition
        if (list.size() == 0) {
            bot.sayLine("There are no items in your list.");
        } else {
            List<Task> matches = IntStream
                    .range(0, list.size())
                    .mapToObj(list::get)
                    .filter(t -> t.getDescription().contains(query))
                    .collect(Collectors.toList());

            if (matches.isEmpty()) {
                bot.sayLine("No matching tasks found!");
            } else {
                bot.sayLine("Here are the matching tasks in your list:");
                for (int i = 0; i < matches.size(); i++) {
                    bot.sayLine((i + 1) + ". " + matches.get(i).displayString());
                }
            }
        }
    }
}
