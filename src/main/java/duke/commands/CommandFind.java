package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandFind extends Command {

    public static final String COMMAND_STRING = "find";
    private String searchTerm;

    /**
     * Constructor for {@code CommandFind}.
     *
     * @param taskList Task list
     * @param ui Ui object
     * @param searchTerm String term to search for
     */
    public CommandFind(TaskList taskList, Ui ui, String searchTerm) {
        super(taskList, ui);
        this.searchTerm = searchTerm.toLowerCase();
    }

    @Override
    public void execute() {
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            if (task.toString().toLowerCase().contains(searchTerm)) {
                output += String.format("%d. %s\n", counter, task.toString());
                counter++;
            }
        }
        ui.outputBlockToUser(output);
    }
}
