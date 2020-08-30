package duke;

import java.util.Scanner;

import exceptions.DukeException;
import tasks.TaskList;

/**
 * Handles interactions with users, namely accepting inputs and printing of info
 */
public class Ui {
    private static final String LINE = "---------------------------------------------------------------";
    private static final String INTRO = "Welcome to Duke! How may I help you today?";
    private Scanner sc;
    private String message;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads an input from a user
     * @return The user's input
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
        this.message = e.getMessage();
    }
    public void setMessageHelp() {
        this.message = "Welcome to Duke! Here is a list of commands you can use: \n"
                + "exit - shuts down the bot\n"
                + "todo <name> - adds a Todo task to your list\n"
                + "deadline <name> <time> - adds a Deadline task to your list\n"
                + "event <name> <time> - adds an Event task to your list\n"
                + "done <number> - marks a task as done\n"
                + "delete <number> - deletes a task from your list\n"
                + "list - displays the current list of your tasks\n"
                + "help - displays this helpful message\n";
    }
    public void setMessageList(TaskList tasks) {
        message = "Here are your tasks:\n"
                + LINE + "\n"
                + tasks + "\n"
                + LINE;
    }
    public void setMessageExit() {
        message = "Goodbye!";
    }
    public void setMessageAddTask(String task, int size) {
        message = "Added new task: " + task + "\n"
                + "You now have " + size + (size == 1
                        ? "task in your list."
                        : " tasks in your list.");
    }
    public void setMessageDoneTask() {
        message = "Congrats, I've marked that task as finished!";
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
        message = "I've marked that task as unfinished.";
    }
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }
}
