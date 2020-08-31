package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeFormatException;
import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.Event;
import tasks.TaskList;

/**
 * Adds the event entry that the user input to the
 * Arraylist of logic.Duke.
 */
public class EventCommand extends Command {
    
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public EventCommand(String input) {
        super(input);
    }

    /**
     * Add event entry to the Arraylist.
     * @param tasks List of tasks given.
     * @param ui Handles the output to print.
     * @param storage Writes the save file.
     * @throws InvalidDateTimeFormatException If input does not follow format specified.
     * @throws InvalidInputException If the input for the delete is incorrect.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateTimeFormatException,
            InvalidInputException, InvalidSaveFileException {

        final int INPUT_INDEX = 6;
        final int EVENT_INDEX = 3;
        //Check if event is specified
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] splitWord = super.input.split("/");
        String desc = splitWord[0].substring(INPUT_INDEX, splitWord[0].length() - 1);
        String timing = splitWord[1].substring(EVENT_INDEX);
        Event task;
        try {
            task = new Event(desc, LocalDateTime.parse(timing, dateTimeFormat));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException(
                    "\tEvent timing input must follow a certain format: yyyy-mm-dd HH:mm "
                    + "e.g. 2020-08-23 16:45");
        }
        tasks.addTask(task);
        storage.saveFile(tasks.getTasks());
        return ui.printOutput("\tGot it. I've added this task:\n" + "\t" + task.toString()
                + "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
