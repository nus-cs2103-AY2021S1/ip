package executables;

import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class DoneCommand extends Execute {

    private String[] userInput;

    public DoneCommand(String[] userInput) {
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
            int index = Integer.parseInt(input[1]) - 1;
            return ui.addLines(taskList.markCompleted(index));
        } catch (Exception e) {
            return (new DukeException("Integer not detected")).toString();
        }
    }
}

