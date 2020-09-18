package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        return feedbackToUser;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof IncorrectCommand
                && feedbackToUser.equals(((IncorrectCommand) other).feedbackToUser));
    }
}
