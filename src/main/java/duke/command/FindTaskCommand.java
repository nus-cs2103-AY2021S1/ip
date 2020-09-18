package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.NoMatchFoundException;
import duke.task.Task;

public class FindTaskCommand extends Command {
    protected String keyword;

    public FindTaskCommand(String keyword) {
        super(CommandType.FINDTASK);
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws NoMatchFoundException {
        boolean hasMatch = false;
        String s = ui.printPrompt("These are the tasks that match the keyword:\n");
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                s += ui.printTask(taskList, i);
                hasMatch = true;
            } else {
                continue;
            }
        }
        if (!hasMatch) {
            throw new NoMatchFoundException();
        }
        return s;
    }
}
