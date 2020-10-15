package DukeComponent;

import TaskList.TaskList;
import Tasks.Task;

import java.util.ArrayList;

/**
 * Components.Ui contains all the responses the program can give to a command.
 */
public class Ui {
    private static String print(String s) {
        return s;
    }

    /**
     * Gets the response to a done command.
     * @param task
     * @return done message
     */
    public static String getDoneMessage(Task task) {
        return print("\tNice! I've marked this task as done:\n"
                + "\t" + task + "\n");
    }

    /**
     * Gets the response to a delete command.
     * @param task
     * @param total
     * @return delete message
     */
    public static String getDeleteMessage(Task task, int total) {
        return print("\tNoted. I've removed this task:\n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    /**
     * Lists out all the tasks.
     * @param tasks
     * @return list of tasks
     */
    public static String list(TaskList tasks) {
        ArrayList<Task> list = tasks.getTaskList();
        String temp = "";
        for (int i = 0; i < list.size(); i++) {
            temp += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return print("\tHere are the tasks in your list:\n" + temp);
    }

    /**
     * Gets the search result to a find command.
     * @param tasks
     * @return search results
     */
    public static String getSearchResult(ArrayList<Task> tasks) {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++) {
            temp += "\t" + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return print("\tHere are the matching tasks in your list:\n" + temp);
    }

    /**
     * Gets the response to a add task command.
     * @param task
     * @param total
     * @return add task message
     */
    public static String getAddTaskMessage(Task task, int total) {
        return print("\tGot it. I've added this task: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    /**
     * Gets the response to an unrecognisable command.
     * @return ignore message
     */
    public static String getIgnoreMessage() {
        return print("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    /**
     * Gets the response to an empty input.
     * @return empty input message
     */
    public static String getEmptyInputMessage() {
        return print("\t☹ OOPS!!! The input cannot be empty.\n");
    }

    /**
     * Gets the response to a todo command with no name.
     * @return empty todo message
     */
    public static String getEmptyTodoMessage() {
        return print("\t☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    /**
     * Gets the response to a wrongly-formatted deadline command.
     * @return wrong deadline message
     */
    public static String getWrongDeadlineMessage() {
        return print("\t☹ OOPS!!! To add a deadline, enter 'deadline <TASK NAME> /by yyyy-mm-dd'\n");
    }

    /**
     * Gets the response to a wrongly-formatted event command.
     * @return wrong event message
     */
    public static String getWrongEventMessage() {
        return print("\t☹ OOPS!!! To add an event, enter 'event <TASK NAME> /at yyyy-mm-dd'\n");
    }

    /**
     * Gets the response to a wrongly-formatted done or delete command.
     * @param size size of the task list
     * @return wrong done or delete message
     */
    public static String getWrongDoneOrDeleteMessage(int size) {
        return size == 0
                ? print("\tList is empty. Add a task!\n")
                : print("\tTask does not exist.\n" +
                "\tCommand should be followed by a number\n" +
                "\tbetween 1 to " + size + ".\n");
    }

    /**
     * Gets the response to a wrongly-formatted find command.
     * @return wrong find message
     */
    public static String getWrongFindMessage() {
        return print("\t☹ OOPS!!! To find tasks containing a phrase" +
                ", enter 'find <PHRASE>'. Search phrase can contain spaces.\n");
    }

    /**
     * Gets the response to a wrong date format for event and deadline commands.
     * @return date format reminder
     */
    public static String getDateFormatReminder() {
        return print("Try again! Date format should be yyyy-mm-dd.");
    }

    /**
     * Gets the exit message before the program exits.
     * @return exit message
     */
    public static String getExitMessage() {
        return print("\tBye. Hope to see you again soon!\n");
    }

    /**
     * Gets the response to undo command when the last command is add task.
     * @param task
     * @param total
     * @return undo add message
     */
    public static String getUndoAddMessage(Task task, int total) {
        return print("\tGot it. I've reversed adding: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    /**
     * Gets the response to undo command when the last command is delete.
     * @param task
     * @param total
     * @return undo delete message
     */
    public static String getUndoDeleteMessage(Task task, int total) {
        return print("\tGot it. I've added the following back to the list: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    /**
     * Gets the response to undo command when the last command is mark as done.
     * @param task
     * @return undo done message
     */
    public static String getUndoDoneMessage(Task task) {
        return print("\tGot it. I've marked this task as undone:\n"
                + "\t" + task + "\n");
    }

    /**
     * Gets the response to undo command when the last command cannot be undone.
     * @return cannot undo message
     */
    public static String getCannotUndoMessage() {
        return print("\tSorry. The last command cannot be undone.\n");
    }
}

