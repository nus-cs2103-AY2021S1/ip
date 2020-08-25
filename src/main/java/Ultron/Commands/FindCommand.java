package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.UI;
import ultron.exceptions.UltronException;
import ultron.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    public FindCommand(String arguments){
        super(false, arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException {

        boolean printed = false;
        int count = 1;
        ui.print("Why do you always bothering me?\n");
        for(Task task : taskList.getList()){
            if(task.getMessage().contains(getArguments())){
                ui.print(String.format("%d. %s\n",count++, task.toString()));
                printed = true;
            }
        }

        if (!printed){
            ui.print("There is literally nothing here\n");
            return;
        }


    }
}
