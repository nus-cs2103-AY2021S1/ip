package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.StringJoiner;

/**
 * Responsible for executing a list command.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(true);
    }

    /**
     * Executes a list command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.add(String.format("%d.%s", i+1, tasks.get(i)));
        }
        ui.printResponse(response.toString());
    }

    @Override
    public String toString() {
        return "list";
    }
}
