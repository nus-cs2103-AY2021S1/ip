package duke.command;

import duke.Gui.Gui;
import duke.component.DukeException;
import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    String bodyCommand;

    public FindCommand(String fullCommand) throws DukeException {
        try {
            this.bodyCommand = fullCommand.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("please enter a term to search for");
        }
    }

    /**
     * Main logic executed to perform search
     * @param tasks list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Gui gui, Storage storage, ArrayList<String> responseList) {
        ArrayList<Task> listOfTasksFound = new ArrayList<>();
        ArrayList<Task> list = tasks.getList();
        list.forEach(task -> {
            if (task.isContain(this.bodyCommand)) {
                listOfTasksFound.add(task);
            }
        });
        responseList.addAll(gui.findMessage(listOfTasksFound));
        return responseList;
    }
}
