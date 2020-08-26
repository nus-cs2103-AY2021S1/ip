package duke.command;

import duke.io.Layout;
import duke.io.Parser;
import duke.task.Task;

import java.util.ArrayList;

public abstract class Command {
    
    protected ArrayList<Task> tasks;
    protected Layout layout;
    protected Parser parser;

    protected Command(ArrayList<Task> tasks) {
        this.tasks = tasks;
        layout = new Layout();
        parser = new Parser();
    }
    
}
