package duke.command;

import duke.data.DukeTaskList;
import duke.exception.NoDescriptionException;
import duke.task.ToDo;
import duke.ui.Ui;

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
