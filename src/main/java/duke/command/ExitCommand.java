package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.TaskList;

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