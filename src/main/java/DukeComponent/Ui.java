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

    public static String welcomeMessage() {
        return print("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n");
    }

    public static String doneMessage(Task task) {
        return print("\tNice! I've marked this task as done:\n"
                + "\t" + task + "\n");
    }

    public static String deleteMessage(Task task, int total) {
        return print("\tNoted. I've removed this task:\n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    public static String list(TaskList tasks) {
        ArrayList<Task> list = tasks.getTaskList();
        String temp = "";
        for (int i = 0; i < list.size(); i++) {
            temp += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return print("\tHere are the tasks in your list:\n" + temp);
    }

    public static String searchResult(ArrayList<Task> tasks) {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++) {
            temp += "\t" + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return print("\tHere are the matching tasks in your list:\n" + temp);
    }

    public static String addTaskMessage(Task task, int total) {
        return print("\tGot it. I've added this task: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    public static String ignoreMessage() {
        return print("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    public static String emptyInputMessage() {
        return print("\t☹ OOPS!!! The input cannot be empty.\n");
    }

    public static String emptyTodoMessage() {
        return print("\t☹ OOPS!!! The description of a todo cannot be empty.\n");
    }

    public static String wrongDeadlineMessage() {
        return print("\t☹ OOPS!!! To add a deadline, enter 'deadline <TASK NAME> /by yyyy-mm-dd'\n");
    }

    public static String wrongEventMessage() {
        return print("\t☹ OOPS!!! To add an event, enter 'event <TASK NAME> /at yyyy-mm-dd'\n");
    }

    public static String wrongDoneOrDeleteMessage(int size) {
        return size == 0
                ? print("\tList is empty. Add a task!\n")
                : print("\tCommand should be followed by a number between 1 to " + size + "\n");
    }

    public static String wrongFindMessage() {
        return print("\t☹ OOPS!!! To find tasks containing a phrase" +
                ", enter 'find <PHRASE>'. Search phrase can contain spaces.\n");
    }

    public static String dateFormatReminder() {
        return print("Try again! Date format should be yyyy-mm-dd.");
    }

    public static String exitMessage() {
        return print("\tBye. Hope to see you again soon!\n");
    }

    public static String undoAddMessage(Task task, int total) {
        return print("\tGot it. I've reversed adding: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    public static String undoDeleteMessage(Task task, int total) {
        return print("\tGot it. I've added the following back to the list: \n"
                + "\t" + task + "\n"
                + "\tNow you have " + total + " tasks in the list.\n");
    }

    public static String undoDoneMessage(Task task) {
        return print("\tGot it. I've marked this task as undone:\n"
                + "\t" + task + "\n");
    }

    public static String cannotUndoMessage() {
        return print("\tSorry. The last command cannot be undone.\n");
    }
}

