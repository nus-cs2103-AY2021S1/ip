package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidDeleteInputException;

/**
 * DeleteCommand deals with delete input.
 */
public class DeleteCommand extends Command {
    private String input;

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
     * @param storage  Storage to update save file
     * @throws InvalidDeleteInputException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws InvalidDeleteInputException {
        try {
            int num = Integer.parseInt(input.replaceAll("\\s+", ""));

            boolean isInvalidInput = num > tasklist.getNumOfTask() || num <= 0;
            boolean isValidInput = !isInvalidInput;

            if (isInvalidInput) {
                throw new InvalidDeleteInputException();
            }

            assert isValidInput;
            Task task = tasklist.deleteTask(num);
            tasklist.updateData(storage);
            return Ui.showDelete(task, tasklist);
        } catch (NumberFormatException | IOException e) {
            throw new InvalidDeleteInputException();
        }
    }
}
