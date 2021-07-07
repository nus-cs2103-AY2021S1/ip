package executables;

import commands.ToDo;
import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class TodoCommand extends Execute {

    private String[] userInput;

    /**
     * contructor for a TodoCommand object
     * @param userInput
     */
    public TodoCommand(String[] userInput) {

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
            ToDo item = new ToDo(input[1]);
            return ui.addLines(taskList.add(item));
        } catch (Exception e) {
            return (new DukeException("OOPS!!! The description of a todo is empty/wrong.", e)).toString();
        }
    }
}
