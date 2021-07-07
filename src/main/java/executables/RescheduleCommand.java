package executables;

import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class RescheduleCommand extends Execute {

    private String[] userInput;

    /**
     * constructor for a RescheduleCommand Object
     * @param userInput
     */
    public RescheduleCommand(String[] userInput) {
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
            return ui.addLines(taskList.rescheduleTask(input[1]));
        } catch (Exception e) {
            return (new DukeException("OOPS!!! The description to reschedule a task is empty/wrong", e))
                    .toString();
        }
    }
}
