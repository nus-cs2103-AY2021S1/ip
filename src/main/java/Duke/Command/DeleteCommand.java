package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * DeleteCommand deals with delete input.
 */
public class DeleteCommand extends Command {
    public String input;

    /**
     * Initiate DeleteCommand.
     * @param input  User input
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }

    /**
     * Execute delete command.
     * @param tasklist TaskList for delete to be performed
     * @param ui       User interface used
     * @param storage  Storage to update save file
     * @throws InvalidDeleteInputException
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws InvalidDeleteInputException {
        try {
            int num = Integer.parseInt(input.replaceAll("\\s+", ""));
            if (num > tasklist.getNumOfTask() || num <= 0) {
                throw new InvalidDeleteInputException();
            }
            Task task = tasklist.deleteTask(num);
            tasklist.updateData(storage);
            ui.showDelete(task, tasklist);
        } catch (NumberFormatException | IOException e) {
            throw new InvalidDeleteInputException();
        }
    }
}
