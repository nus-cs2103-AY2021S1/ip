package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.util.List;

public class ByeCommand extends Command {
    protected static final List<String> NAMES = List.of("bye", "exit");
    protected static final String DESCRIPTION = "Exits ALICE program";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "]";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) {
        ui.displayOutput("Goodbye. Hope to see you again soon!");
    }
}
