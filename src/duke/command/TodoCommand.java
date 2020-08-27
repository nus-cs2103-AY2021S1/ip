package duke.command;

import duke.Duke;
import duke.exception.NoDescriptionException;
import duke.task.ToDo;

public class TodoCommand extends Command {

    public TodoCommand() {
        names = new String[] { "todo" };
    }

    @Override
    public void execute(String str, Duke duke) throws NoDescriptionException {
        ToDo newToDo = ToDo.createToDo(str);
        duke.taskList.addTask(newToDo);
        duke.ui.reportNewTask(newToDo);
    }


}
