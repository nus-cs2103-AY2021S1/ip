package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class Command {

    protected int targetIndex = -1;
    protected boolean isExit = false;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {

    }

    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes!");
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getTargetIndex() {
        return this.targetIndex;
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
