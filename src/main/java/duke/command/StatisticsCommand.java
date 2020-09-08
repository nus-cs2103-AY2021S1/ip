package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class StatisticsCommand extends Command {

    public StatisticsCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showStatistics(tasks);
    }
}
