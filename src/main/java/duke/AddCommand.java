package duke;

/**
 * Represents an add command in Duke.
 */
public class AddCommand extends Command {
    private final String command;
    private final String input;

    /**
     * Creates a new AddCommand instance.
     *
     * @param command type of task to be added.
     * @param input description of task.
     */
    public AddCommand(String command, String input) {
        this.command = command;
        this.input = input;
    }

    /**
     * Executes the add command by processing the input TaskList, Ui, and Storage instances.
     *
     * @param list a TaskList containing all Duke's current tasks.
     * @param ui a user interface in charge of Duke's I/O.
     * @param storage a storage system that handles .txt file manipulation.
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.addTask(command, input);
        String output = "Got it. I've added this task: \n";
        output += task;
        output += "\nNow you have " + list.size() + " tasks in the list.";
        storage.updateTextFile(list);
        ui.printLine(output);
    }
}
