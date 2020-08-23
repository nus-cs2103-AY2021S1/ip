package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeadlineWrongFormatException;
import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;
import java.time.DateTimeException;

public class AddDeadlineCommand extends AddCommand {

    private String fullCommand;

    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeadlineWrongFormatException {
        try {
            String[] splitLineIntoTwo = fullCommand.split("/by");
            Task newTask = new Deadline(splitLineIntoTwo[0]
                    .substring(9).trim(),
                    splitLineIntoTwo[1].trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException | DateTimeException e)
        { // duke.command.Command is in a wrong format
            throw new DeadlineWrongFormatException();
        }
    }
}