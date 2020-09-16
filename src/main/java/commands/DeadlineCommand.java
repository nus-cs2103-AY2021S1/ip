package commands;

import java.time.LocalDate;

import duke.data.task.Deadline;
import duke.data.task.TaskList;
import duke.storage.Storage;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a deadline with a description and due date.\n"
            + "Example: " + COMMAND_WORD + " CS2103T iP /by 2020-09-17\n";
    private final Deadline deadline;

    public DeadlineCommand(String description, LocalDate localDate) {
        this.description = description;
        deadline = new Deadline(description, localDate);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(deadline);
        storage.save(deadline);
        return new CommandResult("Added: " + deadline);
    }
}
