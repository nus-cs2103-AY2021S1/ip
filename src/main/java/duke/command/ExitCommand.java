package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list){
        Ui.goodbyeMessage();
    }

    public boolean isExit(){
        return true;
    }
}
