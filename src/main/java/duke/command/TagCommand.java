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
     * Executes command to tag a task
     * @param taskList list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @param responseList list of strings to return to user
     * @throws DukeException exception thrown to be caught by caller method
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Gui gui, Storage storage, ArrayList<String> responseList)
            throws DukeException {

        assert taskList instanceof TaskList : "tasklist had to be an isntance of a TaskList";

        try {
            // preparing index of task
            char numberCharacter = this.fullCommand.charAt(4);
            int index = Integer.parseInt(String.valueOf(numberCharacter)) - 1;

            if (index > taskList.getTasksLeft()){
                throw new DukeException("Please enter a task number within the range of tasks to be tagged");
            }

            // preparing tags
            String stringOfTags = this.fullCommand.substring(6);
            String[] arrOfTags = stringOfTags.split("#");
            String test = arrOfTags[1];

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
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException a) {
            throw new DukeException("  Please enter a valid tag, 'tag <task number> #<tag name)'");
        } catch (NumberFormatException n) {
            throw new DukeException("Please enter a valid number task to be tagged");
        }
    }
}






















