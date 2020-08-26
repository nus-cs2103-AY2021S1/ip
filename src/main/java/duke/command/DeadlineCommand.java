package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline Command class to execute command that adds a deadline to
 * the TaskList.
 */
public class DeadlineCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";
    public static final String MESSAGE_PARSE_ERROR = "Invalid date and time format.\n" +
            "Please enter date and time in the format: yyyy-MM-dd HH:mm";

    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Execute a Deadline Command to add a Deadline into TaskList.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException rejects empty deadline or incorrect date and time format.
     * @throws InvalidFileException throws an error when method fails to save.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 8) {
            throw new InvalidInputException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
        try {
            String[] split = super.input.substring(9).split("/by ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(split[1], formatter);
            Task deadline = new Deadline(split[0], date);
            tasks.addTask(deadline);
            ui.printMessage(MESSAGE_SUCCESS + deadline.toString() + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");
            storage.save(tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_PARSE_ERROR);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}