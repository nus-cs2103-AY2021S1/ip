package duke;

import java.util.ArrayList;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasklist = tasks.getTasklist();

        ui.printDivider();
        ui.printMsg("\tTasks");

        for (int i = 1; i < tasklist.size() + 1; i++) {
            ui.printMsg("\t" + i + ". " + tasklist.get(i-1));
        }

        ui.printDivider();
    }
}
