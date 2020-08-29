package duke.command;

import java.util.StringJoiner;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a list command.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand.
     */
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
            response.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        ui.printResponse(response.toString().isEmpty()
                ? "You have no tasks at the moment :)"
                : response.toString());
    }

    /**
     * Returns a response after executing the list command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String executeWithResponse(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        return response.toString().isEmpty()
                ? "You have no tasks at the moment :)"
                : response.toString();
    }

    @Override
    public String toString() {
        return "list";
    }
}
