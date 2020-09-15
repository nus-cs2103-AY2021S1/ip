package duke.commands;

import java.util.ArrayList;
import java.util.List;

import duke.errors.DukeException;
import duke.errors.FindException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Task;

/**
 * Handles case where find is keyword
 */
public class FindCommand extends Command {
    private List<Task> tasks = new ArrayList<>();
    //contains list of Task objects whose name contains words after the find keyword

    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
     */
    public FindCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Finds the tasks which contains keyword in string
     *
     * @param tasks to look for the task's string value
     * @param ui to store the DukeException that may be thrown if there is an error in user input
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
        String find = userInput.substring(5);
        String[] strings = find.split(" ", -2); // keywords split into different Strings
        setTasks(strings, tasks);
        if (this.tasks.size() == 0) {
            throw new FindException(true, false, find);
        } else {
            return findMessage();
        }
    }
    /**
     * sets the Tasks list here with Tasks containing words given by user.
     *
     * @param strings contains String of key words
     * @param tasks contains all the current tasks
     */
    private void setTasks(String[] strings, TaskList tasks) {
        List<Task> allTasks = tasks.getAllTasks();
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            Task task = allTasks.get(i);
            String string = task.getName(); //gives name of Task object
            String[] comp = string.split(" ", -2); //split object into separate words
            boolean contains = taskContainsUserWords(strings, comp); //
            if (contains) {
                this.tasks.add(task); //if contains is true, Task is added to ArrayList of tasks.
            }
        }
    }

    /**
     * Checks whether the Task contains the words given by user
     *
     * @param strings array of Task name split into different words
     * @param comp array of user words split into different words
     * @return true is task contains words given by user and false otherwise
     */
    private boolean taskContainsUserWords(String[] strings, String[] comp) {
        boolean contains = false;
        for (int j = 0; j < strings.length; j++) {
            String word = strings[j];
            contains = wordPresentInUserWords(word, comp);
        }
        return contains;
    }

    /**
     * Checks whether a word is the same as the array of words(String) given by user
     *
     * @param word a word from from Task name
     * @param comp array of words given by user
     * @return true if word is contained in comp and false otherwise
     */
    private boolean wordPresentInUserWords(String word, String[] comp) {
        boolean contains = false;
        for (int k = 0; k < comp.length; k++) {
            if (comp[k].equals(word)) { //checks whether Task name/description contains keywords given by user
                contains = true; //then assigns contains true if that is the case
                break;
            }
        }
        return contains;
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

