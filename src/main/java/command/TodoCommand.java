package main.java.command;

import main.java.*;
import main.java.exceptions.InvalidFileException;
import main.java.exceptions.InvalidInputException;
import main.java.tasks.Task;
import main.java.tasks.TaskList;
import main.java.tasks.Todo;

public class TodoCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:";

    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        Task todoTask = new Todo(super.input.substring(5));
        tasks.addTask(todoTask);
        ui.printMessage(MESSAGE_SUCCESS + todoTask.toString() + "\nNow you have " + tasks.taskListSize() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
