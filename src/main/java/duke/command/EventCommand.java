package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

public class EventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";

    public EventCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        String[] split = super.input.substring(6).split("/at ", 2);
        Task event = new Event(split[0], split[1]);
        tasks.addTask(event);
        ui.printMessage(MESSAGE_SUCCESS + event.toString() + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");

        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
