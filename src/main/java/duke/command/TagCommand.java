package duke.command;

import duke.Gui.Gui;
import duke.component.DukeException;
import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class TagCommand extends Command{
    private final String fullCommand;

    public TagCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * abstract method to be implemented by child classes.
     * @param taskList list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @param responseList
     * @throws DukeException exception caught when method implemented by child classes.
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Gui gui, Storage storage, ArrayList<String> responseList)
            throws DukeException {

        // preparing index of task
        char numberCharacter = this.fullCommand.charAt(4);
        int index = Integer.parseInt(String.valueOf(numberCharacter)) - 1;

        // preparing tags
        String stringOfTags = this.fullCommand.substring(6);
        String[] arrOfTags = stringOfTags.split("#");

        // retrieve and modify
        Task taskToChange = taskList.getItem(index);
        for (int i = 1; i < arrOfTags.length; i++) {
            taskToChange.addTag(arrOfTags[i]);
        }
        // push new item to list
        taskList.modifyItem(index, taskToChange);
        storage.modifyTask(taskToChange, index);

        ArrayList<String> addTagMessage = gui.addTag(arrOfTags);
        String taskInfo = taskToChange.toString();

        responseList.addAll(addTagMessage);
        responseList.add(taskInfo);

        return responseList;
    }
}






















