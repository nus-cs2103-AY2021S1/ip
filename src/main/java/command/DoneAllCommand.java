package command;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;


/**
 * DoneAllCommand would execute the program when user specify
 * "done all" as the command. This would automatically set all task to done
 * show message to the user and update the external file.
 */
public class DoneAllCommand extends Command {

    /**
     * Constructs a DoneAllCommand without any
     * argument passed.
     */
    public DoneAllCommand() {
        super();
    }

    /**
     * Prints tasks.
     *
     * @param ui Ui user interface.
     * @param tasks TaskList list of task.
     * @return String Lists of Task.
     */
    private String printTask(Ui ui, TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            boolean isLastTask = i != tasks.size() - 1;
            if (isLastTask) {
                sb.append(ui.formatMessage((i + 1) + ". " + tasks.getTask(i) + "\n"));
            } else {
                sb.append(ui.formatMessage((i + 1) + ". " + tasks.getTask(i)));
            }
        }
        return sb.toString();
    }
    /**
     * Executes parsed user command. The result is:
     * 1. Sets all task in the list to finished via TaskList.
     * 2. Shows the updates list of tasks to the user via UI.
     * 3. Updates the external file via Storage object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException This exception is thrown when the system failed to detect
     * the external file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        for (Task task : tasks.getTasks()) {
            if (!task.isDone()) {
                task.setDone(true);
            }
        }

        String printTask = printTask(ui, tasks);

        storage.updateFile(tasks);

        return ui.getMessageTemplate("Here are the tasks in your list:\n" + printTask);
    }
}
