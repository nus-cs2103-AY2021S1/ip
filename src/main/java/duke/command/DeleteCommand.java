package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeleteWrongFormatException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    private String[] splitCommand;

    public DeleteCommand(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteWrongFormatException,
            TaskIndexOutOfBoundsException {
        try {
            if (splitCommand.length != 2) { // If command is in a wrong format
                throw new DeleteWrongFormatException();
            }
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1; // Index of task in the task list
            Task removedTask = tasks.removeTask(taskIndex);
            ui.showReplyForDeleteTask(removedTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) { // Second argument of
            // command was not a number, e.g. "delete X"
            throw new DeleteWrongFormatException();
        } catch (IndexOutOfBoundsException e) { // User requests
            // for a task with an index not within the current
            // task list
            throw new TaskIndexOutOfBoundsException(splitCommand[1]);
        }
    }
}
