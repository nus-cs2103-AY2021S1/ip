package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class EventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";
    public static final String MESSAGE_PARSE_ERROR = "Invalid date and time format.\n" +
            "Please enter date and time in the format: yyyy-MM-dd HH:mm";

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException
                    ("☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        try {
            String[] split = super.input.substring(6).split("/at ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(split[1], formatter);
            Task event = new Event(split[0], date);
            tasks.addTask(event);
            ui.printMessage(MESSAGE_SUCCESS + event.toString() + "\nNow you have "
                    + tasks.taskListSize() + " tasks in the list.");
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
