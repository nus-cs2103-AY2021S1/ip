package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to show list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     */
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        int counter = 0;
        String reply = ui.printList() + "\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            if (i != tasks.getSize() - 1) {
                reply += i + TASK_INDEX + "." + tasks.getTask(i) + "\n";
            } else {
                reply += i + TASK_INDEX + "." + tasks.getTask(i);
            }
            counter++;
        }
        if (counter == 0) {
            return ui.printNoTasks();
        }
        return reply;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
