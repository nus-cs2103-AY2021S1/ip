package executables;

import commands.Deadline;
import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class DeadlineCommand extends Execute {

    private String[] userInput;

    /**
     * constructor for a deadline command
     * @param userInput user input to use
     */
    public DeadlineCommand(String[] userInput) {
        this.userInput = userInput;
    }

    /**
     * obtains a users Input from the object
     * @return the user input
     */
    public String[] getUserInput() {
        return userInput;
    }

    @Override
    public String execute(TaskList taskList, UI ui) {
        try {
            Storage.save(taskList, Storage.FILE_PATH);
            String[] input = this.getUserInput();
            return ui.addLines(taskList.add(Deadline.createDeadline(input[1])));
        } catch (Exception e) {
            return (new DukeException("OOPS!!! The description of a deadline is empty/wrong.", e)).toString();
        }
    }
}

