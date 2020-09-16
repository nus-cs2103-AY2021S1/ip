package duke.gui;

import java.util.ArrayList;
import java.util.HashMap;

import duke.Duke;
import duke.command.Command;
import duke.data.DukeCommandSet;
import duke.exception.DukeException;
import duke.task.Task;

public class GuiResponse {

    public static final String TASK_SAVED = "Tasks saved successfully...";

    private Duke duke;
    private StringBuilder response;

    /**
     * Constructs a UiResponse.
     * @param duke the Duke that will use this UiResponse
     */
    public GuiResponse(Duke duke) {
        this.duke = duke;
        response = new StringBuilder();
    }

    public String getResponse() {
        return response.toString();
    }

    private void resetResponse() {
        response.delete(0, response.length());
    }

    /**
     * Greets the user.
     */
    public void greet() {
        resetResponse();

        response.append("Hello! I'm Duke\n");
        response.append("What can I do for you?\n");
    }

    /**
     * Prints out the current tasks saved in task list.
     */
    public void reportCurrentTasks() {
        resetResponse();

        response.append("Current tasks:\n\n");

        for (int i = 1; i <= duke.getTaskList().getSize(); i++) {
            response.append(i + ". " + duke.getTaskList().getTask(i) + '\n');
        }

        if (duke.getTaskList().getSize() == 0) {
            response.append("None\n");
        }
    }

    /**
     * Tells the user a new task is added to the task list.
     * @param task the new task added
     */
    public void reportNewTask(Task task) {
        resetResponse();

        response.append("Got it. I've added this task: \n");
        response.append(task + "\n");
        response.append("Now you have " + duke.getTaskList().getSize() + " tasks in the list.\n");
    }

    /**
     * Tells the user a task has been marked as done.
     * @param task the task marked as done
     */
    public void reportDoneTask(Task task) {
        resetResponse();

        response.append("Nice, I've marked this task as done: \n");
        response.append(task + "\n");
    }

    /**
     * Tells the user a task has been deleted.
     * @param task the task deleted
     */
    public void reportDeleteTask(Task task) {
        resetResponse();

        response.append("Noted. I've removed this task: \n");
        response.append(task + "\n");
        response.append("Now you have " + duke.getTaskList().getSize() + " tasks in the list\n");
    }

    /**
     * Says good bye to the user.
     */
    public void reportExit() {
        resetResponse();

        String exitWords = "Bye, hope to see you again soon!\n";
        response.append(exitWords);
    }

    /**
     * Shows the input tasks to the user.
     * @param tasks tasks to show
     */
    public void reportGivenTasks(ArrayList<Task> tasks) {
        resetResponse();

        response.append("Here are the matching tasks in your list:\n");

        if (tasks.size() == 0) {
            response.append("None\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                response.append(i + 1 + ". " + tasks.get(i) + '\n');
            }
        }
    }

    /**
     * Reports the input exception to user.
     * @param exception input exception
     */
    public void reportException(DukeException exception) {
        resetResponse();

        response.append(exception.getMessage() + '\n');
    }

    /**
     * Shows description of all commands.
     * @param commandSet the DukeCommandSet
     */
    public void showAllCommands(DukeCommandSet commandSet) {
        resetResponse();

        HashMap<String, Command> allCommands = commandSet.getAllCommands();
        response.append("Here are all commands in Duke:\n\n");

        for (String commandName : allCommands.keySet()) {
            response.append("\"" + commandName + "\"\n");
            response.append(allCommands.get(commandName).getDescription() + "\n\n");
        }
    }

    /**
     * Reports that the task has been tagged.
     * @param task the tagged task
     */
    public void reportTagTask(Task task) {
        resetResponse();

        response.append(task.getDescription() + " has been tagged with " + task.getTag());
    }
}
