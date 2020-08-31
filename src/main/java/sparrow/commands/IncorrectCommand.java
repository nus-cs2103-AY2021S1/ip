package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return feedbackToUser;
    }

}
