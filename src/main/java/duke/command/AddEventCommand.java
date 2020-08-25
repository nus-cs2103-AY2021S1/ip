package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EventWrongFormatException;
import duke.exception.WrongFormatException;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;

/**
 * Encapsulates an add command for event tasks. This command adds an event task to the task list. The format for this
 * command is: "event task /at venue".
 */
public class AddEventCommand extends AddCommand {

    /** The entire command entered by the user */
    private String fullCommand;

    /**
     * Creates and initializes an AddEventCommand object.
     *
     * @param fullCommand The entire command entered by the user.
     */
    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command. If successful, it will add an event task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws EventWrongFormatException If the add event command is in a wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EventWrongFormatException {
        try {
            String[] splitLineIntoTwo = fullCommand.split("/at");
            Task newTask = new Event(splitLineIntoTwo[0]
                    .substring(6).trim(),
                    splitLineIntoTwo[1].trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e) { // add event command is in a wrong format
            throw new EventWrongFormatException();
        }
    }
}