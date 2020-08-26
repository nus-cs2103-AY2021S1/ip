package main.java.command;

import main.java.exceptions.InvalidInputException;
import main.java.Storage;
import main.java.tasks.TaskList;
import main.java.Ui;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (tasks.taskListSize() == 0) {
            ui.printMessage("List is empty! Start adding to your task list!");
        } else {
            for (int i = 0; i < tasks.taskListSize(); i++) {
                ui.printMessage((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
