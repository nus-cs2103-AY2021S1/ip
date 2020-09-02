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
        TaskList tasks = Duke.tasks;

        if (inputMsg.equals("list")) {
            return Ui.getAllTasksMsg(tasks.getTaskList());
        } else if (inputMsg.equals("help")) {
            return Ui.helpMsg();
        } else if (inputMsg.startsWith("done")) {
            try {
                int index = Integer.parseInt(inputMsg.split("done ")[1]);
                TaskList.doneTask(index, tasks.getTaskList());
                return Ui.doneTaskMsg(index, tasks.getTaskList());
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "done"
                return Warnings.invalidDoneTaskIndex(tasks.getTaskListSize());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "done"
                return Warnings.invalidDoneTaskIndex(tasks.getTaskListSize());
            }
        } else if (inputMsg.startsWith("todo")) {
            String taskTitle;
            try {
                taskTitle = inputMsg.split("todo ")[1];
                TaskList.addNewTodoTask(taskTitle, tasks.getTaskList());
                return Ui.addTodoTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't type the the task description after the keyword "to-do"
                return Warnings.invalidToDo();
            }
        } else if (inputMsg.startsWith("deadline")) {
            String taskTitle;
            String deadlineTime;
            try {
                taskTitle = inputMsg.split("deadline ")[1].split(" /by ")[0];
                deadlineTime = inputMsg.split("deadline ")[1].split(" /by ")[1];
                TaskList.addNewDeadlineTask(taskTitle, deadlineTime, tasks.getTaskList());
                return Ui.addDeadlineTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "deadline"
                return Warnings.invalidDeadline();
            }
        } else if (inputMsg.startsWith("event")) {
            try {
                String taskTitle = inputMsg.split("event ")[1].split(" /at ")[0];
                String eventTime = inputMsg.split("event ")[1].split(" /at ")[1];
                TaskList.addNewEventTask(taskTitle, eventTime, tasks.getTaskList());
                return Ui.addEventTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "event"
                return Warnings.invalidEvent();
            }
        } else if (inputMsg.startsWith("delete")) {
            try {
                int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                Task taskDeleted = TaskList.deleteTask(index, tasks.getTaskList());
                return Ui.deleteTaskMsg(index, taskDeleted, tasks);
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "delete"
                return Warnings.invalidDelete(tasks.getTaskListSize());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "delete"
                return Warnings.invalidDelete(tasks.getTaskListSize());
            }
        } else if (inputMsg.startsWith("find")) {
            String keyword = inputMsg.split("find ")[1];
            ArrayList<Task> matchedTasks = TaskList.findTask(keyword, tasks.getTaskList());
            return Ui.findMatchingTasks(keyword, matchedTasks);
        } else if (inputMsg.equals("bye")) {
            return Ui.byeMsg();
        } else {
            // if the user randomly enter any other commands which are not inside the command list
            return Warnings.invalidInput();
        }
    }

}
