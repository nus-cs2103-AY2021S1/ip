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
        assert !inputMsg.isEmpty() : "Input command should not be empty!";
        TaskList tasks = Duke.tasks;
        String outputMsg = "";

        if (inputMsg.equals("list")) {
            outputMsg = Ui.getAllTasksMsg(tasks.getTaskList());
        } else if (inputMsg.equals("help")) {
            outputMsg = Ui.helpMsg();
        } else if (inputMsg.startsWith("done")) {
            try {
                int index = Integer.parseInt(inputMsg.split("done ")[1]);
                TaskList.doneTask(index, tasks.getTaskList());
                outputMsg = Ui.doneTaskMsg(index, tasks.getTaskList());
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "done"
                outputMsg = Warnings.invalidDoneTaskIndex(tasks.getTaskListSize());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "done"
                outputMsg = Warnings.invalidDoneTaskIndex(tasks.getTaskListSize());
            }
        } else if (inputMsg.startsWith("todo")) {
            String taskTitle;
            try {
                taskTitle = inputMsg.split("todo ")[1];
                TaskList.addNewTodoTask(taskTitle, tasks.getTaskList());
                outputMsg = Ui.addTodoTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't type the the task description after the keyword "to-do"
                outputMsg = Warnings.invalidToDo();
            }
        } else if (inputMsg.startsWith("deadline")) {
            String taskTitle;
            String deadlineTime;
            try {
                taskTitle = inputMsg.split("deadline ")[1].split(" /by ")[0];
                deadlineTime = inputMsg.split("deadline ")[1].split(" /by ")[1];
                TaskList.addNewDeadlineTask(taskTitle, deadlineTime, tasks.getTaskList());
                outputMsg = Ui.addDeadlineTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "deadline"
                outputMsg = Warnings.invalidDeadline();
            }
        } else if (inputMsg.startsWith("event")) {
            try {
                String taskTitle = inputMsg.split("event ")[1].split(" /at ")[0];
                String eventTime = inputMsg.split("event ")[1].split(" /at ")[1];
                TaskList.addNewEventTask(taskTitle, eventTime, tasks.getTaskList());
                outputMsg = Ui.addEventTaskMsg(tasks.getTaskList());
            } catch (ArrayIndexOutOfBoundsException e) {
                // if the user doesn't follow the correct format after the keyword "event"
                outputMsg = Warnings.invalidEvent();
            }
        } else if (inputMsg.startsWith("delete")) {
            try {
                int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                Task taskDeleted = TaskList.deleteTask(index, tasks.getTaskList());
                outputMsg = Ui.deleteTaskMsg(index, taskDeleted, tasks);
            } catch (IndexOutOfBoundsException e) {
                // if the user doesn't type the index after the keyword "delete"
                outputMsg = Warnings.invalidDelete(tasks.getTaskListSize());
            } catch (NumberFormatException e) {
                // if the user doesn't key in a valid index after keyword "delete"
                outputMsg = Warnings.invalidDelete(tasks.getTaskListSize());
            }
        } else if (inputMsg.startsWith("find")) {
            String keyword = inputMsg.split("find ")[1];
            ArrayList<Task> matchedTasks = TaskList.findTask(keyword, tasks.getTaskList());
            outputMsg = Ui.findMatchingTasks(keyword, matchedTasks);
        } else if (inputMsg.equals("bye")) {
            outputMsg = Ui.byeMsg();
        } else {
            // if the user randomly enter any other commands which are not inside the command list
            outputMsg = Warnings.invalidInput();
        }
        assert !outputMsg.isEmpty() : "Output message should not be empty.";
        return outputMsg;
    }

}
