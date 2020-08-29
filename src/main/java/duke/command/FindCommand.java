package duke.command;

import java.util.StringJoiner;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private String toFind;

    /**
     * Constructs a FindCommand.
     *
     * @param toFind The task to find.
     */
    public FindCommand(String toFind) {
        super(true);
        this.toFind = toFind;
    }

    /**
     * Executes a find command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     *
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        response.add(String.format("Here are the tasks in your list that contain \"%s\":", toFind));
        response.add(tasks.find(toFind));
        ui.printResponse(response.toString());
    }

    /**
     * Returns a response after executing the find command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String executeWithResponse(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        response.add(String.format("Here are the tasks in your list that contain \"%s\":", toFind));
        response.add(tasks.find(toFind));
        return response.toString();
    }
}
