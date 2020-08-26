package command;

import command.Command;
import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            output = output + i + ". " + taskList.get(i - 1) + "\n";
        }
        System.out.println(ui.LINE + "Here are the tasks in your list: \n" + output + ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
