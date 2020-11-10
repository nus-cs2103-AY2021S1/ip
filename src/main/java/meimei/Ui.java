package meimei;

import java.util.Scanner;

import meimei.command.AddCommand;
import meimei.command.Command;
import meimei.command.DeleteCommand;
import meimei.task.Task;

/**
 * Represents the user interface that deals with interactions with the user.
 */
public class Ui {
    /** Scanner to take in user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Public constructor. Should only be used by bot.
     */
    public Ui() { }

    /**
     * Displays the welcome message to the user.
     *
     * @return Welcome message as a string.
     */
    public String returnWelcomeMsg() {
        return "Eh harlow! I'm Meimei!" + "\nWhat you want ah?";
    }

    /**
     * Reads user inputs which should contain commands to the bot.
     *
     * @return the line inputted by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints lines to frame bot responses in terminal.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a string with the response from the bot as given by executing a command.
     * Only for the following commands: AddCommand, DeleteCommand.
     *
     * @param command The command that triggers this method in its execute method.
     * @param task Task involved in the reply.
     * @param tasks Full list of tasks.
     * @return Response from Meimei Bot.
     */
    public String returnReply(Command command, Task task, TaskList tasks) {
        String reply = "";
        if (command instanceof AddCommand) {
            reply = getAddReply(task, tasks);
        } else if (command instanceof DeleteCommand) {
            reply = getDeleteReply(task, tasks);
        }

        assert !reply.equals("") : "No reply found in returnReply()";

        return reply;
    }

    public String returnDoneReply(Task task) {
        return "Can, I help you mark this as done liao:" + "\n  " + task.toString();
    }

    public String returnExitReply() {
        return "Ok bye bye! C u again :P";
    }

    public String returnFindReply(String stringOfFound) {
        return "Na, I found this:" + stringOfFound;
    }

    public String returnListReply(TaskList tasks) {
        return "Na, here is your list lah:" + tasks.toString();
    }

    public String returnError(String message) {
        return message;
    }

    private String getAddReply(Task task, TaskList tasks) {
        return "Orh. I added:" + "\n  " + task.toString()
                + "\nNow you got " + tasks.getListLength() + " things in the list.";
    }

    private String getDeleteReply(Task task, TaskList tasks) {
        return "Okay, I deleted this liao:" + "\n  " + task.toString()
                + "\nNow left " + tasks.getListLength() + " things in the list.";
    }
}
