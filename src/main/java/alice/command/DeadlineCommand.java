package alice.command;

import java.time.LocalDateTime;
import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.DeadlineCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Deadline;
import alice.task.Task;
import alice.task.TaskList;
import alice.util.Parser;

/**
 * Represents the command to add a new deadline in ALICE.
 */
public class DeadlineCommand implements Command {
    protected static final List<String> NAMES = List.of("deadline");
    protected static final String DESCRIPTION = "Create a task with deadline";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES)
            + "] <desc> /by <datetime>";

    private final String description;
    private final LocalDateTime by;

    /**
     * Creates a new command to create a new {@code Deadline} with the details provided.
     *
     * @param description the description of the deadline.
     * @param by          the due datetime of the deadline.
     */
    private DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Checks if the command word triggers the {@code DeadlineCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code DeadlineCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new command to create a new {@code Deadline} with the details given by the user.
     *
     * @param argument the deadline details input given by user.
     * @return the {@code DeadlineCommand} with the indicated details.
     * @throws InvalidCommandException if the user gives an invalid description and/or datetime.
     */
    public static DeadlineCommand createCommand(String argument) throws InvalidCommandException {
        String[] arguments = argument.split(" /by ", 2);
        if (arguments.length == 2 && !arguments[1].isBlank()) {
            String description = arguments[0];
            String dateTime = arguments[1];
            LocalDateTime deadlineDt = Parser.parseDateTime(dateTime);
            return new DeadlineCommand(description, deadlineDt);
        } else if (argument.isBlank()) {
            // Empty description
            throw new InvalidCommandException("The deadline description cannot be left empty.");
        } else if (argument.endsWith("/by")) {
            // Empty date
            throw new InvalidCommandException("You cannot create an deadline without the date.");
        } else {
            // No /by marker
            throw new InvalidCommandException("I can't find the deadline date.\n"
                    + "Did you forget to add '/by'?");
        }
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        String reply = "Roger. I've added the deadline to your list:\n    "
                + deadline + "\nNow you have " + tasks.getNumberOfTasks()
                + " tasks in your list!";
        try {
            storageFile.saveToLastLine(deadline.encode());
            return new DeadlineCommandResult(reply, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new DeadlineCommandResult(reply, true, SaveStatus.SAVE_FAILED);
        }
    }
}
