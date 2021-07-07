package executables;

import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class FindCommand extends Execute {

    private String[] userInput;

    public FindCommand(String[] userInput) {
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
            taskList.findTask(input[1]);
            return ui.addLines(taskList.printOutKeyWordList());
        } catch (Exception e) {
            return (new DukeException("OOPS!!! Please specify a keyword to search for!", e)).toString();
        }
    }
}

