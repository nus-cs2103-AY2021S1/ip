package duke.tasks;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline command. This command handles the deadline input
 * from users.
 */
public class DeadlineCommand extends Command {
    protected String deadline;

    /**
     * Constructor that stores the deadline string.
     * @param deadline input from user.
     */
    public DeadlineCommand(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        try {
            //add event task to list of tasks
            tasks.deadline(this.deadline);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return Deadline.invalidInput();
        }

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);

        //Return String to print
        return tasks.deadlineString(this.deadline);
    }
}
