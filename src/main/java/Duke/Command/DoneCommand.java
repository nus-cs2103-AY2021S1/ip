package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * DoneCommand deals with done input.
 */
public class DoneCommand extends Command {
    public String input;

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
