package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to find Tasks that contain the keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyWords;

    /**
     * Constructor for FindCommand.
     * @param keyWords String input of key words to compare with Tasks' description.
     */
    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks,
                        Queue<Action> commandQueue) {
        ui.findTasksMessage(tasks, keyWords);
    }
}
