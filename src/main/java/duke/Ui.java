package duke;

import java.util.Scanner;

import exceptions.DukeException;
import tasks.TaskList;

/**
 * Handles interactions with users, namely accepting inputs and printing of info.
 */
public class Ui {
    private static final String LINE = "---------------------------------------------------------------";
    private static final String INTRO = "Welcome to Duke! How may I help you today?";
    private Scanner sc;
    private String message;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads an input from a user.
     * @return The user's input.
     */
    public String takeInput() {
        return sc.nextLine();
    }
    public String getMessage() {
        return message;
    }
    public static String getIntro() {
        return INTRO;
    }
    public void setMessageException(DukeException e) {
        message = e.getMessage();
    }
    public void setMessagePrintHelp() {
        message = "Welcome to Duke! Here is a list of commands you can use: \n\n"
                + "todo <name> - adds a Todo task to your list\n"
                + "deadline <name> /by <time> - adds a Deadline task to your list\n"
                + "event <name> /at <time> - adds an Event task to your list\n"
                + "list - displays the current list of your tasks\n"
                + "done <number> - marks a task as done\n"
                + "undone <number> - marks a task as not done \n"
                + "delete <number> - deletes a task from your list\n"
                + "find <keyword> - displays all tasks containing the exact keyword\n"
                + "upcoming <number> - displays all tasks occurring within the given number of days\n"
                + "help - displays this helpful message\n"
                + "exit - shuts down the bot\n"
                + LINE;
    }
    public void setMessagePrintList(TaskList tasks) {
        message = "Here are your tasks:\n"
                + LINE + "\n"
                + tasks + "\n"
                + LINE;
    }
    public void setMessageExit() {
        message = "Goodbye!";
    }
    public void setMessageNewTask(String task, int size) {
        message = "Added new task: " + task + "\n"
                + "You now have " + size + (size == 1
                        ? "task in your list."
                        : " tasks in your list.");
    }
    public void setMessageDoneTask() {
        message = "Congrats, I have marked that task as finished!";
    }
    public void setMessageDeleteTask(TaskList tasks, int idx) {
        message = "The task " + tasks.getTasks().get(idx - 1) + " has been removed.";
    }
    public void setMessageFindTask(String tasks, int num) {
        if (num == 0) {
            message = "I couldn't find any tasks matching your keyword.";
        } else {
            message = "I found " + num + (num > 1 ? " tasks " : " task ") + "matching your keyword.\n" + tasks;
        }
    }
    public void setMessageUndoneTask() {
        message = "I have marked that task as unfinished.";
    }
    public void setMessageUpcoming(String tasks, int numTasks, int numDays) {
        if (numTasks == 0) {
            message = "I couldn't find any tasks occurring within " + numDays + " days.";
        } else {
            message = "I found " + numTasks + (numTasks > 1 ? " tasks " : " task ") + "occurring within " + numDays
                    + " days.\n" + tasks;
        }
    }
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }
}
