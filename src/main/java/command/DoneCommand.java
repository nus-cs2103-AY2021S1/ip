package main.java.command;

import main.java.*;
import main.java.exceptions.InvalidFileException;
import main.java.exceptions.InvalidInputException;
import main.java.tasks.Task;
import main.java.tasks.TaskList;

public class DoneCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n";


    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 4) {
            throw new InvalidInputException("☹ OOPS!!! Please choose a task to be completed.");
        }
        int index = Integer.parseInt(super.input.substring(5)) - 1;
        Task taskDone = tasks.getTask(index);
        tasks.markAsDone(index);
        ui.printMessage(MESSAGE_SUCCESS + taskDone);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
