package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

public class DeleteCommand extends Command {
    public String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean isExited() {
        return false;
    }
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws InvalidDeleteInputException, IOException {
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
