package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDoneInputException;

/**
 * DoneCommand deals with done input.
 */
public class DoneCommand extends Command {
    private String input;

    /**
     * Initiate DoneCommand.
     * @param input User input
     */
    public DoneCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Execute done command.
     * @param tasklist  TaskList for Done to be added
     * @param ui        User interface used
     * @param storage   Storage to update save file
     * @throws InvalidDoneInputException
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws InvalidDoneInputException, IOException {
        try {
            int num = Integer.parseInt(input.replaceAll("\\s+", ""));
            if (num > tasklist.getNumOfTask() || num <= 0) {
                throw new InvalidDoneInputException();
            }
            Task task = tasklist.markDone(num);
            tasklist.updateData(storage);
            ui.showDone(task);
        } catch (NumberFormatException e) {
            throw new InvalidDoneInputException();
        }
    }
}
