package main.java.duke.command;

import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public class ListCommand extends Command {

    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
