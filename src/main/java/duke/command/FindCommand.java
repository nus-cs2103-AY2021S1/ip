package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find mathcing keywords in list.
 */
public class FindCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     * @param extra String keyword of task to be searched.
     */
    public FindCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        assert storage != null : "Storage object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert ui != null : "Ui object cannot be null";
        int counter = 0;
        String reply = ui.printFind() + "\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(extra)) {
                if (i != tasks.getSize() - 1) {
                    reply += i + 1 + "." + tasks.getTask(i) + "\n";
                } else {
                    reply += i + 1 + "." + tasks.getTask(i);
                }
                counter++;
            }
        }
        if (counter == 0) {
            return ui.printNoMatch(extra);
        }
        return reply;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
