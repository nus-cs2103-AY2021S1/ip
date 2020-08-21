package main.java.duke.command;

import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public class ExitCommand extends Command {
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isContinuing() {
        return false;
    }
}
