package duke;

import java.util.ArrayList;

/**
 * Takes in user inputs and make sense of them.
 */
public class Parser {
    /**
     * Parses the inputs and calls the corresponding execution.
     * @param inputMsg
     */
    public static String parseUserInput(String inputMsg) {
        ArrayList<Task> tasks = Duke.tasks.getTaskList();

        if (inputMsg.equals("list")) {
            return Ui.getAllTasksMsg(tasks);
        } else if (inputMsg.equals("help")) {
            return Ui.helpMsg();
        } else if (inputMsg.startsWith("done")) {
            try {
                int index = Integer.parseInt(inputMsg.split("done ")[1]);
                return TaskList.doneTask(index, tasks);
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "done"
                return Warnings.invalidDoneTaskIndex(tasks.size());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "done"
                return Warnings.invalidDoneTaskIndex(tasks.size());
            }
        } else if (inputMsg.startsWith("todo")) {
            String taskTitle;
            try {
                taskTitle = inputMsg.split("todo ")[1];
                return TaskList.addNewTodoTask(taskTitle, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't type the the task description after the keyword "todo"
                return Warnings.invalidToDo();
            }
        } else if (inputMsg.startsWith("deadline")) {
            String taskTitle;
            String deadlineTime;
            try {
                taskTitle = inputMsg.split("deadline ")[1].split(" /by ")[0];
                deadlineTime = inputMsg.split("deadline ")[1].split(" /by ")[1];
                return TaskList.addNewDeadlineTask(taskTitle, deadlineTime, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "deadline"
                return Warnings.invalidDeadline();
            }
        } else if (inputMsg.startsWith("event")) {
            try {
                String taskTitle = inputMsg.split("event ")[1].split(" /at ")[0];
                String eventTime = inputMsg.split("event ")[1].split(" /at ")[1];
                return TaskList.addNewEventTask(taskTitle, eventTime, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "event"
                return Warnings.invalidEvent();
            }
        } else if (inputMsg.startsWith("delete")) {
            try {
                int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                return TaskList.deleteTask(index, tasks);
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "delete"
                return Warnings.invalidDelete(tasks.size());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "delete"
                return Warnings.invalidDelete(tasks.size());
            }
        } else if (inputMsg.startsWith("find")) {
            String keyword = inputMsg.split("find ")[1];
            return TaskList.findTask(keyword, tasks);
        } else if (inputMsg.equals("bye")) {
            return Ui.byeMsg();
        } else {
            // if the user randomly enter any other commands which are not inside the command list
            return Warnings.invalidInput();
        }
    }

}
