package main.java.command;

import main.java.*;
import main.java.exceptions.InvalidFileException;
import main.java.exceptions.InvalidInputException;
import main.java.tasks.TaskList;

public class ExitCommand extends Command {

    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting...";

    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidFileException {
        ui.showExitMessage();
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}