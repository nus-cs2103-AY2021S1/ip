package main.java.command;

import main.java.*;
import main.java.exceptions.InvalidFileException;
import main.java.exceptions.InvalidInputException;
import main.java.tasks.Deadline;
import main.java.tasks.Task;
import main.java.tasks.TaskList;

public class DeadlineCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";

    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
        String[] split = super.input.substring(9).split("/by ", 2);
        Task deadline = new Deadline(split[0], split[1]);
        tasks.addTask(deadline);
        ui.printMessage(MESSAGE_SUCCESS + deadline.toString() + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}