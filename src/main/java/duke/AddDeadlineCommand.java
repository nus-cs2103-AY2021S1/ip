package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Encapsulates a Add Deadline Command.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Instantiates a AddDeadlineCommand.
     * @param parsedCommand the parsed command
     */
    public AddDeadlineCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes command and write to storage.
     * @param tasks the tasklist containing tasks so far
     * @param ui ui to interact with user
     * @param storage storage to read and write to storage file
     * @throws DukeException if parsedCommand does not meet the requirements
     */
    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        // if length is not 2, nothing was passed in after 'deadline'
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Enter a description for your deadline!\n");
        }

        // if description is lacking a /by keyword
        String description = parsedCommand[1];
        if (description.indexOf("/by") < 0) {
            throw new DukeException("Please enter a valid deadline! Remember to add '/by'\n");
        }

        String[] descriptionArray = description.split("/by");
        if (descriptionArray.length != 2) {
            throw new DukeException("NANI??! Enter your deadline name & a date and time(optional) (e.g. 2020-01-30 18:30)!\n");
        }

        String deadlineName = descriptionArray[0];
        String deadlineEndTime = descriptionArray[1];

        // split to date and time
        String[] deadlineEndingArray = deadlineEndTime.trim().split(" ");
        // create a LocalDate object
        String formattedDeadline;
        try {
            LocalDate lt = LocalDate.parse(deadlineEndingArray[0]);
            formattedDeadline = lt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            if (deadlineEndingArray.length == 2) {
                LocalTime t = LocalTime.parse(deadlineEndingArray[1]);
                formattedDeadline += " "
                        + DateTimeFormatter.ofPattern("hh:mm a").format(t);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("NANI??! Enter a valid date and time(optional).\n"
                    + "Date should be: yyyy-mm-dd.\n"
                    + "Time should be HH:mm");
        }

        Task taskToAdd = new Deadline(deadlineName, formattedDeadline);
        addTask(tasks, taskToAdd);
    }

    /**
     * Adds a task to the tasklist.
     * @param tasks the tasklist containing tasks so far
     * @param taskToAdd the task to add to tasklist
     */
    void addTask(TaskList<Task> tasks, Task taskToAdd) {
        tasks.add(taskToAdd);
        System.out.println("Hai! I have added this task to your list:\n"
                + taskToAdd);
        printToDoListSize(tasks);
    }

    /**
     * Prints list size.
     * @param tasks the tasklists
     */
    void printToDoListSize(TaskList<Task> tasks) {
        System.out.println("You now have "
                + tasks.size()
                + " tasks in your list. Gambatte!\n");
    }
}
