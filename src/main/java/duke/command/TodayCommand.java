package duke.command;

import duke.task.TaskList;

public class TodayCommand extends Command {
    @Override
    public void execute(TaskList list) {
        list.printTasksToday();
    }
}
