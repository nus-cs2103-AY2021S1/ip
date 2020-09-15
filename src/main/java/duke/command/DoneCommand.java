package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDoneCommandException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that marks a Task as done.
 */
public class DoneCommand extends UserCommand {

    private static final String DEFAULT_MESSAGE = "Nice! I've marked this task as done: " + "\n" + "[\u2713]";

    /**
     * @param userInput User's input.
     */
    public DoneCommand(String userInput) {
        super(userInput);
    }


    /**
     * @param index String representation of the index of the Task to be marked as done.
     * @param taskSize Size of the TaskList.
     * @return
     * @throws InvalidDoneCommandException
     */
    public boolean checkInputRange(String index, int taskSize) throws InvalidDoneCommandException {
        try {
            int itemToBeMarkedAsDone = Integer.parseInt(index);
            return itemToBeMarkedAsDone > taskSize || itemToBeMarkedAsDone <= 0;
        } catch (NumberFormatException ex) {
            throw new InvalidDoneCommandException();
        }
    }

    /**
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] doneCommandArray = userInput.split(" ");
        if (doneCommandArray.length < 2) {
            throw new InvalidDoneCommandException();
        }
        if (checkInputRange(doneCommandArray[1], taskList.listSize())) {
            throw new InvalidDoneCommandException();
        } else {
            int itemToBeMarkedAsDone = Integer.parseInt(doneCommandArray[1]);
            taskList.getTaskAtIndex(itemToBeMarkedAsDone - 1).markAsDone();
            return ui.printResponse(DEFAULT_MESSAGE + taskList.getTaskAtIndex(itemToBeMarkedAsDone - 1)
                    .getDescription());
        }
    }
}
