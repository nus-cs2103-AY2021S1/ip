package duke.command;

import duke.*;

public class CommandBye implements Command {
    public boolean isExit() { return true; }
    public void execute(TaskList tasks, Ui ui) {
        ui.printLine("Bye. Hope to see you again soon!");
    }
}