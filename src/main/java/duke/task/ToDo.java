package duke.task;

import duke.exception.ExceptionMessage;
import duke.exception.NoDescriptionException;
import duke.ui.UiPrint;

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
        ToDo newToDo = new ToDo(UiPrint.TODO_ICON, todoInfo, todoInfo);
        return newToDo;
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    private static void checkException(String str) throws NoDescriptionException {
        if (str.isBlank()) {
            String errMessage = ExceptionMessage.TODO_NO_DESCRIPTION_MESSAGE;
            throw new NoDescriptionException(errMessage);
        }
    }
}
