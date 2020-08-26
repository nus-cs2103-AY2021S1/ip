package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int i;

    public DeleteCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printDelete(taskList.delete(i));
            storage.refresh(taskList);
        } catch (IOException e) {
            System.out.println("Sorry something went wrong!");
        }
    }
}
