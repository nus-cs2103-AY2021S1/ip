package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class GreetCommand extends Command {
    public GreetCommand() {}

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Hello I am\n"
                + " ____                _            " + "\n"
                + "|   _  \\    _   _    | | __    ___  " + "\n"
                + "|  | |  |    | | | |   | |/ /    | __ \\ " + "\n"
                + "|  |_|  |   | |_| |   |    \\    | ___/ " + "\n"
                + "|____/    \\__/    |_|\\_\\   \\____ " + "\n"
                + "How can I help you?";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
