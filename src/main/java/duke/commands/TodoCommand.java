package duke.commands;

import duke.errors.DukeException;
import duke.errors.TodoException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.ToDo;

/**
 * Handles case where todo is the input
 */
public class TodoCommand extends AddCommand {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     * @param lengthOfKeyword assigns this to this.lengthOfKeyword
     */
    public TodoCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Adds deadline into a task list in TaskList.
     *
     * @param tasks to change the taskList if necessary
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file in the if necessary
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException whenever there is an error, no
     * description
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            return process(tasks, storage);
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * Returns String if user input is correct and updates the TaskList tasks and file containing tasks file in
     * storage
     *
     * @param tasks to add ToDo task here if the user input is correct
     * @param storage to add ToDo task in the file containing tasks
     * @return String informing that the user has input the task
     * @throws DukeException if user input is correct
     */
    private String process(TaskList tasks, Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            throw new TodoException();
        }
        ToDo t = new ToDo(todoDescription());
        return updateTaskList(storage, t, tasks);
    }
    /**
     * returns the name of the task
     *
     * @return name of task
     */
    private String todoDescription() {
        return userInput.substring(lengthOfKeyword + 1);
    }

}

