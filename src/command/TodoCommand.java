package command;

import exception.NoDescriptionException;
import task.ToDo;
import ui.UIPrint;
import data.DukeData;
import function.DukeFunction;

public class TodoCommand extends Command {

    public TodoCommand() {
        names = new String[] { "todo" };
    }

    @Override
    public void execute(String str) throws NoDescriptionException {
        ToDo newToDo = ToDo.createToDo(str);
        DukeData.tasks.add(newToDo);
        DukeFunction.reportNewTask(newToDo);
    }


}
