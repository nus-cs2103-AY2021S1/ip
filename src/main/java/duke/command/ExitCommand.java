package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

public class ExitCommand extends Command{
    public final static String COMMAND = "bye";
    
    public ExitCommand() {
        super();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.write(tasks);
        ui.showGoodbye();
    }
}
