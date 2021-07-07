package duke;

import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * Takes in user inputs and make sense of them.
 */
public class Parser {
    /**
     * Parses the inputs and calls the corresponding execution.
     *
     * @param inputMsg
     */
    public static String parseUserInput(String inputMsg) {
        assert !inputMsg.isEmpty() : "Input command should not be empty.";
        TaskList tasks = Duke.tasks;
        ArchivedTaskList archivedTasks = Duke.archivedTasks;

        if (inputMsg.equals("list")) {
            return Ui.getAllTasksMsg(tasks.getTaskList());
        } else if (inputMsg.equals("help")) {
            return Ui.getHelpMsg();
        } else if (inputMsg.startsWith("done")) {
            return parseDone(inputMsg, tasks);
        } else if (inputMsg.startsWith("todo")) {
            return parseToDo(inputMsg, tasks);
        } else if (inputMsg.startsWith("deadline")) {
            return parseDeadline(inputMsg, tasks);
        } else if (inputMsg.startsWith("event")) {
            return parseEvent(inputMsg, tasks);
        } else if (inputMsg.startsWith("delete")) {
            return parseDelete(inputMsg, tasks);
        } else if (inputMsg.startsWith("find")) {
            return parseFind(inputMsg, tasks);
        } else if (inputMsg.startsWith("archive")) {
            return parseArchive(inputMsg, tasks, archivedTasks);
        } else if (inputMsg.equals("list archive")) {
            return Ui.getAllArchivedTasksMsg(archivedTasks);
        } else if (inputMsg.equals("bye")) {
            return Ui.getByeMsg();
        } else {
            // if the user randomly enter any other commands which are not inside the command list
            return Warnings.getInvalidInputMsg();
        }
    }

    private static String parseArchive(String inputMsg, TaskList tasks, ArchivedTaskList archivedTasks) {
        try {
            String indexStr = inputMsg.split("archive ")[1];
            assert !indexStr.isEmpty() : "Index should not be empty.";

            if (indexStr.equals("all")) {
                ArchivedTaskList.archiveAllTasks(tasks.getTaskList(), archivedTasks.getArchivedTaskList());
                return Ui.getArchiveAllTaskMsg(archivedTasks);
            }

            int index = Integer.parseInt(inputMsg.split("archive ")[1]);
            Task taskToArchive = TaskList.deleteTask(index, tasks.getTaskList());
            ArchivedTaskList.addArchivedTask(taskToArchive, archivedTasks.getArchivedTaskList());
            return Ui.getAddingArchiveTaskMsg(taskToArchive, archivedTasks);
        } catch (IndexOutOfBoundsException e) {
            return Warnings.getInvalidArchiveMsg(tasks.getTaskListSize());
        } catch (NumberFormatException e) {
            // if the user doesn't key in a valid index after keyword "archive"
            return Warnings.getInvalidArchiveMsg(tasks.getTaskListSize());
        }
    }

    private static String parseFind(String inputMsg, TaskList tasks) {
        try {
            String keyword = inputMsg.split("find ")[1];
            ArrayList<Task> matchedTasks = TaskList.findTask(keyword, tasks.getTaskList());
            return Ui.getMatchingTasksMsg(keyword, matchedTasks);
        } catch (IndexOutOfBoundsException e) {
            // if the user doesn't key in a valid index after keyword "find"
            return Warnings.getInvalidFindMsg();
        }
    }

    private static String parseDelete(String inputMsg, TaskList tasks) {
        try {
            int index = Integer.parseInt(inputMsg.split("delete ")[1]);
            Task taskDeleted = TaskList.deleteTask(index, tasks.getTaskList());
            return Ui.getDeleteTaskMsg(index, taskDeleted, tasks);
        } catch (IndexOutOfBoundsException e) {
            // if the user doesn't type the index after the keyword "delete"
            return Warnings.getInvalidDeleteMsg(tasks.getTaskListSize());
        } catch (NumberFormatException e) {
            // if the user doesn't key in a valid index after keyword "delete"
            return Warnings.getInvalidDeleteMsg(tasks.getTaskListSize());
        }
    }

    private static String parseEvent(String inputMsg, TaskList tasks) {
        try {
            String taskTitle = inputMsg.split("event ")[1].split(" /at ")[0];
            String eventTime = inputMsg.split("event ")[1].split(" /at ")[1];
            TaskList.addNewEventTask(taskTitle, eventTime, tasks.getTaskList());
            return Ui.getAddingEventTaskMsg(tasks.getTaskList());
        } catch (ArrayIndexOutOfBoundsException e) {
            // if the user doesn't follow the correct format after the keyword "event"
            return Warnings.getInvalidEventMsg();
        } catch (DateTimeException e) {
            return Warnings.getInvalidDateMsg();
        }
    }

    private static String parseDeadline(String inputMsg, TaskList tasks) {
        String taskTitle;
        String deadlineTime;
        try {
            taskTitle = inputMsg.split("deadline ")[1].split(" /by ")[0];
            deadlineTime = inputMsg.split("deadline ")[1].split(" /by ")[1];
            TaskList.addNewDeadlineTask(taskTitle, deadlineTime, tasks.getTaskList());
            return Ui.getAddingDeadlineTaskMsg(tasks.getTaskList());
        } catch (ArrayIndexOutOfBoundsException e) {
            // if the user doesn't follow the correct format after the keyword "deadline"
            return Warnings.getInvalidDeadlineMsg();
        } catch (DateTimeException e) {
            return Warnings.getInvalidDateMsg();
        }
    }

    private static String parseToDo(String inputMsg, TaskList tasks) {
        String taskTitle;
        try {
            taskTitle = inputMsg.split("todo ")[1];
            TaskList.addNewTodoTask(taskTitle, tasks.getTaskList());
            return Ui.getAddingTodoTaskMsg(tasks.getTaskList());
        } catch (ArrayIndexOutOfBoundsException e) {
            // if the user doesn't type the the task description after the keyword "to-do"
            return Warnings.getInvalidToDoMsg();
        }
    }

    private static String parseDone(String inputMsg, TaskList tasks) {
        try {
            int index = Integer.parseInt(inputMsg.split("done ")[1]);
            TaskList.doneTask(index, tasks.getTaskList());
            return Ui.getDoneTaskMsg(index, tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            // if the user doesn't type the index after the keyword "done"
            return Warnings.getInvalidDoneTaskIndexMsg(tasks.getTaskListSize());
        } catch (NumberFormatException e) {
            // if the user doesn't key in a valid index after keyword "done"
            return Warnings.getInvalidDoneTaskIndexMsg(tasks.getTaskListSize());
        }
    }
}
