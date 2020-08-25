package duke.commands;

import duke.tasks.Task;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/**
 * Command to find words in list.
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructor to create FindCommand object.
     *
     * @param description
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Find tasks in TaskList which contains word being searched.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException when no words are found matching in list.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean wordExist = false;
        for (Task i : tasklist.getList()) {
            String task = i.toString();
            if (task.contains(description)) {
                ui.showMessage(task);
                wordExist = true;
            }
        }
        if (!wordExist) {
            throw new DukeException("word does not exist in TaskList!");
        }
    }
}
