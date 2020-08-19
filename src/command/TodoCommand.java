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
        if (str.isBlank()) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nOOPS!!! The description of a todo cannot be empty.\n" + line;

            throw new NoDescriptionException(errMessage);
        }

        ToDo newToDo = new ToDo(UIPrint.todoIcon, str);
        DukeData.tasks.add(newToDo);
        DukeFunction.reportNewTask(newToDo);
    }
}
