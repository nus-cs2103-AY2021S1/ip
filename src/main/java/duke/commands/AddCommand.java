package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.tasks.Task;

/**
 * This class handles the case of adding different tasks which are ToDo, deadline and Event
 */
public abstract class AddCommand extends Command {
    /**
     * constructor that assigns string value of string
     *
     * @param input passes it to super class constructor
     */
    public AddCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Gives a String saying that the task list has been updated
     *
     * @param task to be added into taskList
     * @param taskList where task is added
     * @return String that informs task is added into taskList
     */
    protected static String stringToUpdateTaskList(Task task, TaskList taskList) {
        return "  Got it. I've added this task:\n  " + task.toString() + "\n" + //Task added message
                "  Now you have " + taskList.getAllTasks().size() + " tasks in the list.";
    }

    /**
     * adds the task to list of task in taskList and into the file in storage
     *
     * @param storage where the file here is updated
     * @param task this task is added into storage and taskList
     * @param taskList where the tasks here is updated with task added
     * @throws DukeException when the file in storage is not present
     */
    protected static String updateTaskList(Storage storage, Task task, TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(storage.getFilePath(), true);
            //updates the file in storage as new task is added
            taskList.getAllTasks().add(task);
            fw.write(task.inputListFormat() + "\n");
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
        return stringToUpdateTaskList(task, taskList);
    }

}
