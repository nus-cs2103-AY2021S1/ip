package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class ReminderCommand extends Command {
    /**
     * Creates an ReminderCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public ReminderCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return setReminder(inputArr[1], tasks);
    }

    /**
     * Remind the user about upcoming deadlines.
     *
     * @param command Representing the target task and its reminder option.
     * @param tasks Object contains the task list.
     * @return A string message confirm the task to be reminded
     */
    public String setReminder(String command, TaskList tasks) {
        String[] result = command.split(" ");
        Task task = tasks.get(Integer.parseInt(result[0]) - 1);
        return task.setReminder(result[1].equals("y"));
    }

}
