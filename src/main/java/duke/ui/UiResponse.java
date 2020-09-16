package duke.ui;

import java.util.ArrayList;
import java.util.HashMap;

import duke.Duke;
import duke.command.Command;
import duke.data.DukeCommandSet;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * UiResponse interacts with users by printing out messages in console.
 * e.g. When the user enters a command, the command function would be
 * executed, and methods in UiResponse is usually called in by the execute function
 * to respond the user.
 */
public class UiResponse {

    private Duke duke;

    /**
     * Constructs a UiResponse.
     * @param duke the Duke that will use this UiResponse
     */
    public UiResponse(Duke duke) {
        this.duke = duke;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(UiPrint.LOGO);

        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Prints out the current tasks saved in task list.
     */
    public void reportCurrentTasks() {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Current tasks:\n");

        for (int i = 1; i <= duke.getTaskList().getSize(); i++) {
            System.out.println(i + ". " + duke.getTaskList().getTask(i));
        }

        if (duke.getTaskList().getSize() == 0) {
            System.out.println("None");
        }

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Tells the user a new task is added to the task list.
     * @param task the new task added
     */
    public void reportNewTask(Task task) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.getTaskList().getSize() + " tasks in the list.");

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Tells the user a task has been marked as done.
     * @param task the task marked as done
     */
    public void reportDoneTask(Task task) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Nice, I've marked this task as done:");
        System.out.println(task);

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Tells the user a task has been deleted.
     * @param task the task deleted
     */
    public void reportDeleteTask(Task task) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.getTaskList().getSize() + " tasks in the list");

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Says good bye to the user.
     */
    public void reportExit() {
        UiPrint.drawLine(UiPrint.STAR, 50);

        String exitWords = "Bye, hope to see you again soon!";
        System.out.println(exitWords);

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Shows the input tasks to the user.
     * @param tasks tasks to show
     */
    public void reportGivenTasks(ArrayList<Task> tasks) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("Here are the matching tasks in your list:");

        if (tasks.size() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        }

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Reports the input exception to user.
     * @param exception input exception
     */
    public void reportException(DukeException exception) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("\n" + exception.getMessage());

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Shows description of all commands.
     * @param commandSet the DukeCommandSet
     */
    public void showAllCommands(DukeCommandSet commandSet) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        HashMap<String, Command> allCommands = commandSet.getAllCommands();
        String description;

        System.out.println("Here are all commands in Duke:\n");

        for (String commandName : allCommands.keySet()) {
            description = "\"" + commandName + "\"\n";
            description += allCommands.get(commandName).getDescription();
            System.out.println(description + "\n");
        }

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Reports that the task has been tagged.
     * @param task the tagged task
     */
    public void reportTagTask(Task task) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println(task.getDescription() + " has been tagged with " + task.getTag());

        UiPrint.drawLine(UiPrint.STAR, 50);
    }

    /**
     * Reports that the task has been untagged.
     * @param task the untagged task
     */
    public void reportUntagTask(Task task) {
        UiPrint.drawLine(UiPrint.STAR, 50);

        System.out.println("The tag of " + task.getDescription() + " has been removed.");

        UiPrint.drawLine(UiPrint.STAR, 50);
    }
}
