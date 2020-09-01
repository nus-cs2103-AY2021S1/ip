package java.commands;

import java.tasklist.*;
import java.ui.*;
import java.storage.*;
import java.tasks.*;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index){
        this.index = index;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        String output = ui.displayMarkAsDoneMessage(task);
        tasks.completeTask(index);
        return output;

    }

}
