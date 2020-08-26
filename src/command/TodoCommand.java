package command;

import data.DukeTaskList;
import exception.NoDescriptionException;
import task.ToDo;
import ui.Ui;

public class TodoCommand extends Command {

    public TodoCommand() {
        names = new String[] { "todo" };
    }

    @Override
    public void execute(String str) throws NoDescriptionException {
        ToDo newToDo = ToDo.createToDo(str);
        DukeTaskList.tasks.add(newToDo);
        Ui.reportNewTask(newToDo);
    }


}
