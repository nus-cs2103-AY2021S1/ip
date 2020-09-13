package duke.commands;

import duke.errors.DukeException;
import duke.errors.FindException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * used to handle case where find is keyword
 */
public class FindCommand extends Command {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public FindCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Finds the tasks which contains keyword in string
     *
     * @param tasks to look for the task's string value
     * @param ui
     * @param storage no need
     * @return String returns the string of the output that informs the find action is successful.
     * @throws DukeException used to throw error when no words mentioned after find or the keyword is not present in
     * tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return process(tasks);
            //Returns string if correct input and updates tasks and file in storage if correct input by user, else
            // throws exception
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Returns String informing user that find command is successful otherwise throws FindException
     *
     * @param tasks to access the tasks to find out the description used and to compare keywords given for this command
     * @return String if user input is in correct format
     * @throws FindException when user input is given in wrong format
     */
    public String process(TaskList tasks) throws FindException {
        if (isNumberOrDescriptionAbsent()) {
            throw new FindException(false, true, "");
        }
        String find = commandDescription.substring(5);
        String[] strings = find.split(" ", -2); // keywords split into different Strings
        setTasks(strings, tasks);
        if (this.tasks.size() == 0) {
            throw new FindException(true, false, find);
        } else {
            return findMessage();
        }
    }
    /**
     * sets the Tasks list here with Tasks containing key words.
     *
     * @param strings contains String of key words
     * @param tasks contains all the current tasks
     */
    private void setTasks(String[] strings, TaskList tasks) {
        List<Task> allTasks = tasks.getAllTasks();
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            Task task = allTasks.get(i);
            String string = task.getName();
            String[] comp = string.split(" ", -2);
            boolean contains = false;
            for (int j = 0; j < strings.length; j++) {
                String s = strings[j];
                for (int k = 0; k < comp.length; k++) {
                    if (comp[k].equals(s)) { //checks whether Task name/description contains keywords given by user
                        contains = true; //then assigns contains true if that is the case
                        break;
                    }
                }
            }
            if (contains) {
                this.tasks.add(task); //if contains is true, Task is added to ArrayList of tasks.
            }
        }
    }

    /**
     * Prints out the find message
     *
     * @return String of response
     */
    private String findMessage() {
        String s = "  Here are the matching tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            s = s + "\n" + "  " + (i + 1) + "." + task.toString(); // concatenates all the Task present in tasks
        }
        return s;
    }


}
