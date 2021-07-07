package executables;

import commands.Event;
import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class EventCommand extends Execute {

    private String[] userInput;

    public EventCommand(String[] userInput) {
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
            return ui.addLines(taskList.add(Event.createEvent(input[1])));
        } catch (Exception e) {
            return (new DukeException("OOPS!!! The description of an event is empty/wrong.", e)).toString();
        }
    }
}

