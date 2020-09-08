package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.exception.DukeCommandException;
import seedu.duke.ui.Ui;
import seedu.duke.task.Event;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class that represents adding an event task.
 */
public class AddEvent extends AddCommand {
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);

    public AddEvent(String[] words) {
        super(words);
    }

    /**
     * Adds the task to the list of current tasks.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     * @throws DateTimeException If the user inputs a wrong format of the date.
     */
    @Override
    public void execute(TaskList ls, Ui ui) throws DateTimeException, DukeCommandException {
        String[] input = words[1].split(" /at ");
        assert input.length > 0 : "Input should not be empty.";
        if (input.length < 2) {
            throw new DukeCommandException("Write something after the command, gee.");
        }
        LocalDateTime day = LocalDateTime.parse(input[1], FORMATTER);
        Event newE = new Event(input[0], day, false);
        ls.add(newE);
        String thing = "Alright then, add more things to your ever-growing list of tasks:\n"
                + newE.getStatus().replaceAll("(?m)^", "\t")
                + "\nNow you have " + ls.size() + " tasks in the list.";
        ui.printResult(thing);
    }
}
