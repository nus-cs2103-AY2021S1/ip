package duke.command;

import java.util.LinkedList;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The command to list all task of a designated task list.
 */
public class CommandList implements Command {
    private String message = "";
    /**
     * Execute the command to list all tasks of a given <code>TaskList</code> object.
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> to handle the interface updates
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks.getList());

        LinkedList<Task> list = tasks.getList();
        if (list.size() == 0) {
            message += "You have no tasks in your list now!\nType todo, event or deadline to add some!\n";
        } else {
            message += "Here are the tasks in your list:\n";
            for (int i = 1; i <= list.size(); i++) {
                message += i + "." + list.get(i - 1);
                if (i != list.size()) message += "\n";
            }
        }
    }

    /**
     * Return <code>false</code> since the command is not exit.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }

    public String getMessage() {
        return message;
    }
}
