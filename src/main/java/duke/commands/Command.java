package duke.commands;

import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * this is an abstract class used for polymorphism, parent class of all Command classes
 */
public abstract class Command {
    /**
     * commandName which contains information on task and details to perform task
     * isExit is used to tell whether program terminates, where id true, it terminates
     */
    protected String userInput; //the String input given by user
    protected int lengthOfKeyword; //length of keyword eg for find is 4
    private boolean isExit = false;

    /**
     * Assigns string to a value
     *
     * @param userInput assigns this.string to string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
     */
    public Command(String userInput, int lengthOfKeyword) {
        this.userInput = userInput;
        this.lengthOfKeyword = lengthOfKeyword;
    }

    /**
     * gets value of exit
     * @return exit
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the necessary task
     *
     * @param tasks used to access tasks in its list and change if necessary
     * @param ui used to assign ui's dukeException if there is an error in user input
     * @param storage to change the input there if necessary
     * @throws DukeException if there are exceptions present
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the number/description is present.
     *
     * @return true is the number/description is absent and false if number is present.
     */
    protected boolean isNumberOrDescriptionAbsent() {
        return userInput.length() == lengthOfKeyword || userInput.length() == lengthOfKeyword + 1;
        //since the delete number appears after length of keyword/+1
    }

}
