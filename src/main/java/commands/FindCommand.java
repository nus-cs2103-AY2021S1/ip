package commands;

import duke.Storage;
import duke.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents an instruction from the user to find specific tasks matching the input keywords.
 */

public class FindCommand extends Command {
    private String text;

    public FindCommand(String text) {
        this.text = text;
    }

    /**
     * Executes the command to find the specific tasks matching the input keywords.
     * @param tasks The current TaskList.
     * @param ui The Ui object in use.
     * @param storage The Storage object in use.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null : "Null UI";
        assert tasks != null : "Null Tasklist";
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Task task : tasks.getTasks()) {
            String name = task.getName();
            if (name.contains(text)) {
                output.append(counter == 1 ? "" : "\n").append(counter).append(".").append(task);
                counter++;
            }
        }
        ui.setMessageFindTask(output.toString(), counter - 1);
    }
}
