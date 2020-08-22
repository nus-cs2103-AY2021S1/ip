package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class AddCommand extends Command {

    private Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(toAdd);
        ui.show(String.format(
                "\t Got it. I've added this task:\n\t\t%s\n\t %s",
                toAdd.toString(),
                taskList.tasksRemaining()
        ));
    }

}
