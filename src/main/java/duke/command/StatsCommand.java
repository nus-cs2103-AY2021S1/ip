package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a stats command.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";
    @Override
    public String execute(TaskList tasks, Storage storage, Statistics stats) {
        return stats.getStats();
    }
}
