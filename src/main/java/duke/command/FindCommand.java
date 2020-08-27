package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        String[] input = fullCommand.split(" ");
        String toFind;
        if (input.length == 2) {
            toFind = fullCommand.substring(fullCommand.indexOf(" ")).trim();
            ArrayList<Task> results = taskList.find(toFind);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, results.get(i)));
            }
            ui.showLine();
        } else {
            throw new DukeException("No input search item!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
