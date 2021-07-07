package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.exception.DukeCommandException;
import seedu.duke.exception.DukeDateTimeException;
import seedu.duke.task.Deadline;
import seedu.duke.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class that represents adding a task with a deadline.
 */
public class AddDeadline extends Command {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);

    public AddDeadline(String[] words) {
        super(words);
    }

    /**
     * Adds the task to the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DukeDateTimeException If the user inputs a wrong format of the date.
     * @throws DukeCommandException If the user did not write anything after the command.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DukeDateTimeException, DukeCommandException {
        try {
            String[] input = words[1].split(" /by ");
            assert input.length > 0 : "Input should not be empty.";

            LocalDateTime day = LocalDateTime.parse(input[1], FORMATTER);
            Deadline newDL = new Deadline(input[0], day, false);
            ls.add(newDL);
            String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                    + newDL.getStatus().replaceAll("(?m)^", "\t")
                    + "\nNow you have " + ls.size() + " tasks in the list.";
            ui.printResult(thing);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCommandException("Write something after the command, gee.");
        } catch (DateTimeException e) {
            throw new DukeDateTimeException("Can't read your date man. Put it like this ok? --> 25 Mar 2020 1930");
        }
    }
}
