package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindTaskCommand extends Command {
    protected String keyword;

    public FindTaskCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        String s = ui.printFoundTasksHeader();
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                s += ui.printTask(taskList, i);
            } else {
                continue;
            }
        }
        return s;
    }
}
