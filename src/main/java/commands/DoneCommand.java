package main.java.commands;

import main.java.tasklist.*;
import main.java.ui.*;
import main.java.storage.*;
import main.java.tasks.*;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index){
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        ui.displayMarkAsDoneMessage(task);
        tasks.completeTask(index);
    }

}
