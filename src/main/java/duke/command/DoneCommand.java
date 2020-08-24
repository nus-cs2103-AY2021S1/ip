package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command when user marks a task as completed.
 */
public class DoneCommand extends Command {
    private final String userInput;

    public DoneCommand(String userInput) {
        this.userInput = userInput;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String taskIndex = userInput.substring(5);
            int index = Integer.valueOf(taskIndex) - 1; // taskIndex started from 1
            Task completedTask = taskList.get(index);
            completedTask.markAsDone();
            storage.updateTask(completedTask,index);
            ui.print("Nice! I've marked this task as done:\n" +
                    completedTask.toString());


        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to mark as done.");
        }
    }
}
