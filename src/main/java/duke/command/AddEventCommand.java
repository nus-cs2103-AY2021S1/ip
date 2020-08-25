package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EventWrongFormatException;
import duke.exception.WrongFormatException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

public class AddEventCommand extends AddCommand {

    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EventWrongFormatException {
        try {
            String[] commandParts = fullCommand.split("/at");
            Task newTask = new Event(commandParts[0].substring(6).trim(), commandParts[1].trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e) {
            // duke.command.Command is in a wrong format
            throw new EventWrongFormatException();
        }
    }
}