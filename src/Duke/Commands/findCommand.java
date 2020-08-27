package Duke.Commands;

import Duke.Task.TaskList;

public class findCommand extends Command{
    String str;

    public findCommand(TaskList tasklist,String str){
        super(tasklist);
        this.str = str;
    }

    @Override
    public void execute(){
        this.tasklist.findTask(this.str);
    }
}
