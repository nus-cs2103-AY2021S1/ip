package Command;

import DukeComponent.Ui;
import TaskList.TaskList;

public class ExitCommand extends Command{
    public ExitCommand(){
        super(CommandType.EXIT);
    }

    @Override
    public void act(TaskList list) {
        Ui.exitMessage();
    }
}
