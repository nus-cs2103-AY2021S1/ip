package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT = "Bye. Hope t' see ye again soon!";

    /**
     * Creates an ExitCommand to terminate the program.
     */
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        return MESSAGE_EXIT;
    }
}
