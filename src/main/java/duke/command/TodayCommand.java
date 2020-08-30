package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class TodayCommand extends Command {
    @Override
    public void execute(TaskList list, Storage storage) {
        Ui.todayMessage(list.getTaskList());
    }
}
