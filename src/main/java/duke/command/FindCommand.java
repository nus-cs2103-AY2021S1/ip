package duke.command;

import duke.logic.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand implements Command {
    private String cmd;

    public FindCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String execute(TaskList tasks) {
        TaskList res = new TaskList();
        String toSearch = cmd.replaceFirst("find\\s+", "");
        for (Task task : tasks.getThingsToDo()) {
            if (task.getDescription().matches(".*?" + toSearch + ".*")) {
                res.add(task);
            }
        }
        // do ui stuff
        return Ui.printFind(toSearch, res.printTaskList());
        // do storage stuff
        // do tasklist stuff
    }
}
