package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DoneWrongFormatException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command {

    private String[] commandParts;

    public DoneCommand(String[] commandParts) {
        this.commandParts = commandParts;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneWrongFormatException,
            TaskIndexOutOfBoundsException {
        try {
            if (commandParts.length != 2) { // If command is in a wrong format
                throw new DoneWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandParts[1]) - 1; // Index of task in the task list
            Task completedTask = tasks.getTask(taskIndex);
            completedTask.setDone(true);
            ui.showReplyForDoneTask(completedTask);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "done X"
            throw new DoneWrongFormatException();
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(commandParts[1]);
        }
    }
}
