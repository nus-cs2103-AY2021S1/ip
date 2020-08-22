package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyDeadlineException;
import task.Deadline;
import task.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is adding a <code>Deadline</code> to the <code>TaskList</code>.
 * A <code>AddDeadlineCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Adds the <code>Deadline</code> to <code>tasks</code> and save the <code>Deadline</code> to
     * <code>storage</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException If failed to save to <code>storage</code>, no description provided in
     * <code>splitCommand</code>, or invalid date and time format (the date and time are
     * located inside the command argument).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String argument = splitCommand[1];
            String description = argument.split(" /by ", 2)[0];
            String deadline = argument.split(" /by ", 2)[1];
            Task toAdd = new Deadline(description, LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
            tasks.add(toAdd);
            ui.sayAddedTask(toAdd, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            ui.sayException(e);
        } catch (IndexOutOfBoundsException e) { // No description
            throw new EmptyDeadlineException();
        } catch (DateTimeParseException e) {
            ui.say("The date and time format must be: [dd/MM/yyyy HHmm]\nFor example, 02/12/2019 1800");
        }
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof AddDeadlineCommand) {
            AddDeadlineCommand other = (AddDeadlineCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
