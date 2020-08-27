package duke.task;

import duke.exception.NoDescriptionException;
import duke.ui.UIPrint;

/**
 * A ToDo is a task with no time info. Todo objects store only task description.
 */
public class ToDo extends Task {

    private ToDo(String icon, String description, String taskInfo) {

        super(icon, description, taskInfo);
    }

    /**
     * Creates a ToDo using a string of task info
     * @param todoInfo the string of task info
     * @return the ToDo object created
     */
    public static ToDo createToDo(String todoInfo) {
        checkException(todoInfo);
        ToDo newToDo = new ToDo(UIPrint.todoIcon, todoInfo, todoInfo);
        return newToDo;
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    private static void checkException(String str) throws NoDescriptionException {
        if (str.isBlank()) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "\nOOPS!!! The description of a todo cannot be empty.\n" + line;

            throw new NoDescriptionException(errMessage);
        }
    }
}
