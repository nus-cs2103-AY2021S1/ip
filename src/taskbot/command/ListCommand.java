package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.listTasks();
    }

    @Override
    public boolean equals(Object obj) {
        //Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /*Check if obj is an instance of this class.
          All ListCommand instances are equal.
         */
        return obj instanceof ListCommand;
    }
}
