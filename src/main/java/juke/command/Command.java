package juke.command;

import juke.TaskList;
import juke.Storage;

public abstract class Command {

    public abstract String executeCommand(TaskList tasklist, Storage storage);

}
