package main.java.seedu.duke.commands;

import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {
        super("done");
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doneTask(taskNo);
        // ui.showDoneMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
