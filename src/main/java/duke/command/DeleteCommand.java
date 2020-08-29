package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.io.IOException;

public class DeleteCommand implements Command {
    private final String item;

    public DeleteCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int number = Integer.parseInt(item) - 1;
            tasks.deleteTask(number);
            storage.updateDataFile(tasks.getList());
        } catch (IOException error) {
            error.printStackTrace();
        } catch (NumberFormatException error) {
            System.out.println("Please provide a valid task number to delete");
        } catch (IndexOutOfBoundsException error) {
            System.out.println("This task is not in your list");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}