package duke.command;

import duke.Statistics;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class StatisticsCommand extends Command {

    public StatisticsCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Statistics statistics = new Statistics(tasks);
        return ui.showStatistics(tasks, statistics);
    }
}
