package duke.commands;

import duke.util.OutputUi;
import duke.util.Storage;
import duke.tasks.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.DukeException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Locates tasks that contain the given keyword, storing them in an arraylist.
 */
public class FindCommand extends Command {
    static String keyword;

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
     * @return
     */
//    @Override
    public String execute(TaskList tasks, OutputUi ui, Storage storage) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Task t : tasks.getTasklist()) {
            if (t.getDescription().contains(keyword)) {
                relevantTasks.add(t);
            }
        }

        ui.reset();

        if (relevantTasks.isEmpty()) {
            ui.addSentence("no matching tasks!");
        } else {
            ui.addSentence("pingu found these matching tasks:");
            int count = 1;
            for (Task t : relevantTasks) {
                ui.addSentence(count + ". " + t.toString());
                count++;
            }
        }

        return ui.getResponse();
    }
}
