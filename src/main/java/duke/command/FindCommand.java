package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    String cmd;

    public FindCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList tasks) {
        TaskList res = new TaskList();
        String toSearch = cmd.replaceFirst("find\\s+", "");
        for (Task task : tasks.thingsToDo) {
            if (task.getDescription().matches(".*?" + toSearch + ".*")) {
                res.add(task);
            }
        }
        // do ui stuff
        Ui.printList(res.printTodoList());
        // do storage stuff
        // do tasklist stuff
    }
}
