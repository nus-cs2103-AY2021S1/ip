package main.java.duke.command;

import java.io.IOException;
import main.java.duke.core.*;
import main.java.duke.task.*;
import main.java.duke.handle.*;

public abstract class Command {
    abstract public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException;

    public boolean isContinuing() {
        return true;
    }
}