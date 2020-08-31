package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private final String item;

    public DoneCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int number = Integer.parseInt(item) - 1;
            tasks.markAsDone(number);
            storage.updateDataFile(tasks.getList());
        } catch (IOException error) {
            error.printStackTrace();
        } catch (NumberFormatException error) {
            System.out.println("Please provide a valid task number to mark as done");
        } catch (IndexOutOfBoundsException error) {
            System.out.println("This task is not in your list");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
