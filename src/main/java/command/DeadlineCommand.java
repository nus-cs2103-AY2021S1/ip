package command;

import exception.DeadlineException;
import storage.Storage;
import task.Deadline;
import task.Task;
import taskList.TaskList;
import undoStack.UndoStack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A subclass of Command.
 * Handles "deadline" command.
 */
public class DeadlineCommand extends Command {
    private static final String ADD_TASK_TITLE = "Got it. I've added this task:";
    private String[] input;

    /**
     * Constructor.
     * @param input user input.
     */
    public DeadlineCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param storage the storage working on data file.
     * @throws DeadlineException to show incorrect user input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DeadlineException {
        UndoStack.add(tasks);
        ArrayList<Task> store = tasks.getTaskList();
        int index = getIndexOfBy();
        if (input.length == 1 || index == 1) { //no description
            throw new DeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (index == input.length - 1 || index == 0) { //no time
            throw new DeadlineException("☹ OOPS!!! The time of a deadline cannot be empty.");
        }
        String description = getDescriptionFromUserInput(index);
        Date date = getDateFromUserInput(index);
        Deadline newTask = new Deadline(description, new SimpleDateFormat("MMM dd yyyy HH:mm").format(date));
        store.add(newTask);
        storage.save(new TaskList(store));
        return ADD_TASK_TITLE + "\n"
                + newTask + "\n"
                + "Now you have " + store.size() + " tasks in the list.";
    }

    /**
     * Gets the index number of "/by".
     * @return index number of "/by".
     */
    public int getIndexOfBy() {
        int index = 0;
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/by")) {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * Get the description of the task from the user input.
     * @param index index number of "/by".
     * @return description.
     */
    public String getDescriptionFromUserInput(int index) {
        String description = "";
        for (int i = 1; i < index; i++) {
            description = description + input[i] + " ";
        }
        // trim is to remove the extra space at the end of the description
        // caused when description is retrieved from user input
        return description.trim();
    }

    /**
     * Get the time of the task from the user input.
     * @param index index number of "/by".
     * @return time.
     */
    public Date getDateFromUserInput(int index) throws DeadlineException {
        String time = "";
        for (int i = index + 1; i < input.length; i++) {
            time = time + input[i] + " ";
        }
        // trim is to remove the extra space at the end of the time
        // caused when time is retrieved from user input
        time = time.trim();
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hhmm");
            date = formatter.parse(time);
        } catch (ParseException e) {
            throw new DeadlineException(" ☹ OOPS!!! The time of a deadline must be in the format of dd/M/yyyy hhmm.");
        }
        return date;
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
