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

/**
 * Encapsulates an add command for deadline tasks. This command adds a deadline task to the task list. The format for
 * this command is: "deadline task /by YYYY-MM-DD hhmm" where hh is hours and mm is minutes.
 */
public class AddDeadlineCommand extends AddCommand {

    /** The entire command entered by the user */
    private String fullCommand;

    /**
     * Creates and initializes an AddDeadlineCommand object.
     *
     * @param fullCommand The entire command entered by the user.
     */
    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command. If successful, it will add a deadline task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws DeadlineWrongFormatException If the add deadline command is in a wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeadlineWrongFormatException {
        try {
            String[] commandParts = fullCommand.split("/by");
            Task newTask = new Deadline(commandParts[0].substring(9).trim(), commandParts[1].trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException | DateTimeException | NumberFormatException e) {
            // add deadline command is in a wrong format
            throw new DeadlineWrongFormatException();
        }
    }
}