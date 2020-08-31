package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {

    public static final String commandWord = "add";

    private int taskType;

    public AddCommand(String attributes, int taskType) {
        this.attributes = attributes;
        this.taskType = taskType;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        return true;
    }
}
