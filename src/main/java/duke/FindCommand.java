package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Locates tasks that contain the given keyword, storing them in an arraylist.
 */
public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Locates tasks that contain the given keyword, storing them in an arraylist.
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @throws IOException IOException.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Task t : tasks.getTasklist()) {
            System.out.println("TASK: " + t);
            if (t.getDescription().contains(keyword)) {
                relevantTasks.add(t);
            }
        }

        ui.printDivider();
        ui.printMsg("Here are the matching tasks Mr Camel found:");
        int counter = 1;
        for (Task t : relevantTasks) {
            ui.printMsg(counter + ". " + t.toString());
            counter++;
        }

        ui.printDivider();
    }
}
